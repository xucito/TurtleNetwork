package com.wavesplatform.it

import com.wavesplatform.state.DataEntry
import com.wavesplatform.it.util._

package object sync {
  val fee                        = 0.02.TN
  val smartFee                   = 0.06.TN
  val issueFee                   = 1000.TN
  val transferAmount             = 10.TN
  val leasingAmount              = transferAmount
  val issueAmount                = transferAmount
  val massTransferFeePerTransfer = 0.005.TN
  val someAssetAmount            = 100000

  def calcDataFee(data: List[DataEntry[_]]): Long = {
    val dataSize = data.map(_.toBytes.length).sum + 128
    if (dataSize > 1024) {
      fee * (dataSize / 1024 + 1)
    } else fee
  }

  def calcMassTransferFee(numberOfRecipients: Int): Long = {
    fee + massTransferFeePerTransfer * (numberOfRecipients + 1)
  }
}
