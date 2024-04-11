package seg3102team3.project.adapters.services.implementation.application

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import seg3102team3.project.application.services.DomainEventEmitter
import seg3102team3.project.domain.common.DomainEvent

@Component
class DomainEventEmitterAdapter: DomainEventEmitter {
    @Autowired
    private lateinit var applicationEventPublisher: ApplicationEventPublisher

    override fun emit(event: DomainEvent){
        applicationEventPublisher.publishEvent(event)
    }
}