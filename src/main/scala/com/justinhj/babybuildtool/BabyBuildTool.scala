package com.justinhj.babybuildtool

import java.io.File

import io.circe.{DecodingFailure, Json, ParsingFailure, yaml}
import io.circe.generic.auto._

import scala.io.Source

object BabyBuildTool {

  def parseYaml(yml: String) : Either[ParsingFailure, Json] = {

    yaml.parser.parse(yml)

  }

  def jsonToBuildDefinition(json: Json) : Either[DecodingFailure, BuildDefinition] = {

    json.as[BuildDefinition]

  }

  def main(args : Array[String]) : Unit = {

    if(args.size < 1) {
      println("Please supply a yml file")

      System.exit(1)

    } else {

      val inputFile = args(0)

      val input = Source.fromFile(inputFile).getLines.foldLeft(""){(acc, n) => acc + n + "\n"}

      val buildDef = for (
        json <- parseYaml(input);
        bd <- jsonToBuildDefinition(json)
      ) yield bd

      buildDef match {
        case Right(bd) =>
          bd.writeToSbtFile(new File("/tmp/"))
          println("Wrote file to disk")
        case Left(err) =>
          println(s"Failed because ${err.getMessage}")
      }

    }


  }

}
