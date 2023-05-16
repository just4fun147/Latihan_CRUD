package com.techno.springbootdasar.service

import com.techno.springbootdasar.dto.CrudDto

interface CrudService {

    fun exampleSave(name:String?, username:String?, email:String?, password:String?)
    fun exampleById(id:Long): CrudDto
    fun exampleGetAll(): List<CrudDto>
    fun exampleUpdate(id:Long, name: String?,username:String?, email:String?, password:String?)
    fun exampleDelete(id:Long)

}