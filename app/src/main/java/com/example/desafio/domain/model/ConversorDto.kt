package com.example.desafio.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ConversorDto (
    @SerializedName("success")
    val success:Boolean,
    @SerializedName("terms")
    val terms:String,
    @SerializedName("privacy")
    val privacy:String,
    @SerializedName("timestamp")
    val timestamp:Integer,
    @SerializedName("source")
    val source:String,
    @SerializedName("quotes")
    val moeda:Map<String, Double>
)