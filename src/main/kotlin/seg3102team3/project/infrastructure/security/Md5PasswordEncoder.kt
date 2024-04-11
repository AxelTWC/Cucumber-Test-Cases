package seg3102team3.project.infrastructure.security

import org.springframework.security.crypto.password.PasswordEncoder
import seg3102team3.project.application.services.HashService

class Md5PasswordEncoder(private var hashService: HashService): PasswordEncoder {
    override fun encode(rawPassword: CharSequence?): String {
        return hashService.md5(rawPassword.toString())
    }

    override fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean {
        return encodedPassword == hashService.md5(rawPassword.toString())
    }
}