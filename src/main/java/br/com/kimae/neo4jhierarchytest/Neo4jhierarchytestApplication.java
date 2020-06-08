package br.com.kimae.neo4jhierarchytest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Neo4jhierarchytestApplication implements CommandLineRunner{

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Neo4jhierarchytestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("CLEANNING");
		repository.deleteAll();

		log.info("START CREATION");
		Person ceo = Person.builder().name("jorge").registration("CEO").build();
		
		Person jessica = Person.builder().name("jessica").registration("1").build();
		jessica.setManager(ceo);
		Person mano = Person.builder().name("mano").registration("2").build();
		mano.setManager(ceo);
		Person kao = Person.builder().name("kao").registration("3").build();
		kao.setManager(ceo);

		Person folha = Person.builder().name("folha").registration("5").build();
		folha.setManager(mano);
		Person jiro = Person.builder().name("jiro").registration("6").build();
		jiro.setManager(jiro);
		Person firo = Person.builder().name("firo").registration("4").build();
		firo.setManager(jiro);
		Person alone = Person.builder().name("alone").registration("7").build();

		List<Person> guys = Arrays.asList(ceo, jessica, mano, kao, firo, folha, jiro, alone);
		repository.saveAll(guys);

		List<Person> cycles = repository.findCycles();
		log.info(" --- CYCLES ---");
		cycles.stream().map(Object::toString).forEach(log::info);
		log.info(" --- CYCLES ---");

		List<Person> ceoLess = repository.findDisconnectedBranches();
		log.info(" --- CEO LESS ---");
		ceoLess.stream().map(Object::toString).forEach(log::info);
		log.info(" --- CEO LESS ---");
	}

}
