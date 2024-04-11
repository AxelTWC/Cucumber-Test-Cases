package seg3102team3.project.domain.agent.factories

import seg3102team3.project.application.dtos.queries.AgentDto
import seg3102team3.project.domain.agent.entities.User

interface UserFactory {
    fun createAgent(agentInfo: AgentDto): User
}