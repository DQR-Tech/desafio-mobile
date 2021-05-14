package br.com.dqrtech.currencylayerconverter.model

class ConversionResponse(success: Boolean, error: ErrorResponse, quotes: Map<String, Double>) : BaseResponse(success, error) {
}