package com.justinhj.babybuildtool

import BuildDefinition._

object BuildDefinition {
  type OrganisationString = String

}

case class BuildDefinition (
  name: String, // Name of the project
  organisation : OrganisationString = "com.example", // Organisation  (todo regexp for valid organization)
  version : String = "0.1-SNAPSHOT", //  version of project
  scalaVersion : String = "2.12.6" // scala version (can be verified?, maybe maintain a list of valid scala or from web)
)
