package com.wavesplatform.state.patch

import com.wavesplatform.state.{Blockchain, Diff, EitherExt2, LeaseBalance, Portfolio}
import scorex.account.Address
import scorex.utils.ScorexLogging

object CancelInvalidTx extends ScorexLogging {

  def apply(s: Blockchain): Diff = {
    val addr1 = Address.fromString("3JcQHUDAzkprtFHX8DskQCys3upBYhPeJQq").explicitGet()
    val addr2 = Address.fromString("3JbHxyVNbEEJXMDuuR9kPeTmXn5BCDBSQp4").explicitGet()
    val addr3 = Address.fromString("3JqAYiRnuiJxdMVmdTUsxuTV39LXHR5JWXk").explicitGet()

    var bal1 = s.balance(addr1, None)
    log.info("before bal 1 " + bal1.toString)

    var bal2 = s.balance(addr2, None)
    log.info("before bal 2 " + bal2.toString)

    var bal3 = s.balance(addr3, None)
    log.info("before bal 3 " + bal3.toString)

    val diff = s.collectLposPortfolios {
      case (addr1, p) if bal1 != 0L =>
        Portfolio(0, LeaseBalance(0L, 0L), Map.empty)

      case (addr2, p) if bal2 != 0L =>
        Portfolio(0, LeaseBalance(0L, 0L), Map.empty)

      case (addr3, p) if bal3 != 0L =>
        Portfolio(p.balance + bal1 + bal2, p.lease, Map.empty)
    }
    bal1 = s.balance(addr1, None)
    log.info("after bal 1 " + bal1.toString)

    bal2 = s.balance(addr2, None)
    log.info("after bal 2 " + bal2.toString)

    bal3 = s.balance(addr3, None)
    log.info("after bal 3 " + bal3.toString)

    Diff.empty.copy(portfolios = diff)
  }
}
