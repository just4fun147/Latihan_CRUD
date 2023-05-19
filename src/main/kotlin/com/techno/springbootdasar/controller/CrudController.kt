package com.techno.springbootdasar.controller

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.techno.springbootdasar.dto.response.BaseResponseDto
import com.techno.springbootdasar.dto.request.CrudDto
import com.techno.springbootdasar.dto.request.LoginDto
import com.techno.springbootdasar.dto.request.ReqDecodeJWT
import com.techno.springbootdasar.dto.response.JWTSubject
import com.techno.springbootdasar.service.CrudService
import com.techno.springjwt.util.JWTGenerator
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.io.FileReader
import javax.servlet.http.HttpServletRequest
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

    @PostMapping(
        value = ["/login"],
        produces =["application/json"],
        consumes = ["application/json"]
    )
    fun loginUser(@Valid @RequestBody request: LoginDto):ResponseEntity<BaseResponseDto<Any>>{
        val result = userService.login(request.username,request.password)
        if(result.id!!.toInt()!=0){
//            val token = JWTGenerator().createJWT(result.id, result.name!!, result.username!!,result.email!!)
            val token = JWTGenerator().createJWT(result.id, result.id)
            return ResponseEntity.ok(
                BaseResponseDto(
                    status = "T",
                    message = "Login Success",
                    data = token
                )
            )
        }else{
            return ResponseEntity.ok(
                BaseResponseDto(
                    status = "F",
                    message = "Invalid Username or Password",
                    data = ""
                )
            )
        }
    }

    @PostMapping("/validateLogin")
    fun decodeJWT(request: HttpServletRequest): ResponseEntity<BaseResponseDto<Any>>{
        val token = request.getHeader("authToken")
        val claim = JWTGenerator().decodeJWT(token)
        val result = userService.exampleByIds(claim)
        return ResponseEntity.ok(BaseResponseDto(
            status = "T",
            message = "Success Decode JWT",
            data = result
        ))
    }
}