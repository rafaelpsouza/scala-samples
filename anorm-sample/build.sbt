name := "anorm-sample"

version := "1.0"

scalaVersion := "2.10.2"

resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases"

libraryDependencies ++= Seq(
    "com.h2database"                % "h2"              % "1.3.174",
    "com.github.seratch" %% "scalikejdbc" % "[0.5,)",
    "com.typesafe.play"             %% "anorm"          % "2.2.1" 
)