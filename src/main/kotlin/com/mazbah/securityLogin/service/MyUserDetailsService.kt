package com.mazbah.securityLogin.service

import com.mazbah.securityLogin.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.ArrayList
import kotlin.jvm.Throws

@Service
class MyUserDetailsService(private var userService: UserService): UserDetailsService {

  //  private lateinit var userService: UserService

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val user: User? = userService.findUserByEmail(email)
        val roles: MutableSet<GrantedAuthority> = HashSet()

        for (role in user!!.roles) {
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
