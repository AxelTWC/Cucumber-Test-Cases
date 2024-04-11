package seg3102team3.project.application.usecases

import seg3102team3.project.application.dtos.queries.AgentDto
import java.util.UUID

interface RegisterAgent {
    fun registerAgent(newAgentInfo: AgentDto): UUID?
}