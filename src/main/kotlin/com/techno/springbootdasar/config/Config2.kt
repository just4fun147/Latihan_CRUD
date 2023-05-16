package com.techno.springbootdasar.config

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class Config2 {

    @Bean
    fun getName() : String{
        return "Pandu"
    }
}