package com.mazbah.securityLogin.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.security.RolesAllowed




@RestController
class LogicController {

    @GetMapping("/*")
    fun getUser():String{
        return "Welcome User"
    }

    //@RolesAllowed("USER", "ADMIN")
    @GetMapping("/admin")
    fun getAdmin(): String {
        return "Welcome Admin"
    }
}