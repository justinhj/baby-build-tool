package com.justinhj.babybuildtool

import org.scalatest._

class YmlParsing extends FlatSpec with Matchers with OptionValues with Inside with Inspectors {


  val goodSampleYaml = """version: '3'
                     |services:
                     |  web:
                     |    build: .
                     |    ports:
                     |     - "5000:5000"
                     |  redis:
                     |    image: "redis:alpine"
                     |""".stripMargin

  val badIndentSampleYaml = """version: '3'
                         |services:
                         |  web:
                         |    build: .
                         |    ports:
                         |- "5000:5000"
                         |  redis:
                         |    image: "redis:alpine"
                         |""".stripMargin

  "BabyBuildTool" should "be able to parse a correct YML string" in {

    BabyBuildTool.parseYaml(goodSampleYaml) match {
      case Right(json) => succeed
      case Left(_) => fail
    }

  }

  it should "not be able to parse a bad YML string" in {

    BabyBuildTool.parseYaml(badIndentSampleYaml) match {
      case Right(_) => fail
      case Left(err) =>
        succeed
    }

  }



}
