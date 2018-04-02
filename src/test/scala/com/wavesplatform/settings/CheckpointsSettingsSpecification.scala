package com.wavesplatform.settings

import com.typesafe.config.ConfigFactory
import com.wavesplatform.state2.ByteStr
import org.scalatest.{FlatSpec, Matchers}

class CheckpointsSettingsSpecification extends FlatSpec with Matchers {
  "CheckpointsSettings" should "read values" in {
<<<<<<< HEAD
    val config = ConfigFactory.parseString(
      """
        |TN {
=======
    val config   = ConfigFactory.parseString("""
        |waves {
>>>>>>> pr/3
        |  checkpoints {
        |    public-key: "BASE58PUBKEY"
        |  }
        |}
      """.stripMargin).resolve()
    val settings = CheckpointsSettings.fromConfig(config)

    settings.publicKey should be(ByteStr.decodeBase58("BASE58PUBKEY").get)
  }
}
