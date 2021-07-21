package com.example.desafio.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversor")
data class ConversorEntity (

    @PrimaryKey(autoGenerate = false) val id:Long = 1,
    val moedas: String,
    val success:Boolean,
    val terms:String,
    val privacy:String,
    val timestamp:Int,
    val source:String
)