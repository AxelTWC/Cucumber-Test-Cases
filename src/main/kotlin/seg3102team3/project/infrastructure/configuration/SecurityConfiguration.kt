package seg3102team3.project.infrastructure.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import seg3102team3.project.application.services.HashService
import seg3102team3.project.infrastructure.security.Md5PasswordEncoder

@Configuration
class SecurityConfiguration(var hashService: HashService) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf()
            .disable()
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/auth/account").hasAnyAuthority("ASSISTANT", "PHARMACIST", "ADMINISTRATOR")
                .antMatchers("/auth/agent/**").hasAuthority("ASSISTANT")
                .antMatchers("/auth/pharmacist/**").hasAuthority("PHARMACIST")
                .antMatchers("/auth/admin/**").hasAuthority("ADMINISTRATOR")
            .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/auth/account")
                .permitAll()
            .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll()
        return http.build()
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring()
                .antMatchers("/resources/**") }
    }

    @Bean
    fun passwordEncoder(): Md5PasswordEncoder? {
        return Md5PasswordEncoder(hashService)
    }
}
