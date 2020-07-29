import WavesDockerKeys._

enablePlugins(WavesDockerPlugin, IntegrationTestsPlugin)

description := "NODE integration tests"
libraryDependencies ++= Dependencies.it

def stageFiles(ref: ProjectReference): TaskKey[File] =
  ref / Universal / stage

(Test / test) := (Test / test).dependsOn(Docker / docker).value

inTask(docker)(
  Seq(
    imageNames := Seq(ImageName("turtlenetwork/node-it")),
    exposedPorts := Set(6860, 6861, 6870), // NetworkApi, RestApi
    additionalFiles ++= Seq(
      stageFiles(LocalProject("node")).value,
      stageFiles(LocalProject("grpc-server")).value,
      (Test / resourceDirectory).value / "template.conf",
      (Test / sourceDirectory).value / "container" / "start-TN.sh"
    )
  )
)
