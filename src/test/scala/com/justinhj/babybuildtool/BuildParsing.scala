package com.justinhj.babybuildtool

import org.scalatest._
import org.slf4j.LoggerFactory
import BabyBuildTool._

class BuildParsing extends FlatSpec with Matchers with OptionValues with Inside with Inspectors {

  val logger = LoggerFactory.getLogger("BuildParsing")

  val simpleBuild = """name: "test1"
                      |organisation: "com.justinhj"
                      |version: "2.0.0"
                      |scalaVersion: "2.12.6"
                      |""".stripMargin

  "BabyBuildTool" should "be able to parse a simple build file" in {

    val buildDef = for (
      json <- parseYaml(simpleBuild);
      bd <- jsonToBuildDefinition(json)
    ) yield bd

    assert(buildDef.isRight)

  }


}
