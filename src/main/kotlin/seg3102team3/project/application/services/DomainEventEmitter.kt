package seg3102team3.project.application.services

import seg3102team3.project.domain.common.DomainEvent

interface DomainEventEmitter {
    fun emit(event: DomainEvent)
}