# Disclaimer
This project is just a sandbox to understand how is possible to import employee hierarchy using Neo4j + spring-data, in anyway is a production ready code.

# Setup
```
docker run  --name testneo4j  -p7474:7474 -p7687:7687  -d  --env NEO4J_AUTH=neo4j/secret neo4j:latest
```

# QUERIES
NOT CONNECTED: MATCH (p:Person) WHERE NOT(p:Person {registration:"BOSS"})<-[:IS_SUBORDINATE*]-(:Person) AND NOT ((:Person {registration: "BOSS"})<-[:IS_SUBORDINATE*]-(p:Person)) RETURN p

CYCLE: MATCH (p:Person) WHERE (p:Person)<-[:IS_SUBORDINATE]-(p:Person) RETURN p