package dev.keader.coinconversor.network.model

data class ExchangeDTO(
    val success: Boolean,
    val timestamp: Long,
    val source: String,
    val quotes: Map<String, Double>
)
