package seg3102team3.project.adapters.repositories

import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import seg3102team3.project.domain.agent.entities.User
import seg3102team3.project.domain.agent.repositories.UserRepository
import seg3102team3.project.infrastructure.mongo.dao.UserMongoRepository
import java.util.*

@Component
@CacheConfig(cacheNames=["users"])
class UserMongoAdapter(private val userRepository: UserMongoRepository): UserRepository {

    @Cacheable(key = "#id")
    @Transactional
    override fun find(id: UUID): User? {
        return userRepository.findByIdOrNull(id)
    }

    @CachePut(key = "#user.id")
    override fun save(user: User): User {
        return userRepository.save(user)
    }
}