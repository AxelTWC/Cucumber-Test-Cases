package seg3102team3.project.domain.agent.facade.implementation

import seg3102team3.project.application.dtos.queries.AgentDto
import seg3102team3.project.domain.agent.repositories.UserRepository
import seg3102team3.project.domain.agent.factories.UserFactory
import seg3102team3.project.application.services.DomainEventEmitter
import seg3102team3.project.domain.agent.entities.User
import seg3102team3.project.domain.agent.entities.UserRole
import seg3102team3.project.domain.agent.events.NewAgentAdded
import seg3102team3.project.domain.agent.events.AgentUpdated
import seg3102team3.project.domain.agent.events.AgentRemoved
import seg3102team3.project.domain.agent.facade.AgentFacade
import java.util.*

class AgentFacadeImpl (
    var userRepository: UserRepository,
    var userFactory: UserFactory,
    var eventEmitter: DomainEventEmitter
    ): AgentFacade {

    override fun addAgent(agentInfo: AgentDto): UUID {
        val agent = userFactory.createAgent(agentInfo)
        userRepository.save(agent)
        eventEmitter.emit(NewAgentAdded(UUID.randomUUID(), Date(), agent.id))
        return agent.id
    }
    
    override fun getAgent(agentID: UUID): User? {
        val agent = userRepository.find(agentID)
        return agent
    }

    override fun updateAgent(agentID: UUID, agentInfo: AgentDto) : Boolean {
        val agent = userRepository.find(agentID)
        if(agent == null) return false

        val tempAgent = userFactory.createAgent(agentInfo)
        agent.name = tempAgent.name
        agent.username = tempAgent.username
        agent.language = tempAgent.language
        agent.role = tempAgent.role
        eventEmitter.emit(AgentUpdated(UUID.randomUUID(), Date(), agent.id))
        return true
    }

    override fun isPharmacist(agentID: UUID): Boolean {
        val agent = userRepository.find(agentID)
        if(agent == null) return false

        return agent.role == UserRole.PHARMACIST
    }

    override fun updateAgentCredentials(agentID: UUID, email: String, password: String) {
        val agent = userRepository.find(agentID)
        if (agent != null) {
            agent.email = email
            agent.password = password
            eventEmitter.emit(AgentUpdated(UUID.randomUUID(), Date(), agent.id))
        }
    }

    override fun unregisterAgent(agentID: UUID): Boolean {
        val agent = userRepository.find(agentID)
        if(agent == null) return false

        agent.deactivate()
        eventEmitter.emit(AgentRemoved(UUID.randomUUID(), Date(), agent.id))
        return true
    }
}
