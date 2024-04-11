package seg3102team3.project.application.usecases.implementation

import seg3102team3.project.application.dtos.queries.AgentDto
import seg3102team3.project.application.usecases.PrivilegedUpdateAgent
import seg3102team3.project.domain.agent.facade.AgentFacade
import java.util.*

class PrivilegedUpdateAgentImpl(
    private var agentFacade: AgentFacade):PrivilegedUpdateAgent {
    override fun privilegedUpdateAgent(agentID: UUID, updatedAgentInfo: AgentDto): Boolean {
        var result: Boolean = agentFacade.updateAgent(agentID, updatedAgentInfo)
        return result
    }
}