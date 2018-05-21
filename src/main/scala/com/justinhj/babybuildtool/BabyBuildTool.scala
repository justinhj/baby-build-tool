package com.justinhj.babybuildtool

import io.circe.{Json, ParsingFailure, yaml}

object BabyBuildTool {

  def parseYaml(yml: String) : Either[ParsingFailure, Json] = {

    yaml.parser.parse(yml)

  }

  def main(args : Array[String]) : Unit = {

    println("Hi mum")

  }

}
