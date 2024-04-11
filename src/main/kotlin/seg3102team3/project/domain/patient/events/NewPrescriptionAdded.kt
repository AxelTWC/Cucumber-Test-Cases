package seg3102team3.project.domain.patient.events

import seg3102team3.project.domain.common.DomainEvent
import java.util.*

class NewPrescriptionAdded(val id: UUID,
                           val occurredOn: Date): DomainEvent {
}