package io.vinicius.androidcommon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Country
(
    @SerialName("name")
    val name: String,

    @SerialName("capital")
    val capital: String
)