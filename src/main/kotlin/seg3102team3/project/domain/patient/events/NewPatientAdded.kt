package seg3102team3.project.domain.patient.events

import seg3102team3.project.domain.common.DomainEvent
import java.util.*

class NewPatientAdded(val id: String,
                      val occurredOn: Date): DomainEvent {
}