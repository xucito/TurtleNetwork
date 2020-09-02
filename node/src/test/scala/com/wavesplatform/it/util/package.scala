package com.wavesplatform.it

import com.wavesplatform.settings.Constants

package object util {
  implicit class DoubleExt(val d: Double) extends AnyVal {
    def TN: Long = (BigDecimal(d) * Constants.UnitsInWave).toLong
  }
}
