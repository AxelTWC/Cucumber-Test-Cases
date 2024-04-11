package seg3102team3.project.domain.agent.facade

import seg3102team3.project.application.dtos.queries.AgentDto
import seg3102team3.project.domain.agent.entities.User
import java.util.*

interface AgentFacade {
    fun addAgent(agentInfo: AgentDto) : UUID
    fun getAgent(agentID: UUID) : User?
    fun updateAgentCredentials(agentID: UUID, email: String, password: String)
    fun updateAgent(agentID: UUID, agentInfo: AgentDto): Boolean
    fun isPharmacist(agentID: UUID): Boolean
    fun unregisterAgent(agentID: UUID): Boolean
}
