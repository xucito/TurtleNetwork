package com.wavesplatform.settings

import com.wavesplatform.Version
import scorex.utils.ScorexLogging

/**
  * System constants here.
  */
object Constants extends ScorexLogging {
<<<<<<< HEAD
  val ApplicationName = "TN"
  val AgentName = s"TN v${Version.VersionString}"

  val UnitsInWave = 100000000L
  val TotalWaves = 500000000L
=======
  val ApplicationName = "waves"
  val AgentName       = s"Waves v${Version.VersionString}"

  val UnitsInWave = 100000000L
  val TotalWaves  = 100000000L
>>>>>>> pr/3
}
