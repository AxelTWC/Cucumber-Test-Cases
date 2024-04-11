package seg3102team3.project.application.usecases.implementation

import seg3102team3.project.application.dtos.queries.AgentDto
import seg3102team3.project.application.usecases.RegisterAgent
import seg3102team3.project.domain.agent.facade.AgentFacade
import java.util.*

class RegisterAgentImpl(
    private var agentFacade: AgentFacade):RegisterAgent {
    override fun registerAgent(newAgentInfo: AgentDto): UUID? {
        var uuid: UUID = agentFacade.addAgent(newAgentInfo)
        return uuid
    }
}