package seg3102team3.project.domain.agent.repositories

import seg3102team3.project.domain.agent.entities.User
import java.util.*

interface UserRepository {
    fun save(user: User): User
    fun find(id: UUID): User?
}