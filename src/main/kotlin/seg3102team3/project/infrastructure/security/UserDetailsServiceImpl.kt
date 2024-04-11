package seg3102team3.project.infrastructure.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import seg3102team3.project.infrastructure.mongo.dao.UserMongoRepository

@Service
class UserDetailsServiceImpl(val userRepository: UserMongoRepository): UserDetailsService {

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        return build(userRepository.findByUsername(username))
    }
}
