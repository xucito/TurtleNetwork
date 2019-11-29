import WavesDockerKeys._

enablePlugins(WavesDockerPlugin, ItTestPlugin)

description := "NODE integration tests"
libraryDependencies ++= Dependencies.itTest

inTask(docker)(
  Seq(
    imageNames := Seq(ImageName("com.wavesplatform/node-it")),
    exposedPorts := Set(6860, 6861), // NetworkApi, RestApi
    additionalFiles ++= (LocalProject("node") / Universal / stage).value +: Seq(
      (Test / resourceDirectory).value / "template.conf",
      (Test / sourceDirectory).value / "container" / "start-TN.sh"
    )
  ))
