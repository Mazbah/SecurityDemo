package com.mazbah.securityLogin.repository

import com.mazbah.securityLogin.model.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<Role, Int> {
    fun findByRole(role: String): Role
}