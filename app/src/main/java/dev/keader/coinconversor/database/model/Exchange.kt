package dev.keader.coinconversor.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exchange(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val code: String,
    val value: Double
)
