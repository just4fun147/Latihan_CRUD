package com.techno.springbootdasar.repository

import com.techno.springbootdasar.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository:JpaRepository<User, Long> {
}