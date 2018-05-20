package com.justinhj

import io.circe._
import io.circe.yaml

// Command line driver
// Simply converts the provided yml file to an sbt build file

object BabyBuildTool {

  def parseYaml(yml: String) : Either[ParsingFailure, Json] = {

    yaml.parser.parse(yml)

  }

  def main(args : Array[String]) : Unit = {

    println("Hi mum")

  }

}
