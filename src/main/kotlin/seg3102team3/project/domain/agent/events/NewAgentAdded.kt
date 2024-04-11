package seg3102team3.project.domain.agent.events

import seg3102team3.project.domain.common.DomainEvent
import java.util.*

class NewAgentAdded(
    val id: UUID,
    val occuredOn: Date,
    val userID: UUID
) : DomainEvent