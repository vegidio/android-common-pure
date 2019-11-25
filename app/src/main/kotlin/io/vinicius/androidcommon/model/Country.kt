package io.vinicius.androidcommon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Country
(
    @SerialName("alpha2Code")
    val alpha2Code: String,

    @SerialName("name")
    val name: String,

    @SerialName("capital")
    val capital: String
)