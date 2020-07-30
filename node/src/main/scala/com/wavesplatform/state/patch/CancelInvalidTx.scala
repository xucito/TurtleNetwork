package com.wavesplatform.state.patch

import com.wavesplatform.account.{Address, AddressScheme}
import com.wavesplatform.common.utils.EitherExt2
import com.wavesplatform.database.AddressId
import com.wavesplatform.state.patch.CancelAllLeases.CancelledLeases
import com.wavesplatform.state.{Blockchain, Diff, LeaseBalance, Portfolio}
import com.wavesplatform.utils.ScorexLogging
import play.api.libs.json.Json

object CancelInvalidTx extends DiffPatchFactory {
  val height: Int = AddressScheme.current.chainId.toChar match {
    case 'L' => 450000
  }


  private[patch] case class Balances(portfolios: Map[String, Portfolio])
  private[patch] object Balances {
    implicit val jsonFormat = Json.format[Balances]
  }
  def apply(): Diff = {
    val patch = PatchLoader.read[Balances](this)
    val addr1 = Address.fromString("3JcQHUDAzkprtFHX8DskQCys3upBYhPeJQq").explicitGet()
    val addr2 = Address.fromString("3JbHxyVNbEEJXMDuuR9kPeTmXn5BCDBSQp4").explicitGet()
    val addr3 = Address.fromString("3JqAYiRnuiJxdMVmdTUsxuTV39LXHR5JWXk").explicitGet()
    val addr4 = Address.fromString("3JtJ5Kf3XuAUiMhtDQasSPdpaG6X5WLa8cE").explicitGet()
    val addr5 = Address.fromString("3JwSzCsBpTZtqxkfdj3KuZgZJ33BcTBnqQr").explicitGet()

    var bal1 = 0L
    var bal2 = 0L
    val pfs = patch.portfolios.map{

      case (addr, p) if Address.fromString(addr).explicitGet() == addr1 && p.balance != 0L=>
        bal1 = p.balance
        Portfolio(-p.balance, LeaseBalance(0L, 0L), Map.empty)

      case (addr, p) if Address.fromString(addr).explicitGet() == addr2 && p.balance != 0L =>
        bal2 = p.balance
        Portfolio(-p.balance, LeaseBalance(0L, 0L), Map.empty)

      case (addr, p) if Address.fromString(addr).explicitGet() == addr3 =>
        Portfolio(bal1 + bal2, p.lease, Map.empty)

      case (addr, p) if Address.fromString(addr).explicitGet() == addr4 =>
        Portfolio(110000000L,p.lease , Map.empty)

      case (addr, p) if Address.fromString(addr).explicitGet() == addr5 =>
        Portfolio(108000000L, p.lease, Map.empty)
    }

    val diff = Diff.empty.copy(portfolios = pfs)
    diff
  }
}

object CancelInvalidTx2 extends DiffPatchFactory {
  val height: Int = AddressScheme.current.chainId.toChar match {
    case 'L' => 457100
  }

  def apply(): Diff = {
    val addr1 = Address.fromString("3JreB3JjQJgFfuz6ntFt76eTDUyv7hxdzMk").explicitGet()
    val patch = PatchLoader.read[CancelledLeases](this)
    val pfs = patch.balances.map {
      case (address, lb)if Address.fromString(address).explicitGet() == addr1 =>
        addr1 -> Portfolio(9593994,lease = lb)
    }
    val diff = Diff.empty.copy(portfolios = pfs)
    diff
  }
}
