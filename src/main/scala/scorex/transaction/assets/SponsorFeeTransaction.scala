case class SponsorFeeTransaction private (version: Byte,
                                          sender: PublicKeyAccount,
                                          assetId: ByteStr,
                                          minSponsoredAssetFee: Option[Long],
                                          fee: Long,
                                          timestamp: Long,
                                          proofs: Proofs)
    extends ProvenTransaction
    with FastHashId {

  override val builder: SponsorFeeTransaction.type = SponsorFeeTransaction

  val bodyBytes: Coeval[Array[Byte]] = Coeval.evalOnce(
    Bytes.concat(
      Array(builder.typeId),
      Array(version),
      sender.publicKey,
      assetId.arr,
      Longs.toByteArray(minSponsoredAssetFee.getOrElse(0)),
      Longs.toByteArray(fee),
      Longs.toByteArray(timestamp)
    ))

  override val json: Coeval[JsObject] = Coeval.evalOnce(
    jsonBase() ++ Json.obj(
      "version"              -> version,
      "assetId"              -> assetId.base58,
      "minSponsoredAssetFee" -> minSponsoredAssetFee
    ))

  override val assetFee: (Option[AssetId], Long) = (None, fee)

  override val bytes: Coeval[Array[Byte]] = Coeval.evalOnce(Bytes.concat(Array(0: Byte, builder.typeId, version), bodyBytes(), proofs.bytes()))
}
