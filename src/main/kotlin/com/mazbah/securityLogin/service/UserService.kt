package com.mazbah.securityLogin.service

import com.mazbah.securityLogin.model.Role
import com.mazbah.securityLogin.model.User
import com.mazbah.securityLogin.repository.RoleRepository
import com.mazbah.securityLogin.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserService(
    private val roleRepository: RoleRepository, private val userRepository: UserRepository,
    private val bCryptPasswordEncoder: PasswordEncoder
)
{
    fun findUserByEmail(email: String?): User {
        return userRepository.findByEmail(email)
    }

    fun saveUser(user: User): User {
        user.password = bCryptPasswordEncoder.encode(user.password)
        user.active = true
        var userRole: Role = roleRepository.findByRole("USER")
       // user.roles = listOf(userRole)
        // user.roles = mutableListOf<Role>(userRole)
        //user.roles(HashSet(Arrays.asList(userRole)))
        return userRepository.save(user)
    }


}