package br.com.dqrtech.currencylayerconverter.data.model

import com.google.gson.annotations.SerializedName

class ConversionResponse(
    success: Boolean,
    error: ErrorResponse?,
    @field:SerializedName("quotes") val conversionRates: LinkedHashMap<String, Double>?
) : BaseResponse(success, error)