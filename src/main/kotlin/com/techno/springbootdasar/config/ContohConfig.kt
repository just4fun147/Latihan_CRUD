package com.techno.springbootdasar.config

import com.techno.springbootdasar.LogicService
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class ContohConfig(
    val service : LogicService
) {

    @Bean
    fun printName(name:Config2):String{
        println(name.getName())
        return name.getName()
    }

    @Bean
    fun getOddOrEven(){
        println(service.oddOrEven(5))
    }
}