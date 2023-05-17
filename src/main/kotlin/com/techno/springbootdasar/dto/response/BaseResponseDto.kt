package com.techno.springbootdasar.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class BaseResponseDto<T>(
    @field:JsonProperty("OUT_STAT")
    val status:String,
    @field:JsonProperty("OUT_MESS")
    val message:String,
    @field:JsonProperty("OUT_DATA")
    val data : T
)
