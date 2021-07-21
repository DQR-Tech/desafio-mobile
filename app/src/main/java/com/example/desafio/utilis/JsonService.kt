package com.example.desafio.utilis

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JsonService {

    companion object{
        fun fromMoedaMap(map : String) : MutableMap<String, String>{
            val turnsType = object  : TypeToken<Map<String, String>>() {}.type
            return Gson().fromJson(map, turnsType)
        }

        fun fromJsonMoeda(mapMoedas : MutableMap<String, String>) : String = Gson().toJson(mapMoedas)


        fun fromJsonConversor(map : String) : MutableMap<String, Double>{
            val turnsType = object  : TypeToken<Map<String, Double>>() {}.type
            return Gson().fromJson(map, turnsType)
        }

        fun fromJsonConversor(mapMoedas : MutableMap<String, Double>) : String = Gson().toJson(mapMoedas)
    }
}