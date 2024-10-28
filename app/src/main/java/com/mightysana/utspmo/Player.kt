package com.mightysana.utspmo
import java.io.Serializable

data class Player(
    val name: String,
    val position: String,
    val imageResId: Int,
    val nationality: String,
    val birthDate: String,
    val height: String,
//    val age: Int,
//    val weight: String,
    val marketValue: Double
) : Serializable

