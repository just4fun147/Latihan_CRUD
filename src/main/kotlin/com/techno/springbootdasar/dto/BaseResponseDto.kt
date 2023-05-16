package com.techno.springbootdasar.dto

data class BaseResponseDto<T>(
    val status:String,
    val message:String,
    val data : T
)
