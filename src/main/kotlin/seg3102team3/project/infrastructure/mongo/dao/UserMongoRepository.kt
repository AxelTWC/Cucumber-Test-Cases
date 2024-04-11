package seg3102team3.project.infrastructure.mongo.dao

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import seg3102team3.project.domain.agent.entities.User
import java.util.UUID

@Repository
interface UserMongoRepository: MongoRepository<User, UUID>{
    fun existsByUsername(username: String): Boolean
    fun findByUsername(username: String): User
}