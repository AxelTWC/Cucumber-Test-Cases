package seg3102team3.project.contracts.testStubs.repositories

import seg3102team3.project.domain.agent.entities.User
import seg3102team3.project.domain.agent.repositories.UserRepository
import java.util.*

class UserRepositoryStub : UserRepository{
    private val users: MutableMap<UUID, User> = HashMap()

    override fun save(user: User): User {
        users[user.id] = user
        return user
    }

    override fun find(id: UUID): User? = users[id]
}