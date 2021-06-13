package com.mazbah.securityLogin.service

import com.mazbah.securityLogin.model.Role
import com.mazbah.securityLogin.model.User
import com.mazbah.securityLogin.repository.RoleRepository
import com.mazbah.securityLogin.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserService(
    private var roleRepository: RoleRepository, private var userRepository: UserRepository,
    private var bCryptPasswordEncoder: PasswordEncoder
)
{
    fun findUserByEmail(email: String?): User? {
        return userRepository.findByEmail(email)
    }

    fun saveUser(user: User): User {
        user.password = bCryptPasswordEncoder.encode(user.password)
        user.active = true
        val userRole: Role = roleRepository.findByRole("ROLE_USER")
        user.roles = setOf(userRole)
        // user.roles = mutableListOf<Role>(userRole)
        //user.roles(HashSet(Arrays.asList(userRole)))
        return userRepository.save(user)
    }


}