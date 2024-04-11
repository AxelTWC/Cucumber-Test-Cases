package seg3102team3.project.domain.agent.events

import seg3102team3.project.domain.common.DomainEvent
import java.util.*

class AgentRemoved(
    val id: UUID,
    val occurredOn: Date,
    val userID: UUID
): DomainEvent