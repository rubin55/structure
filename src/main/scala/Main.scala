import neotypes.GraphDatabase
import neotypes.mappers.ResultMapper
import neotypes.syntax.all.*
import org.neo4j.driver.AuthTokens

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}

@main def main(): Unit =

  val driver = GraphDatabase.asyncDriver[Future]("bolt://localhost:7687", AuthTokens.basic("neo4j", "****"))

  val peopleTuple =
    "MATCH (p: Person) RETURN p.name, p.born LIMIT 10"
      .query(ResultMapper.tuple[String, Int])
      .list(driver)

  Await.result(peopleTuple, 1.second)

  final case class Person(id: Long, born: Int, name: Option[String], notExists: Option[Int])

  val peopleCaseClass =
    "MATCH (p: Person) RETURN p LIMIT 10"
    .query(ResultMapper.fromFunction(Person.apply))
    .list(driver)

  Await.result(peopleCaseClass, 1.second)


  Await.ready(driver.close, 1.second)
