package br.com.dqrtech.currencylayerconverter.data.model

class CurrenciesResponse(
    success: Boolean,
    error: ErrorResponse?,
    val currencies: LinkedHashMap<String, String>?
    ) : BaseResponse(success, error)