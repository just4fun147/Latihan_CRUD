package com.techno.springbootdasar.service.impl

import com.techno.springbootdasar.dto.request.CrudDto
import com.techno.springbootdasar.dto.response.JWTSubject
import com.techno.springbootdasar.entity.User
import com.techno.springbootdasar.repository.UserRepository
import com.techno.springbootdasar.service.CrudService
import org.springframework.stereotype.Service

@Service
class CrudServiceImpl(
    val exampleRepository:UserRepository
):CrudService {
    override fun exampleSave(name:String?, username:String?, email:String?, password:String?) {
        val exampleEntity = User(name= name, username = username, email = email, password = password)
        exampleRepository.save(exampleEntity)
    }

    override fun exampleById(id: Long): CrudDto {
        val exampleEntity = exampleRepository.findById(id)
        return CrudDto(
            name = exampleEntity.get().name,
            username = exampleEntity.get().username,
            email = exampleEntity.get().email,
            password = exampleEntity.get().password)
    }
    override fun exampleByIds(id: Long): JWTSubject {
        val exampleEntity = exampleRepository.findById(id)
        return JWTSubject(
            id = id,
            name = exampleEntity.get().name,
            username = exampleEntity.get().username,
            email = exampleEntity.get().email)
    }

    override fun exampleGetAll(): List<CrudDto> {
        val exampleEntities = exampleRepository.findAll()
        val result = mutableListOf<CrudDto>()
        for(example in exampleEntities){
            result.add(
                CrudDto(
                name = example.name,
                username = example.username,
                email = example.email,
                password = example.password,)
            )
        }
        return result
    }

    override fun exampleUpdate(id: Long, name: String?,username:String?, email:String?, password:String?) {
        val exampleEntity = exampleRepository.findById(id)
        val tempEntity = exampleEntity.get().copy(name = name, username = username, email = email, password = password)
        exampleRepository.save(tempEntity)
    }

    override fun exampleDelete(id: Long) {
        exampleRepository.deleteById(id)
    }

    override fun login(username: String?, password: String?):User {
        val user = exampleRepository.findByEmailPassword(username)
        val temp = User(0,"","","","")
        if(password.equals(user!!.password)){
            return user
        }else{
            return temp
        }
    }
}