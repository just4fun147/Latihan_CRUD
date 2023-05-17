package com.techno.springbootdasar.controller

import com.techno.springbootdasar.dto.BaseResponseDto
import com.techno.springbootdasar.dto.CrudDto
import com.techno.springbootdasar.service.CrudService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.regex.Pattern
import javax.validation.Valid

@RestController
@RequestMapping("v1/api")
@Validated
class CrudController(
    val userService: CrudService
) {

    @PostMapping(
        value = ["/create"],
        produces =["application/json"],
        consumes = ["application/json"]
    )
    fun createExample(@Valid @RequestBody request: CrudDto):ResponseEntity<BaseResponseDto<Any>>{
        userService.exampleSave(request.name, request.username, request.email, request.password)

            return ResponseEntity.ok(
                BaseResponseDto(
                    status = "T",
                    message = "Create User Success",
                    data = ""
                )
            )
    }
    @GetMapping("/{id}")
    fun getExampleById(@PathVariable("id") id:Long):ResponseEntity<BaseResponseDto<Any>>{
        val exampleEntity = userService.exampleById(id)
        return ResponseEntity.ok(
            BaseResponseDto(
                status = "T",
                message = "Get User Success",
                data = exampleEntity
            )
        )
    }

    @GetMapping("/all")
    fun getAllExample() : ResponseEntity<BaseResponseDto<List<CrudDto>>>{
        val listExample = userService.exampleGetAll()
        return ResponseEntity.ok(
            BaseResponseDto(
                status = "T",
                message = "GET ALL User Succes!",
                data = listExample
            )
        )
    }

    @PutMapping("/update")
    fun updateExample(@RequestParam("id") id: Long,@Valid @RequestBody request: CrudDto): ResponseEntity<BaseResponseDto<Any>>{
            userService.exampleUpdate(id,request.name, request.username, request.email,request.password)
            return ResponseEntity.ok(
                BaseResponseDto(
                    status = "T",
                    message = "Update User Succes!",
                    data = ""
                )
            )
    }

    @DeleteMapping("/delete")
    fun deleteExample(@RequestParam("id") id: Long):ResponseEntity<BaseResponseDto<Any>>{
        userService.exampleDelete(id)
        return ResponseEntity.ok(
            BaseResponseDto(
                status = "T",
                message = "Delete User Success!",
                data = ""
            )
        )
    }
}