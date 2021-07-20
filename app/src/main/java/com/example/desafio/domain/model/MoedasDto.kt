package com.example.desafio.domain.model

import com.google.gson.annotations.SerializedName

data class MoedasDto(
    @SerializedName("success")
    val success:Boolean,

    @SerializedName("terms")
    val terms:String,

    @SerializedName("privacy")
    val privacy:String,

    @SerializedName("currencies")
    val moedas: Map<String, String>?
)