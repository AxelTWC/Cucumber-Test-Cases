package seg3102team3.project.infrastructure.web.services

import org.mapstruct.factory.Mappers
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import seg3102team3.project.application.dtos.queries.AgentDto
import seg3102team3.project.application.dtos.queries.PatientDto
import seg3102team3.project.application.dtos.queries.PrescriptionFillDto
import seg3102team3.project.application.dtos.queries.converters.PrescriptionFillDtoConverter
import seg3102team3.project.application.usecases.*
import seg3102team3.project.domain.agent.entities.UserRole
import seg3102team3.project.infrastructure.mongo.dao.UserMongoRepository
import seg3102team3.project.infrastructure.web.forms.*
import seg3102team3.project.infrastructure.web.forms.converters.AgentFormDtoConverter
import seg3102team3.project.infrastructure.web.forms.converters.PatientFormDtoConverter
import seg3102team3.project.infrastructure.web.forms.converters.PrescriptionFillFormDtoConverter
import seg3102team3.project.infrastructure.web.forms.converters.PrescriptionFormDtoConverter
import java.util.UUID

@Service
class PharmacyService(private val userRepository: UserMongoRepository,
                      private val accountConverter: AgentFormDtoConverter,
                      private val patientConverter: PatientFormDtoConverter,
                      private val prescriptionConverter: PrescriptionFormDtoConverter,
                      private val prescriptionFillConverter: PrescriptionFillFormDtoConverter,
                      private val createPrescription: CreatePrescription,
                      private val generateDrugReport: GenerateDrugReport,
                      private val pickUpMedicine: PickUpMedicine,
                      private val preparePrescriptionFill: PreparePrescriptionFill,
                      private val registerAgent: RegisterAgent,
                      private val updateAgent: UpdateAgent,
                      private val privilegedUpdateAgent: PrivilegedUpdateAgent,
                      private val unregisterAgent: UnregisterAgent,
                      private val registerPatient: RegisterPatient,
                      private val updatePatient: UpdatePatient
) {

    fun createAccount(accountData: AgentForm): Boolean {
        if(userRepository.existsByUsername(accountData.userName)) return false
        return registerAgent.registerAgent(accountConverter.convertFormAccount(accountData)) != null
    }

    fun getAccount(userid: String): AgentDto? {
        val agent = userRepository.findByUsername(userid);
        return AgentDto(agent.name.firstName + " " + if(agent.name.middleName!="") agent.name.middleName + " " else "" + agent.name.lastName,
                        agent.username, agent.gender.name, agent.language.name, agent.email, agent.password, agent.role.name)
    }

    fun createPrescription(patient: PatientDto, prescriptionData: PrescriptionForm): Boolean {
        val prescription = prescriptionConverter.convertFormPrescription(patient.patientID, prescriptionData)

        val prescId = createPrescription.createPrescription(prescription)
        return prescId != null
    }

    fun pickUpMedicine(pharmacistId: UUID, pickUpSummaryData: PickUpMedicineForm, prescriptionFillId: UUID): Boolean {
        return pickUpMedicine.pickUpMedicine( prescriptionFillId, pharmacistId, pickUpSummaryData.pickUpMedicine)
    }

    fun preparePrescriptionFill(agentId: UUID, prescriptionFillData: PrescriptionFillForm): UUID? {
        val prescriptionFill = prescriptionFillConverter.convertFormPrescriptionFill(UUID.randomUUID(), prescriptionFillData)
        return preparePrescriptionFill.preparePrescriptionFill(prescriptionFill, agentId)
    }

    fun privilegedUpdateAgent(agentId: UUID, agentForm: AgentForm): Boolean {
        return privilegedUpdateAgent.privilegedUpdateAgent(agentId, accountConverter.convertFormAccount(agentForm))
    }

    fun registerAgent(agentForm: AgentForm): UUID? {
        return registerAgent.registerAgent(accountConverter.convertFormAccount(agentForm))
    }

    fun updateAgent(agentId: UUID, agentForm: AgentForm): Boolean {
        return updateAgent.updateAgent(agentId, accountConverter.convertFormAccount(agentForm))
    }

    fun registerPatient(patientForm: PatientForm): String? {
        val patient = patientConverter.convertFormPatient(patientForm)
        return registerPatient.registerPatient(patient)
    }

    fun updatePatient(patientId: String, patientForm: PatientForm): Boolean {
        val patient = patientConverter.convertFormPatient(patientForm)
        return updatePatient.updatePatient(patientId, patient)
    }
}