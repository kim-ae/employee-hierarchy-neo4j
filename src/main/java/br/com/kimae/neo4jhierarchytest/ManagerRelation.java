package br.com.kimae.neo4jhierarchytest;

import java.util.Collection;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RelationshipEntity(type = "MANAGER_RELATION")
public class ManagerRelation {

    @Id
    @GeneratedValue
    private Long id;

    private Collection<String> registrations;

    @StartNode
    private Person manager;

    @EndNode
    private Person subordinate;
    
}