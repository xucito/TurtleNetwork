package com.wavesplatform.settings

import com.typesafe.config.ConfigFactory
import com.wavesplatform.state2.ByteStr
import net.ceedubs.ficus.Ficus._
import net.ceedubs.ficus.readers.ArbitraryTypeReader._
import org.scalatest.{FlatSpec, Matchers}

class WalletSettingsSpecification extends FlatSpec with Matchers {
  "WalletSettings" should "read values from config" in {
<<<<<<< HEAD
    val config = loadConfig(ConfigFactory.parseString(
      """TN.wallet {
=======
    val config   = loadConfig(ConfigFactory.parseString("""waves.wallet {
>>>>>>> pr/3
        |  password: "some string as password"
        |  seed: "BASE58SEED"
        |}""".stripMargin))
    val settings = config.as[WalletSettings]("TN.wallet")

    settings.seed should be(Some(ByteStr.decodeBase58("BASE58SEED").get))
    settings.password should be("some string as password")
  }
}
