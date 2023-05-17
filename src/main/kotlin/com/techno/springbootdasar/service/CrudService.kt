package com.techno.springbootdasar.service

import com.techno.springbootdasar.dto.request.CrudDto
import com.techno.springbootdasar.dto.response.JWTSubject
import com.techno.springbootdasar.entity.User

interface CrudService {

    fun exampleSave(name:String?, username:String?, email:String?, password:String?)
    fun exampleById(id:Long): CrudDto
    fun exampleByIds(id:Long): JWTSubject
    fun exampleGetAll(): List<CrudDto>
    fun exampleUpdate(id:Long, name: String?,username:String?, email:String?, password:String?)
    fun exampleDelete(id:Long)
    fun login(username: String?,password: String?):User

}