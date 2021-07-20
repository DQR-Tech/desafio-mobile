package br.com.dqrtech.currencylayerconverter.data.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class ConversionResponse(
    success: Boolean,
    error: ErrorResponse?,
    @field:SerializedName("quotes") val conversionRates: LinkedHashMap<String, BigDecimal>?
) : BaseResponse(success, error)