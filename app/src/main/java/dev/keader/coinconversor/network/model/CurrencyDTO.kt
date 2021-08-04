package dev.keader.coinconversor.network.model

data class CurrencyDTO(
    val success: Boolean,
    val currencies: Map<String, String>
)
