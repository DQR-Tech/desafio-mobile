package com.example.desafio.utilis

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JsonService {

    companion object{
        fun fromMoedaMap(map : String) : Map<String, String>{
            val turnsType = object  : TypeToken<Map<String, String>>() {}.type
            return Gson().fromJson(map, turnsType)
        }

        fun fromJson(mapMoedas : Map<String, String>) : String = Gson().toJson(mapMoedas)
    }
}