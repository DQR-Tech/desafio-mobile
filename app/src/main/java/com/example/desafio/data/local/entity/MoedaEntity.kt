package com.example.desafio.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moeda")
class MoedaEntity (

    @PrimaryKey(autoGenerate = true) val id:Long = 0,
    val moedas: String,
    val success:Boolean,
    val terms:String,
    val privacy:String
)