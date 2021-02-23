package br.com.kimae.neo4jhierarchytest.bonusstone;

import lombok.*;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(of = "key")
public class Node {

    @Id
    private final String key;

    @Relationship(type="CONNECTED_BY")
    private Node node;
}
