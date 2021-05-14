package br.com.dqrtech.currencylayerconverter.model

class CurrenciesResponse(success: Boolean, error: ErrorResponse, var currencies: Map<String, String>) : BaseResponse(success, error) {
}