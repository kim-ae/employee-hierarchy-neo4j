package br.com.kimae.neo4jhierarchytest;

import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@NodeEntity
@Getter
@Setter
@Builder
@ToString
public class Person {

    @Id
    @GeneratedValue
    private Long id;
    
    private String name;

    private String registration;

    @Relationship(type="IS_SUBORDINATE")
    private Person manager;

    // @Relationship(type="IS_SUBORDINATE", direction = Relationship.INCOMING)
    // private List<Person> subordinates;

}