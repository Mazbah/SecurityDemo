package com.mazbah.securityLogin.config

import com.mazbah.securityLogin.model.User
import com.mazbah.securityLogin.service.MyUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User.withUsername
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
class WebSecurityConfig(): WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var userDetailsService: MyUserDetailsService

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(auth:AuthenticationManagerBuilder){
        auth.
            userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder())
//        inMemoryAuthentication()
//            .withUser("springuser@a.com").password(passwordEncoder().encode("spring123")).roles("USER")
//            .and()
//            .withUser("springadmin").password(passwordEncoder().encode("admin123")).roles("ADMIN", "USER");
    }


    override fun configure(http: HttpSecurity) {
        http.
            authorizeRequests()
                .antMatchers("/", "/login", "/register", "/h2-console/**").permitAll()
                .antMatchers("/home").hasAuthority("USER").anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/home")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout();
//            .antMatchers("/", "/login", "/register", "/h2-console/**").hasRole("USER")
//                .antMatchers("/home").hasRole("USER")
//            .anyRequest().authenticated().and().formLogin()


    }

}