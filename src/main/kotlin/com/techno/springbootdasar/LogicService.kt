package com.techno.springbootdasar

import org.springframework.stereotype.Component

@Component
class LogicService {

    fun oddOrEven(number:Int):String{
        if(number %2 ==0){
            return "Even"
        }else{
            return "Odds"
        }
    }
}