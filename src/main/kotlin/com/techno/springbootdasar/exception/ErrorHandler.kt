package com.techno.springbootdasar.exception

import com.techno.springbootdasar.dto.BaseResponseDto
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleArgumentNotValidException(exception:MethodArgumentNotValidException):ResponseEntity<Any>{
        val errors = mutableListOf<String>()
        exception.bindingResult.fieldErrors.forEach{
            errors.add(it.defaultMessage!!)
        }
        val result = BaseResponseDto(
            "F",
            "Something Went Wrong",
            errors
        )
        return ResponseEntity.badRequest().body(result)
    }

    @ExceptionHandler(CustomExceptionHandler::class)
    fun handleCustomException(exception: SqlExceptionHelper):ResponseEntity<Any>{
        val result = mapOf<String, Any>("status" to "F", "error" to "field", "message" to exception)
        return ResponseEntity.badRequest().body(result)

    }
}