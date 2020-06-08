package br.com.kimae.neo4jhierarchytest;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long>{
    
    public List<Person> findByManagerId(Long id);

    @Query("MATCH (p:Person) WHERE (p:Person)<-[:IS_SUBORDINATE]-(p:Person) RETURN p")
    public List<Person> findCycles();

    @Query("MATCH (p:Person) WHERE NOT(p:Person {registration:'CEO'})<-[:IS_SUBORDINATE*]-(:Person) AND NOT ((:Person {registration: 'CEO'})<-[:IS_SUBORDINATE*]-(p:Person)) RETURN p")
    public List<Person> findDisconnectedBranches();
}