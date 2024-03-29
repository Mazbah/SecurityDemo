package com.mazbah.securityLogin.controller

import com.mazbah.securityLogin.model.User
import com.mazbah.securityLogin.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class ViewController(private var userService: UserService) {


    @GetMapping(path = ["/","/login"])
    fun loginPage(): ModelAndView{
        val modelAndView = ModelAndView()
        modelAndView.viewName = "login.html"
        return modelAndView
    }

    @GetMapping(path = ["/register"])
    fun registrationPage():ModelAndView{
        val modelAndView:ModelAndView = ModelAndView()
        modelAndView.addObject("user",User())
        modelAndView.viewName = "register.html"
        return modelAndView
    }

    @PostMapping(path = ["/register"])
    fun createNewUser(user: User, bindingResult: BindingResult,ra: RedirectAttributes): ModelAndView {
        val modelAndView:ModelAndView = ModelAndView()
        val userExists: User? = userService.findUserByEmail(user.email)
        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user", "There is already a user registered with this Email")
        }

        if (!(user.password).equals(user.confirmPassword)) {
            bindingResult.rejectValue("confirmPassword", "error.user", "Password did not matched")
        }

        if (bindingResult.hasErrors()) {
            modelAndView.viewName = "register.html"
        } else {
            userService.saveUser(user)
            ra.addFlashAttribute("successMessage", "User has been registered successfully")
            return ModelAndView("redirect:/login")
        }
        return modelAndView
    }

    @GetMapping(path = ["/home"])
    fun homePage(): ModelAndView {
        val modelAndView:ModelAndView = ModelAndView()
        modelAndView.viewName = "/home.html"
        return modelAndView
    }

}