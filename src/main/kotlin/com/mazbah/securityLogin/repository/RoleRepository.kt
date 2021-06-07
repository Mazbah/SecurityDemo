package com.mazbah.securityLogin.repository

import com.mazbah.securityLogin.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository: JpaRepository<Role, Int> {
    fun findByRole(role: String): Role
}