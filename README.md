## Structure in Scala

This is a minimal experiment implementing the core ideas of [structure][1] in
Scala, using `neotypes` to talk to a `neo4j` instance.

[1]: /raaftech/structure

### Load example database

Assuming you have a `neo4j` database running on `localhost:7687`:

```shell
export NEO4J_USERNAME=neo4j
export NEO4J_PASSWORD=wrong
cypher-shell < movies.cql
```
