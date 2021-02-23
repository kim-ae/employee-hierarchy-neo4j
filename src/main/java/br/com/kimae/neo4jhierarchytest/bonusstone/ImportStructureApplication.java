package br.com.kimae.neo4jhierarchytest.bonusstone;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@SpringBootApplication
@Slf4j
public class ImportStructureApplication implements CommandLineRunner {

    @Autowired
    private NodeRepository nodeRepository;

    public static void main(String[] args) {
        SpringApplication.run(ImportStructureApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("CLEANNING");
        nodeRepository.deleteAll();

        try (Reader reader = Files.newBufferedReader(Paths.get("filepath"))) {
            CsvToBean<ImportRow> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(ImportRow.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<ImportRow> values = csvToBean.parse();
            final Set<String> registrations = values.stream().map(ImportRow::getRequesterRegistration).filter((r)->!r.trim().equals("-")).collect(toSet());
            final List<ImportRow> validatorsRows = values.stream()
                    .filter((r)->registrations.contains(r.getRegistration()))
                    .collect(Collectors.toList());

            final Map<String, Node> nodes =
                    validatorsRows.stream()
                            .map((r) -> Node.builder().key(r.getRegistration()).build())
                    .collect(Collectors.toMap(
                            Node::getKey,
                            Function.identity())
                    );
            final Map<String, String> hierarchy = validatorsRows.stream()
                    .collect(
                            Collectors.toMap(
                                ImportRow::getRegistration,
                                ImportRow::getRequesterRegistration
                            )
                    );
            hierarchy.entrySet().stream().forEach((e) ->nodes.get(e.getKey()).setNode(nodes.get(hierarchy.get(e.getKey()))));
            nodeRepository.saveAll(nodes.values());
        }
    }
}
