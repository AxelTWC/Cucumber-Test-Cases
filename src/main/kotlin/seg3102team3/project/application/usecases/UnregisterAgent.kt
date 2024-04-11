package seg3102team3.project.application.usecases

import java.util.UUID

interface UnregisterAgent {
    fun unregisterAgent(agentID: UUID): Boolean
}