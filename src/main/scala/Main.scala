import neotypes.GraphDatabase
import neotypes.mappers.ResultMapper
import neotypes.syntax.all.*
import org.neo4j.driver.AuthTokens

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}

@main def main(): Unit =

  //TODO: Use configuration settings for protocol, hostname, port, username, password
  //TODO: Read configuration from arguments, environment variables or configuration files

  val driver = GraphDatabase.asyncDriver[Future]("bolt://localhost:7687", AuthTokens.basic("neo4j", "wrong"))

  println("")

  val peopleTuple =
    "MATCH (p: Person) RETURN p.name, p.born LIMIT 10"
      .query(ResultMapper.tuple[String, Int])
      .list(driver)

  val tupleResult = Await.result(peopleTuple, 1.second)

  println("Result from Tuple:")
  tupleResult.foreach(println)
  println("")

  final case class Person(born: Int, name: Option[String])

  val peopleCaseClass =
    "MATCH (p: Person) RETURN p.born,p.name LIMIT 10"
    .query(ResultMapper.fromFunction(Person.apply))
    .list(driver)

  val caseClassResult = Await.result(peopleCaseClass, 1.second)

  println("Result from Case Class:")
  caseClassResult.foreach(println)
  println("")

  Await.ready(driver.close, 1.second)
