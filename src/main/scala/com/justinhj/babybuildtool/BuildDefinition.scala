package com.justinhj.babybuildtool

import java.io.File
import BuildDefinition._
import java.io.BufferedWriter
import java.io.FileWriter
import scala.util.control.NonFatal

object BuildDefinition {
  type OrganisationString = String

}

case class BuildDefinition (
  name: String, // Name of the project
  organisation : OrganisationString = "com.example", // Organisation  (todo regexp for valid organization)
  version : String = "0.1-SNAPSHOT", //  version of project
  scalaVersion : String = "2.12.6" // scala version (can be verified?, maybe maintain a list of valid scala or from web)
                           ) {

  // Convert to SBT build DSL
  def toSbt : String = {

    // TODO project root should be a variable
    val project = s"""import sbt._
                    |
                    |lazy val $name = (project in file(".")).
                    |    settings (
                    |      name := "$name",
                    |      organization := "$organisation",
                    |      version := "$version",
                    |      scalaVersion := "$scalaVersion"
                    |      // add other settings here
                    |    )
                    |""".stripMargin

    project
  }

  // Export the build to a file in the specified folder
  def writeToSbtFile(targetDirectory : File) : Either[String, Unit] = {

    if(targetDirectory.isDirectory) {

      try {
        val fileName = s"${name}.sbt" // TODO optional extension and overridable name
        val file = new File(targetDirectory, fileName)

        val output = new BufferedWriter(new FileWriter(file))

        output.write(toSbt)

        Right(output.close())


      }
      catch {
        case NonFatal(err) =>
          Left(err.getMessage)
      }


    }
    else {
      Left(s"${targetDirectory.getCanonicalPath} is not a directory")
    }

  }

}
