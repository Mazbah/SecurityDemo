package com.mazbah.securityLogin.service

import com.mazbah.securityLogin.model.Role
import com.mazbah.securityLogin.model.User
import com.mazbah.securityLogin.repository.RoleRepository
import com.mazbah.securityLogin.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UserService(
    private val roleRepository: RoleRepository, private val userRepository: UserRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
)
{
    fun findUserByEmail(email: String?): User {
        return userRepository.findByEmail(email)
    }

    fun saveUser(user: User): User {
        user.password = bCryptPasswordEncoder.encode(user.password)
        user.active = true
        var userRole: Role = roleRepository.findByRole("USER")
        //user.roles = setOf(userRole)
        // user.roles = mutableListOf<Role>(Arrays.asList(userRole))
      //  user.roles(HashSet(Arrays.asList(userRole)))
        return userRepository.save(user)
    }


}