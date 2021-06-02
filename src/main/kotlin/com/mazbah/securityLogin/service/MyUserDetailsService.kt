package com.mazbah.securityLogin.service

import com.mazbah.securityLogin.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import java.util.ArrayList

class MyUserDetailsService: UserDetailsService
{
    @Autowired
    lateinit var userService:UserService

    override fun loadUserByUsername(email: String): UserDetails {
        var user: User = userService.findUserByEmail(email) ?: throw UsernameNotFoundException("User Not Found")

        val roles: MutableSet<GrantedAuthority> = HashSet()
        for (role in user.roles) {
            roles.add(SimpleGrantedAuthority(role.toString()))
        }

        val grantedAuthorities: List<GrantedAuthority> = ArrayList(roles)

        return org.springframework.security.core.userdetails.User(user.email, user.password,
            user.active == true,
            true,
            true,
            true,
            grantedAuthorities)

    }


}