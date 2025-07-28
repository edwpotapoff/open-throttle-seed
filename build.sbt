ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.5"

PB.protocVersion := "3.25.5"

Compile / PB.targets := Seq(
  scalapb.gen() -> (Compile / sourceManaged).value / "scalapb"
)

lazy val root = (project in file("."))
  .settings(
    name := "yourtest",
    libraryDependencies ++= Seq(
      "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
      "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
      "io.grpc" % "grpc-core" % scalapb.compiler.Version.grpcJavaVersion, // было "1.71.0"
      "io.grpc" % "grpc-stub" % scalapb.compiler.Version.grpcJavaVersion,
      "io.grpc" % "grpc-protobuf" % scalapb.compiler.Version.grpcJavaVersion,
      "javax.annotation" % "javax.annotation-api" % "1.3.2"
    )
  )
