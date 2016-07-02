name := """bacon-finder"""

version := "1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  "net.virtual-void" %%  "json-lenses" % "0.6.1",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.2" % "test"
)

// Uncomment to use Akka
//libraryDependencies +=

