package seg3102team3.project.application.usecases

import seg3102team3.project.application.dtos.queries.AgentDto
import java.util.UUID

interface UpdateAgent {
    fun updateAgent(agentID: UUID, updatedAgentInfo: AgentDto): Boolean
}