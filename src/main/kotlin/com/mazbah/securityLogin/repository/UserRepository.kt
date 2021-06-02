package com.mazbah.securityLogin.repository

import com.mazbah.securityLogin.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Int> {
    fun findByEmail(email: String?): User
}