package seg3102team3.project.application.usecases.implementation

import seg3102team3.project.application.usecases.UnregisterAgent
import seg3102team3.project.domain.agent.facade.AgentFacade
import java.util.*

class UnregisterAgentImpl(private var agentFacade: AgentFacade):UnregisterAgent {
    override fun unregisterAgent(agentID: UUID): Boolean {
        var success: Boolean = agentFacade.unregisterAgent(agentID)
        return success
    }
}