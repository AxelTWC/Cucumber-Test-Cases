package seg3102team3.project.infrastructure.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.core.GrantedAuthorityDefaults
import seg3102team3.project.application.services.DomainEventEmitter
import seg3102team3.project.application.usecases.*
import seg3102team3.project.application.usecases.implementation.*
import seg3102team3.project.domain.agent.facade.AgentFacade
import seg3102team3.project.domain.agent.facade.implementation.AgentFacadeImpl
import seg3102team3.project.domain.agent.factories.UserFactory
import seg3102team3.project.domain.agent.repositories.UserRepository
import seg3102team3.project.domain.drug.facade.DrugFacade
import seg3102team3.project.domain.drug.services.DrugProductDatabaseAdaptor
import seg3102team3.project.domain.patient.facade.PatientFacade
import seg3102team3.project.domain.patient.facade.implementation.DrugFacadeImpl
import seg3102team3.project.domain.patient.facade.implementation.PatientFacadeImpl
import seg3102team3.project.domain.patient.factory.PatientFactory
import seg3102team3.project.domain.patient.factory.PrescriptionFactory
import seg3102team3.project.domain.patient.factory.PrescriptionFillFactory
import seg3102team3.project.domain.patient.repository.PatientRepository
import seg3102team3.project.domain.prescriber.facade.PrescriberFacade
import seg3102team3.project.domain.prescriber.facade.implementation.PrescriberFacadeImpl
import seg3102team3.project.domain.prescriber.services.PrescriberRegistryAdaptor

@Configuration
class BeanConfiguration {
    @Bean
    fun createPrescriptionUseCase(patientFacade: PatientFacade, prescriberFacade: PrescriberFacade, drugFacade: DrugFacade): CreatePrescription{
        return CreatePrescriptionImpl(patientFacade, prescriberFacade, drugFacade)
    }

    @Bean
    fun generateDrugReportUseCase(agentFacade: AgentFacade, drugFacade: DrugFacade): GenerateDrugReport{
        return GenerateDrugReportImpl(agentFacade, drugFacade)
    }

    @Bean
    fun pickUpMedicineUseCase(patientFacade: PatientFacade): PickUpMedicine{
        return PickUpMedicineImpl(patientFacade)
    }

    @Bean
    fun preparePrescriptionFillUseCase(patientFacade: PatientFacade, prescriberFacade: PrescriberFacade, drugFacade: DrugFacade): PreparePrescriptionFill{
        return PreparePrescriptionFillImpl(patientFacade, prescriberFacade, drugFacade)
    }

    @Bean
    fun registerAgentUseCase(agentFacade: AgentFacade): RegisterAgent {
        return RegisterAgentImpl(agentFacade)
    }

    @Bean
    fun updateAgentUseCase(agentFacade: AgentFacade): UpdateAgent {
        return UpdateAgentImpl(agentFacade)
    }

    @Bean
    fun privilegedUpdateAgentUseCase(agentFacade: AgentFacade): PrivilegedUpdateAgent {
        return PrivilegedUpdateAgentImpl(agentFacade)
    }

    @Bean
    fun unregisterAgentUseCase(agentFacade: AgentFacade): UnregisterAgent {
        return UnregisterAgentImpl(agentFacade)
    }

    @Bean
    fun registerPatientUseCase(patientFacade: PatientFacade): RegisterPatient {
        return RegisterPatientImpl(patientFacade)
    }

    @Bean
    fun updatePatientUseCase(patientFacade: PatientFacade): UpdatePatient {
        return UpdatePatientImpl(patientFacade)
    }


    @Bean
    fun agentFacade(userRepository: UserRepository, userFactory: UserFactory, eventEmitter: DomainEventEmitter): AgentFacade{
        return AgentFacadeImpl(userRepository, userFactory, eventEmitter)
    }

    @Bean
    fun patientFacade(patientRepository: PatientRepository, patientFactory: PatientFactory, prescriptionFactory: PrescriptionFactory, prescriptionFillFactory: PrescriptionFillFactory,  eventEmitter: DomainEventEmitter): PatientFacade{
        return PatientFacadeImpl(patientRepository, patientFactory, prescriptionFactory, prescriptionFillFactory, eventEmitter)
    }

    @Bean
    fun prescriberFacade(prescriberRepository: PrescriberRegistryAdaptor): PrescriberFacade{
        return PrescriberFacadeImpl(prescriberRepository)
    }

    @Bean
    fun drugFacade(drugDB: DrugProductDatabaseAdaptor): DrugFacade{
        return DrugFacadeImpl(drugDB)
    }
    
    //Fixes a very subtle bug relating to a "ROLE_" prefix being added at the start of roles when evaluating them
    @Bean
    fun grantedAuthorityDefaults(): GrantedAuthorityDefaults {
        return GrantedAuthorityDefaults("");
    }
}