package br.com.kimae.neo4jhierarchytest.bonusstone;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class ImportRow {
    @CsvBindByName(column="Matrícula")
    private String registration;
    @CsvBindByName(column="Matricula do solicitante")
    private String requesterRegistration;
}
