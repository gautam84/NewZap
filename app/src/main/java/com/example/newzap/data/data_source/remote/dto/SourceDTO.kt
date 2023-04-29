package com.example.newzap.data.data_source.remote.dto

import com.google.gson.annotations.SerializedName

data class SourceDTO(
    @SerializedName("name")
    var sourceName: String = String(),
    @SerializedName("id")
    var sourceId: String = String()
)