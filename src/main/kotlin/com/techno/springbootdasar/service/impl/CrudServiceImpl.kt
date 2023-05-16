package com.techno.springbootdasar.service.impl

import com.techno.springbootdasar.dto.CrudDto
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
        return CrudDto(name = exampleEntity.get().name)
    }

    override fun exampleGetAll(): List<CrudDto> {
        val exampleEntities = exampleRepository.findAll()
        val result = mutableListOf<CrudDto>()
        for(example in exampleEntities){
            result.add(CrudDto(name = example.name))
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
}