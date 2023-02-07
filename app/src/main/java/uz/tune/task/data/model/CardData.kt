package uz.tune.task.data.model

import java.io.Serializable

data class CardData(
    var date: String = "",
    var cardNumber: String = "",
    var currentTime:Long = 0
) : Serializable