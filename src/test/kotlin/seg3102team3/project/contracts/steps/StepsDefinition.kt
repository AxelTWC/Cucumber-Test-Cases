package seg3102team3.project.contracts.steps


import org.assertj.core.api.Assertions
import io.cucumber.java8.En
import io.cucumber.java8.Scenario
import org.mockito.MockitoAnnotations
import seg3102team3.project.application.dtos.queries.AgentDto
import seg3102team3.project.application.dtos.queries.PatientDto
import seg3102team3.project.application.dtos.queries.PrescriptionDto
import seg3102team3.project.application.dtos.queries.PrescriptionFillDto
import seg3102team3.project.application.usecases.*
import seg3102team3.project.application.usecases.implementation.*
import seg3102team3.project.contracts.testStubs.factories.PatientFactoryStub
import seg3102team3.project.contracts.testStubs.factories.PrescriptionFactoryStub
import seg3102team3.project.contracts.testStubs.factories.PrescriptionFillFactoryStub
import seg3102team3.project.contracts.testStubs.factories.UserFactoryStub
import seg3102team3.project.contracts.testStubs.repositories.PatientRepositoryStub
import seg3102team3.project.contracts.testStubs.repositories.UserRepositoryStub
import seg3102team3.project.contracts.testStubs.services.DrugProductDatabaseAdaptorStub
import seg3102team3.project.contracts.testStubs.services.EventEmitterStub
import seg3102team3.project.contracts.testStubs.services.HashServiceImpl
import seg3102team3.project.contracts.testStubs.services.PrescriberRegistryAdaptorStub
import seg3102team3.project.domain.agent.entities.User
import seg3102team3.project.domain.agent.entities.UserRole
import seg3102team3.project.domain.agent.facade.implementation.AgentFacadeImpl
import seg3102team3.project.domain.common.Address
import seg3102team3.project.domain.common.Gender
import seg3102team3.project.domain.common.LanguagePreference
import seg3102team3.project.domain.common.Name
import seg3102team3.project.domain.drug.entities.Drug
import seg3102team3.project.domain.patient.entities.*
import seg3102team3.project.domain.patient.facade.implementation.DrugFacadeImpl
import seg3102team3.project.domain.patient.facade.implementation.PatientFacadeImpl
import seg3102team3.project.domain.prescriber.facade.implementation.PrescriberFacadeImpl
import java.util.*

class StepsDefinition: En {
    private var patientRepository = PatientRepositoryStub()
    private var userRepository = UserRepositoryStub()

    private var prescriberRegistryAdaptor = PrescriberRegistryAdaptorStub()
    private var drugDatabaseAdaptor = DrugProductDatabaseAdaptorStub()
    private var eventEmitter = EventEmitterStub()
    private var hashService = HashServiceImpl()

    private var patientFactory = PatientFactoryStub()
    private var prescriptionFactory = PrescriptionFactoryStub()
    private var prescriptionFillFactory = PrescriptionFillFactoryStub()
    private var userFactory = UserFactoryStub(hashService)

    private var patientInfo: PatientDto? = null
    private var prescriptionInfo: PrescriptionDto? = null
    private var prescriptionFillInfo: PrescriptionFillDto? = null
    private var agentInfo: AgentDto? = null

    private var patientID: String? = null
    private var prescriberID: String? = null
    private var prescriptionFillID: UUID? = null
    private var userID: UUID? = null
    private var identifier: String? = null
    private var verification: Boolean? = null
    private var pickUpSummary: String? = null
    private var clinicalCheck: String? = null

    private var newPatientID: String? = null
    private var newUserID: UUID? = null
    private var newPrescriptionID: UUID? = null
    private var newPrescriptionFillID: UUID? = null
    private var success: Boolean? = null
    private var fetchedContactInfo: String? = null
    private var fetchedPrescriptionDocs: MutableList<ByteArray>? = null
    private var verifiedDrugID: UInt? = null
    private var verifiedPatientID: String? = null
    private var verifiedPrescriberID: String? = null

    private var initialUserRole: UserRole? = null
    private var initialRefillCount: UShort? = null

    init {
        Before { _: Scenario ->
            MockitoAnnotations.openMocks(this)
        }

        Given("the prescription info's patient exists") {
            Assertions.assertThat(prescriptionInfo).isNotNull
            Assertions.assertThat(patientRepository.find(prescriptionInfo!!.patientID)).isNotNull
        }
        Given("the prescription info's prescriber exists") {
            Assertions.assertThat(prescriptionInfo).isNotNull
            Assertions.assertThat(prescriberRegistryAdaptor.getPrescriberByLicenseNumber(prescriptionInfo!!.prescriberLicenseID)).isNotNull
        }
        Given("the prescription info's drug exists") {
            Assertions.assertThat(prescriptionInfo).isNotNull
            Assertions.assertThat(drugDatabaseAdaptor.getDrugByDIN(prescriptionInfo!!.DIN)).isNotNull
        }
        Given("the prescription info's admin method matches to an AdministrationMethod value") {
            Assertions.assertThat(prescriptionInfo).isNotNull
            Assertions.assertThat(enumValues<AdministrationMethod>().any { it.name == prescriptionInfo!!.adminMethod.uppercase().replace(' ', '_') }).isTrue
        }
        Given("the prescription info's refillable status matches to a RefillableStatus value") {
            Assertions.assertThat(prescriptionInfo).isNotNull
            Assertions.assertThat(prescriptionInfo!!.refillable >= 0u && prescriptionInfo!!.refillable <= 2u).isTrue
        }
        Given("the given prescriber exists") {
            Assertions.assertThat(prescriberID).isNotNull
            Assertions.assertThat(prescriberRegistryAdaptor.getPrescriberByLicenseNumber(prescriberID!!)).isNotNull
        }
        Given("the Prescription Fill specified by the given ID exists"){
            Assertions.assertThat(prescriptionFillID).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionFillID(prescriptionFillID!!)).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionFillID(prescriptionFillID!!)!!.getPrescriptionFill(prescriptionFillID!!)).isNotNull //redundant check
        }
        Given("the status of the Prescription Fill specified by the given ID is verified"){
            Assertions.assertThat(prescriptionFillID).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionFillID(prescriptionFillID!!)).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionFillID(prescriptionFillID!!)!!.getPrescriptionFill(prescriptionFillID!!)).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionFillID(prescriptionFillID!!)!!.getPrescriptionFill(prescriptionFillID!!)!!.status
                    == PrescriptionFillStatus.VERIFIED).isTrue
        }
        Given("the prescription fill info's prescription exists"){
            Assertions.assertThat(prescriptionFillInfo).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionID(prescriptionFillInfo!!.prescriptionID)).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionID(prescriptionFillInfo!!.prescriptionID)!!.getPrescription(prescriptionFillInfo!!.prescriptionID)).isNotNull //redundant check
        }
        Given("the prescription fill info's prescription is non-refillable") {
            Assertions.assertThat(prescriptionFillInfo).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionID(prescriptionFillInfo!!.prescriptionID)).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionID(prescriptionFillInfo!!.prescriptionID)!!.getPrescription(prescriptionFillInfo!!.prescriptionID)!!.refillable == RefillableStatus.NON_REFILLABLE).isTrue
        }
        Given("the prescription fill info's prescription is not non-refillable") {
            Assertions.assertThat(prescriptionFillInfo).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionID(prescriptionFillInfo!!.prescriptionID)).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionID(prescriptionFillInfo!!.prescriptionID)!!.getPrescription(prescriptionFillInfo!!.prescriptionID)!!.refillable == RefillableStatus.NON_REFILLABLE).isFalse
        }
        Given("the prescription fill info's prescription's refill count is above 0") {
            Assertions.assertThat(prescriptionFillInfo).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionID(prescriptionFillInfo!!.prescriptionID)).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionID(prescriptionFillInfo!!.prescriptionID)!!.getPrescription(prescriptionFillInfo!!.prescriptionID)!!.refillCount).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionID(prescriptionFillInfo!!.prescriptionID)!!.getPrescription(prescriptionFillInfo!!.prescriptionID)!!.refillCount!! > 0u).isTrue
        }
        Given("the prescription fill info's prescription's refill count is under 1") {
            Assertions.assertThat(prescriptionFillInfo).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionID(prescriptionFillInfo!!.prescriptionID)).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionID(prescriptionFillInfo!!.prescriptionID)!!.getPrescription(prescriptionFillInfo!!.prescriptionID)!!.refillCount).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionID(prescriptionFillInfo!!.prescriptionID)!!.getPrescription(prescriptionFillInfo!!.prescriptionID)!!.refillCount!! < 0u).isTrue
        }
        Given("the agent info's language preference matches to a Language value"){
            Assertions.assertThat(agentInfo).isNotNull
            Assertions.assertThat(enumValues<LanguagePreference>().any { it.name == agentInfo!!.languagePref.uppercase() }).isTrue
        }
        Given("the agent info's gender matches to a Gender value"){
            Assertions.assertThat(agentInfo).isNotNull
            Assertions.assertThat(enumValues<LanguagePreference>().any { it.name == agentInfo!!.gender.uppercase().replace(' ', '_') }).isTrue
        }
        Given("the patient info's language preference matches to a Language value") {
            Assertions.assertThat(patientInfo).isNotNull
            Assertions.assertThat(enumValues<LanguagePreference>().any { it.name == patientInfo!!.languagePref.uppercase() }).isTrue
        }
        Given("the patient info's gender matches to a Gender value") {
            Assertions.assertThat(patientInfo).isNotNull
            Assertions.assertThat(enumValues<LanguagePreference>().any { it.name == patientInfo!!.gender.uppercase().replace(' ', '_') }).isTrue
        }
        Given("the patient info's address contains 5 comma seperated substrings") {
            Assertions.assertThat(patientInfo).isNotNull
            Assertions.assertThat(patientInfo!!.fullAddress.split(", ").size == 5).isTrue
        }
        Given("the user account specified by the given ID is registered") {
            Assertions.assertThat(userID).isNotNull
            Assertions.assertThat(userRepository.find(userID!!)).isNotNull
        }
        Given("the user account specified by the given ID is an Agent") {
            Assertions.assertThat(userID).isNotNull
            Assertions.assertThat(userRepository.find(userID!!)).isNotNull
            Assertions.assertThat(userRepository.find(userID!!)!!.role == UserRole.ADMINISTRATOR).isFalse
        }
        Given("the user account specified by the given ID is active") {
            Assertions.assertThat(userID).isNotNull
            Assertions.assertThat(userRepository.find(userID!!)).isNotNull
            Assertions.assertThat(userRepository.find(userID!!)!!.activated).isTrue
        }
        Given("the Patient specified by the given ID exists") {
            Assertions.assertThat(patientID).isNotNull
            Assertions.assertThat(patientRepository.find(patientID!!)).isNotNull
        }
        Given("the given identifier matches a known Drug's name") {
            Assertions.assertThat(identifier).isNotNull
            Assertions.assertThat(drugDatabaseAdaptor.getDrugByName(identifier!!)).isNotNull
        }
        Given("the given identifier matches a known Drug's ATC") {
            Assertions.assertThat(identifier).isNotNull
            Assertions.assertThat(drugDatabaseAdaptor.getDrugByATC(identifier!!)).isNotNull
        }
        Given("the given identifier matches a known Drug's DIN") {
            Assertions.assertThat(identifier).isNotNull
            Assertions.assertThat(drugDatabaseAdaptor.getDrugByDIN(identifier!!.toUInt())).isNotNull
        }
        Given("the given identifier does not match a known Drug's name, ATC or DIN") {
            Assertions.assertThat(identifier).isNotNull
            Assertions.assertThat(drugDatabaseAdaptor.getDrugByName(identifier!!)).isNull()
            Assertions.assertThat(drugDatabaseAdaptor.getDrugByATC(identifier!!)).isNull()
            Assertions.assertThat(drugDatabaseAdaptor.getDrugByDIN(identifier!!.toUInt())).isNull()
        }
        Given("the given identifier matches an existing Patient's name") {
            Assertions.assertThat(identifier).isNotNull
            Assertions.assertThat(patientRepository.findByName(identifier!!)).isNotNull
        }
        Given("the given identifier matches an existing Patient's email") {
            Assertions.assertThat(identifier).isNotNull
            Assertions.assertThat(patientRepository.findByEmail(identifier!!)).isNotNull
        }
        Given("the given identifier matches an existing Patient's PHIN") {
            Assertions.assertThat(identifier).isNotNull
            Assertions.assertThat(patientRepository.find(identifier!!)).isNotNull
        }
        Given("the given identifier does not match an existing Patient's name, email or PHIN") {
            Assertions.assertThat(identifier).isNotNull
            Assertions.assertThat(patientRepository.findByName(identifier!!)).isNull()
            Assertions.assertThat(patientRepository.findByEmail(identifier!!)).isNull()
            Assertions.assertThat(patientRepository.find(identifier!!)).isNull()
        }
        Given("the given identifier matches a known Prescriber's name") {
            Assertions.assertThat(identifier).isNotNull
            Assertions.assertThat(prescriberRegistryAdaptor.getPrescriberByName(identifier!!)).isNotNull
        }
        Given("the given identifier matches a known Prescriber's email") {
            Assertions.assertThat(identifier).isNotNull
            Assertions.assertThat(prescriberRegistryAdaptor.getPrescriberByEmail(identifier!!)).isNotNull
        }
        Given("the given identifier matches a known Prescriber's license number") {
            Assertions.assertThat(identifier).isNotNull
            Assertions.assertThat(prescriberRegistryAdaptor.getPrescriberByLicenseNumber(identifier!!)).isNotNull
        }
        Given("the given identifier does not match a known Prescriber's name, email or license number") {
            Assertions.assertThat(identifier).isNotNull
            Assertions.assertThat(prescriberRegistryAdaptor.getPrescriberByName(identifier!!)).isNull()
            Assertions.assertThat(prescriberRegistryAdaptor.getPrescriberByEmail(identifier!!)).isNull()
            Assertions.assertThat(prescriberRegistryAdaptor.getPrescriberByLicenseNumber(identifier!!)).isNull()
        }
        Given("the status of the Prescription Fill specified by the given ID is prepared") {
            Assertions.assertThat(prescriptionFillID).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionFillID(prescriptionFillID!!)).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionFillID(prescriptionFillID!!)!!.getPrescriptionFill(prescriptionFillID!!)).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionFillID(prescriptionFillID!!)!!.getPrescriptionFill(prescriptionFillID!!)!!.status
                    == PrescriptionFillStatus.PREPARED).isTrue
        }
        Given("the user account specified by the given ID is a Pharmacist") {
            Assertions.assertThat(userID).isNotNull
            Assertions.assertThat(userRepository.find(userID!!)).isNotNull
            Assertions.assertThat(userRepository.find(userID!!)!!.role == UserRole.PHARMACIST).isTrue
        }
        Given("the assessment was positive") {
            Assertions.assertThat(verification).isTrue
        }
        Given("the assessment was negative") {
            Assertions.assertThat(verification).isFalse
        }





        When("application command registerPatient is invoked") {
            val patientFacade = PatientFacadeImpl(patientRepository, patientFactory, prescriptionFactory, prescriptionFillFactory, eventEmitter)
            val uc: RegisterPatient = RegisterPatientImpl(patientFacade)
            newPatientID = uc.registerPatient(patientInfo!!)
        }
        When("application command unregisterAgent is invoked") {
            val agentFacade = AgentFacadeImpl(userRepository, userFactory, eventEmitter)
            val uc: UnregisterAgent = UnregisterAgentImpl(agentFacade)
            success = uc.unregisterAgent(userID!!)
        }
        When("the application command updateAgent is invoked") {
            initialUserRole = userRepository.find(userID!!)!!.role
            val agentFacade = AgentFacadeImpl(userRepository, userFactory, eventEmitter)
            val uc: UpdateAgent = UpdateAgentImpl(agentFacade)
            success = uc.updateAgent(userID!!, agentInfo!!)
        }
        When("the application command updatePatient is invoked") {
            val patientFacade = PatientFacadeImpl(patientRepository, patientFactory, prescriptionFactory, prescriptionFillFactory, eventEmitter)
            val uc: UpdatePatient = UpdatePatientImpl(patientFacade)
            success = uc.updatePatient(patientID!!, patientInfo!!)
        }
        When("application command registerAgent is invoked") {
            val agentFacade = AgentFacadeImpl(userRepository, userFactory, eventEmitter)
            val uc: RegisterAgent = RegisterAgentImpl(agentFacade)
            newUserID = uc.registerAgent(agentInfo!!)
        }
        When("the application command privilegedUpdateAgent is invoked") {
            val agentFacade = AgentFacadeImpl(userRepository, userFactory, eventEmitter)
            val uc: PrivilegedUpdateAgent = PrivilegedUpdateAgentImpl(agentFacade)
            success = uc.privilegedUpdateAgent(userID!!, agentInfo!!)
        }
        When("application command pickUpPrescriptionFill is invoked") {
            val patientFacade = PatientFacadeImpl(patientRepository, patientFactory, prescriptionFactory, prescriptionFillFactory, eventEmitter)
            val uc: PickUpMedicine = PickUpMedicineImpl(patientFacade)
            success = uc.pickUpMedicine(prescriptionFillID!!, userID!!, pickUpSummary!!)
        }
        When("application command preparePrescriptionFill is invoked") {
            initialRefillCount = patientRepository.findByPrescriptionID(prescriptionFillInfo!!.prescriptionID)!!.getPrescription(prescriptionFillInfo!!.prescriptionID)!!.refillCount
            val patientFacade = PatientFacadeImpl(patientRepository, patientFactory, prescriptionFactory, prescriptionFillFactory, eventEmitter)
            val prescriberFacade = PrescriberFacadeImpl(prescriberRegistryAdaptor)
            val drugFacade = DrugFacadeImpl(drugDatabaseAdaptor)
            val uc: PreparePrescriptionFill = PreparePrescriptionFillImpl(patientFacade, prescriberFacade, drugFacade)
            newPrescriptionFillID = uc.preparePrescriptionFill(prescriptionFillInfo!!, userID!!)
        }
        When("application command verifyPrescriptionFill is invoked") {
            val patientFacade = PatientFacadeImpl(patientRepository, patientFactory, prescriptionFactory, prescriptionFillFactory, eventEmitter)
            val prescriberFacade = PrescriberFacadeImpl(prescriberRegistryAdaptor)
            val drugFacade = DrugFacadeImpl(drugDatabaseAdaptor)
            val uc: PreparePrescriptionFill = PreparePrescriptionFillImpl(patientFacade, prescriberFacade, drugFacade)
            success = uc.verifyPrescriptionFill(prescriptionFillID!!, userID!!, clinicalCheck!!, verification!!)
        }
        When("application command fetchPrescriptionDocs is invoked") {
            val patientFacade = PatientFacadeImpl(patientRepository, patientFactory, prescriptionFactory, prescriptionFillFactory, eventEmitter)
            val prescriberFacade = PrescriberFacadeImpl(prescriberRegistryAdaptor)
            val drugFacade = DrugFacadeImpl(drugDatabaseAdaptor)
            val uc: PreparePrescriptionFill = PreparePrescriptionFillImpl(patientFacade, prescriberFacade, drugFacade)
            fetchedPrescriptionDocs = uc.fetchPrescriptionDocs(prescriptionFillID!!)
        }
        When("application command fetchPrescriberContactInfo is invoked") {
            val patientFacade = PatientFacadeImpl(patientRepository, patientFactory, prescriptionFactory, prescriptionFillFactory, eventEmitter)
            val prescriberFacade = PrescriberFacadeImpl(prescriberRegistryAdaptor)
            val drugFacade = DrugFacadeImpl(drugDatabaseAdaptor)
            val uc: PreparePrescriptionFill = PreparePrescriptionFillImpl(patientFacade, prescriberFacade, drugFacade)
            fetchedContactInfo = uc.fetchPrescriberContactInfo(prescriberID!!)
        }
        When("application command verifyDrugInfo is invoked") {
            val patientFacade = PatientFacadeImpl(patientRepository, patientFactory, prescriptionFactory, prescriptionFillFactory, eventEmitter)
            val prescriberFacade = PrescriberFacadeImpl(prescriberRegistryAdaptor)
            val drugFacade = DrugFacadeImpl(drugDatabaseAdaptor)
            val uc: CreatePrescription = CreatePrescriptionImpl(patientFacade, prescriberFacade, drugFacade)
            verifiedDrugID = uc.verifyDrugInfo(identifier!!)
        }
        When("application command verifyPatientInfo is invoked") {
            val patientFacade = PatientFacadeImpl(patientRepository, patientFactory, prescriptionFactory, prescriptionFillFactory, eventEmitter)
            val prescriberFacade = PrescriberFacadeImpl(prescriberRegistryAdaptor)
            val drugFacade = DrugFacadeImpl(drugDatabaseAdaptor)
            val uc: CreatePrescription = CreatePrescriptionImpl(patientFacade, prescriberFacade, drugFacade)
            verifiedPatientID = uc.verifyPatientInfo(identifier!!)
        }
        When("application command verifyPrescriberInfo is invoked") {
            val patientFacade = PatientFacadeImpl(patientRepository, patientFactory, prescriptionFactory, prescriptionFillFactory, eventEmitter)
            val prescriberFacade = PrescriberFacadeImpl(prescriberRegistryAdaptor)
            val drugFacade = DrugFacadeImpl(drugDatabaseAdaptor)
            val uc: CreatePrescription = CreatePrescriptionImpl(patientFacade, prescriberFacade, drugFacade)
            verifiedPrescriberID = uc.verifyPrescriberInfo(identifier!!)
        }
        When("application command createPrescription is invoked") {
            val patientFacade = PatientFacadeImpl(patientRepository, patientFactory, prescriptionFactory, prescriptionFillFactory, eventEmitter)
            val prescriberFacade = PrescriberFacadeImpl(prescriberRegistryAdaptor)
            val drugFacade = DrugFacadeImpl(drugDatabaseAdaptor)
            val uc: CreatePrescription = CreatePrescriptionImpl(patientFacade, prescriberFacade, drugFacade)
            newPrescriptionID = uc.createPrescription(prescriptionInfo!!)
        }




        Then("the operation is carried out successfully") {
            Assertions.assertThat(success).isNotNull
            Assertions.assertThat(success!!).isTrue
        }
        Then("the user account is marked as deactivated") {
            Assertions.assertThat(userRepository.find(userID!!)!!.activated).isFalse
        }
        Then("a new patient is created") {
            Assertions.assertThat(newPatientID).isNotNull
            Assertions.assertThat(patientRepository.find(newPatientID!!)).isNotNull
        }
        Then("the new patient is initialized from the patient information") {
            val newPatient: Patient = patientRepository.find(newPatientID!!)!!
            Assertions.assertThat(newPatient.dateOfBirth).isEqualTo(patientInfo!!.dateOfBirth)
            Assertions.assertThat(newPatient.email).isEqualTo(patientInfo!!.email)
            Assertions.assertThat(newPatient.phoneNumber).isEqualTo(patientInfo!!.phoneNumber)
            Assertions.assertThat(newPatient.healthHistoryNote).isEqualTo(patientInfo!!.healthHistoryNote)
            Assertions.assertThat(newPatient.prescriptionMeds).isEqualTo(patientInfo!!.prescriptionMeds)
            Assertions.assertThat(newPatient.nonPrescriptionMeds).isEqualTo(patientInfo!!.nonPrescriptionMeds)
            Assertions.assertThat(newPatient.insuranceNumber).isEqualTo(patientInfo!!.insuranceNumber)
            Assertions.assertThat(newPatient.fullName).isEqualTo(
                Name(
                    patientInfo!!.fullName.substring(0, patientInfo!!.fullName.indexOf(' ')),
                    patientInfo!!.fullName.substring(patientInfo!!.fullName.indexOf(' ')+1, patientInfo!!.fullName.lastIndexOf(' ')),
                    patientInfo!!.fullName.substring(patientInfo!!.fullName.lastIndexOf(' ')+1),
                ))
            Assertions.assertThat(newPatient.languagePref).isEqualTo(LanguagePreference.valueOf(patientInfo!!.languagePref.uppercase()))
            Assertions.assertThat(newPatient.gender).isEqualTo(Gender.valueOf(patientInfo!!.gender.uppercase().replace(' ', '_')))
            val addressComponents = patientInfo!!.fullAddress.split(", ").toTypedArray()
            Assertions.assertThat(newPatient.address).isEqualTo(Address(addressComponents[0].toUInt(), addressComponents[1], addressComponents[2], addressComponents[3], addressComponents[4]))
        }
        Then("the patient is updated from the patient information") {
            val updatedPatient: Patient = patientRepository.find(patientID!!)!!
            Assertions.assertThat(updatedPatient.dateOfBirth).isEqualTo(patientInfo!!.dateOfBirth)
            Assertions.assertThat(updatedPatient.email).isEqualTo(patientInfo!!.email)
            Assertions.assertThat(updatedPatient.phoneNumber).isEqualTo(patientInfo!!.phoneNumber)
            Assertions.assertThat(updatedPatient.healthHistoryNote).isEqualTo(patientInfo!!.healthHistoryNote)
            Assertions.assertThat(updatedPatient.prescriptionMeds).isEqualTo(patientInfo!!.prescriptionMeds)
            Assertions.assertThat(updatedPatient.nonPrescriptionMeds).isEqualTo(patientInfo!!.nonPrescriptionMeds)
            Assertions.assertThat(updatedPatient.insuranceNumber).isEqualTo(patientInfo!!.insuranceNumber)
            Assertions.assertThat(updatedPatient.fullName).isEqualTo(
                Name(
                    patientInfo!!.fullName.substring(0, patientInfo!!.fullName.indexOf(' ')),
                    patientInfo!!.fullName.substring(patientInfo!!.fullName.indexOf(' ')+1, patientInfo!!.fullName.lastIndexOf(' ')),
                    patientInfo!!.fullName.substring(patientInfo!!.fullName.lastIndexOf(' ')+1),
                ))
            Assertions.assertThat(updatedPatient.languagePref).isEqualTo(LanguagePreference.valueOf(patientInfo!!.languagePref.uppercase()))
            Assertions.assertThat(updatedPatient.gender).isEqualTo(Gender.valueOf(patientInfo!!.gender.uppercase().replace(' ', '_')))
            val addressComponents = patientInfo!!.fullAddress.split(", ").toTypedArray()
            Assertions.assertThat(updatedPatient.address).isEqualTo(Address(addressComponents[0].toUInt(), addressComponents[1], addressComponents[2], addressComponents[3], addressComponents[4]))
        }
        Then("a new user account is created") {
            Assertions.assertThat(newUserID).isNotNull
            Assertions.assertThat(userRepository.find(newUserID!!)).isNotNull
        }
        Then("the new user account is initialized from the agent information") {
            val newUser: User = userRepository.find(newUserID!!)!!
            Assertions.assertThat(newUser.username).isEqualTo(agentInfo!!.username)
            Assertions.assertThat(newUser.email).isEqualTo(agentInfo!!.username)
            Assertions.assertThat(newUser.password).isEqualTo(hashService.md5(agentInfo!!.password))
            Assertions.assertThat(newUser.name).isEqualTo(Name(
                agentInfo!!.fullName.substring(0, agentInfo!!.fullName.indexOf(' ')),
                agentInfo!!.fullName.substring(agentInfo!!.fullName.indexOf(' ')+1, agentInfo!!.fullName.lastIndexOf(' ')),
                agentInfo!!.fullName.substring(agentInfo!!.fullName.lastIndexOf(' ')+1),
            ))
            Assertions.assertThat(newUser.language).isEqualTo(LanguagePreference.valueOf(agentInfo!!.languagePref.uppercase()))
            Assertions.assertThat(newUser.gender).isEqualTo(Gender.valueOf(agentInfo!!.gender.uppercase().replace(' ', '_')))
            if(agentInfo!!.pharmacist) Assertions.assertThat(newUser.role == UserRole.PHARMACIST).isTrue
            else Assertions.assertThat(newUser.role == UserRole.ASSISTANT).isTrue
        }
        Then("the user account is updated from the agent information") {
            val updatedUser: User = userRepository.find(userID!!)!!
            Assertions.assertThat(updatedUser.username).isEqualTo(agentInfo!!.username)
            Assertions.assertThat(updatedUser.email).isEqualTo(agentInfo!!.username)
            Assertions.assertThat(updatedUser.password).isEqualTo(hashService.md5(agentInfo!!.password))
            Assertions.assertThat(updatedUser.name).isEqualTo(Name(
                agentInfo!!.fullName.substring(0, agentInfo!!.fullName.indexOf(' ')),
                agentInfo!!.fullName.substring(agentInfo!!.fullName.indexOf(' ')+1, agentInfo!!.fullName.lastIndexOf(' ')),
                agentInfo!!.fullName.substring(agentInfo!!.fullName.lastIndexOf(' ')+1),
            ))
            Assertions.assertThat(updatedUser.language).isEqualTo(LanguagePreference.valueOf(agentInfo!!.languagePref.uppercase()))
            Assertions.assertThat(updatedUser.gender).isEqualTo(Gender.valueOf(agentInfo!!.gender.uppercase().replace(' ', '_')))
            if(agentInfo!!.pharmacist) Assertions.assertThat(updatedUser.role == UserRole.PHARMACIST).isTrue
            else Assertions.assertThat(updatedUser.role == UserRole.ASSISTANT).isTrue
        }
        Then("the user account's UserRole remains the same as before") {
            Assertions.assertThat(userRepository.find(userID!!)!!.role == initialUserRole).isTrue
        }
        Then("the user account is otherwise updated from the agent information") {
            val updatedUser: User = userRepository.find(userID!!)!!
            Assertions.assertThat(updatedUser.username).isEqualTo(agentInfo!!.username)
            Assertions.assertThat(updatedUser.email).isEqualTo(agentInfo!!.username)
            Assertions.assertThat(updatedUser.password).isEqualTo(hashService.md5(agentInfo!!.password))
            Assertions.assertThat(updatedUser.name).isEqualTo(Name(
                agentInfo!!.fullName.substring(0, agentInfo!!.fullName.indexOf(' ')),
                agentInfo!!.fullName.substring(agentInfo!!.fullName.indexOf(' ')+1, agentInfo!!.fullName.lastIndexOf(' ')),
                agentInfo!!.fullName.substring(agentInfo!!.fullName.lastIndexOf(' ')+1),
            ))
            Assertions.assertThat(updatedUser.language).isEqualTo(LanguagePreference.valueOf(agentInfo!!.languagePref.uppercase()))
            Assertions.assertThat(updatedUser.gender).isEqualTo(Gender.valueOf(agentInfo!!.gender.uppercase().replace(' ', '_')))
        }
        Then("the System returns the ID of the known Drug") {
            Assertions.assertThat(verifiedDrugID).isNotNull
            Assertions.assertThat(drugDatabaseAdaptor.getDrugByDIN(verifiedDrugID!!)).isNotNull
        }
        Then("the system returns the same DIN that was given") {
            Assertions.assertThat(verifiedDrugID).isNotNull
            Assertions.assertThat(verifiedDrugID!!).isEqualTo(identifier!!.toUInt())
        }
        Then("the System returns null - expected drug") {
            Assertions.assertThat(verifiedDrugID).isNull()
        }
        Then("the System returns the ID of the Patient") {
            Assertions.assertThat(verifiedPatientID).isNotNull
            Assertions.assertThat(patientRepository.find(verifiedPatientID!!)).isNotNull
        }
        Then("the system returns the same PHIN that was given") {
            Assertions.assertThat(verifiedPatientID).isNotNull
            Assertions.assertThat(verifiedPatientID!!).isEqualTo(identifier!!)
        }
        Then("the System returns null - expected patient") {
            Assertions.assertThat(verifiedPatientID).isNull()
        }
        Then("the System returns the ID of the known Prescriber") {
            Assertions.assertThat(verifiedPrescriberID).isNotNull
            Assertions.assertThat(prescriberRegistryAdaptor.getPrescriberByLicenseNumber(verifiedPrescriberID!!)).isNotNull
        }
        Then("the system returns the same license number that was given") {
            Assertions.assertThat(verifiedPrescriberID).isNotNull
            Assertions.assertThat(verifiedPrescriberID!!).isEqualTo(identifier!!)
        }
        Then("the System returns null - expected prescriber") {
            Assertions.assertThat(verifiedPrescriberID).isNull()
        }
        Then("the System returns null - expected UUID") {
            Assertions.assertThat(newPrescriptionFillID).isNull()
        }
        Then("the Prescription Fill's status is set to verified") {
            Assertions.assertThat(patientRepository.findByPrescriptionFillID(prescriptionFillID!!)!!.getPrescriptionFill(prescriptionFillID!!)!!.status
                    == PrescriptionFillStatus.VERIFIED).isTrue
        }
        Then("the Prescription Fill's status is set to cancelled") {
            Assertions.assertThat(patientRepository.findByPrescriptionFillID(prescriptionFillID!!)!!.getPrescriptionFill(prescriptionFillID!!)!!.status
                    == PrescriptionFillStatus.CANCELLED).isTrue
        }
        Then("the Prescription Fill's recorded clinical check is set to the one provided by the user") {
            Assertions.assertThat(patientRepository.findByPrescriptionFillID(prescriptionFillID!!)!!.getPrescriptionFill(prescriptionFillID!!)!!.clinicalCheck).isEqualTo(clinicalCheck!!)
        }
        Then("the Prescription Fill's clinical check pharmacist ID is set to the given ID") {
            Assertions.assertThat(patientRepository.findByPrescriptionFillID(prescriptionFillID!!)!!.getPrescriptionFill(prescriptionFillID!!)!!.checkPharmacistID).isEqualTo(userID)
        }
        Then("a new prescription fill is created") {
            Assertions.assertThat(newPrescriptionFillID).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionFillID(newPrescriptionFillID!!)).isNotNull //no redundant checks here
        }
        Then("the new prescription fill is initialized from the prescription fill information") {
            val newPrescriptionFill: PrescriptionFill = patientRepository.findByPrescriptionFillID(newPrescriptionFillID!!)!!.getPrescriptionFill(newPrescriptionFillID!!)!!
            Assertions.assertThat(newPrescriptionFill.lotNumber).isEqualTo(prescriptionFillInfo!!.lotNumber)
            Assertions.assertThat(newPrescriptionFill.expiryDate).isEqualTo(prescriptionFillInfo!!.expiryDate)
        }
        Then("the new prescription fill's prescription is set to the provided prescription") {
            val prescription: Prescription = patientRepository.findByPrescriptionID(prescriptionFillInfo!!.prescriptionID)!!.getPrescription(prescriptionFillInfo!!.prescriptionID)!!
            val newPrescriptionFill: PrescriptionFill = patientRepository.findByPrescriptionFillID(newPrescriptionFillID!!)!!.getPrescriptionFill(newPrescriptionFillID!!)!!
            Assertions.assertThat(newPrescriptionFill.prescription).isEqualTo(prescription)
        }
        Then("the new prescription fill's prescription's refill count was decremented") {
            val newPrescriptionFill: PrescriptionFill = patientRepository.findByPrescriptionFillID(newPrescriptionFillID!!)!!.getPrescriptionFill(newPrescriptionFillID!!)!!
            Assertions.assertThat(newPrescriptionFill.prescription.refillCount!!).isEqualTo((initialRefillCount!! - 1u).toUShort())
        }
        Then("the new prescription fill is added to the prescription's fills") {
            val prescription: Prescription = patientRepository.findByPrescriptionID(prescriptionFillInfo!!.prescriptionID)!!.getPrescription(prescriptionFillInfo!!.prescriptionID)!!
            val newPrescriptionFill: PrescriptionFill = patientRepository.findByPrescriptionFillID(newPrescriptionFillID!!)!!.getPrescriptionFill(newPrescriptionFillID!!)!!
            Assertions.assertThat(prescription.fills).contains(newPrescriptionFill)
        }
        Then("the new prescription fill's status is set to prepared") {
            Assertions.assertThat(patientRepository.findByPrescriptionFillID(newPrescriptionFillID!!)!!.getPrescriptionFill(newPrescriptionFillID!!)!!.status == PrescriptionFillStatus.PREPARED).isTrue
        }
        Then("the new prescription fill's preparation agent ID is set to the given ID") {
            Assertions.assertThat(patientRepository.findByPrescriptionFillID(newPrescriptionFillID!!)!!.getPrescriptionFill(newPrescriptionFillID!!)!!.preparationAgentID).isEqualTo(userID)
        }
        Then("the Prescription Fill's pick-up summary is set to the provided value") {
            Assertions.assertThat(patientRepository.findByPrescriptionFillID(prescriptionFillID!!)!!.getPrescriptionFill(prescriptionFillID!!)!!.pickUpSummary).isEqualTo(pickUpSummary)
        }
        Then("the Prescription Fill's status is set to retrieved") {
            Assertions.assertThat(patientRepository.findByPrescriptionFillID(prescriptionFillID!!)!!.getPrescriptionFill(prescriptionFillID!!)!!.status == PrescriptionFillStatus.RETRIEVED).isTrue
        }
        Then("the Prescription Fill's retrieval pharmacist ID is set to the given ID") {
            Assertions.assertThat(patientRepository.findByPrescriptionFillID(prescriptionFillID!!)!!.getPrescriptionFill(prescriptionFillID!!)!!.retrievalPharmacistID).isEqualTo(userID)
        }
        Then("the System fetches and returns those documents from the Drug Product Database") {
            Assertions.assertThat(fetchedPrescriptionDocs).isNotNull
            Assertions.assertThat(fetchedPrescriptionDocs!!.size > 0).isTrue
        }
        Then("the System fetches and returns contact information from the Prescriber Registry") {
            Assertions.assertThat(fetchedContactInfo).isNotNull
            Assertions.assertThat(fetchedContactInfo!!.contains("\n")).isTrue
        }
        Then("a new prescription is created") {
            Assertions.assertThat(newPrescriptionID).isNotNull
            Assertions.assertThat(patientRepository.findByPrescriptionID(newPrescriptionID!!)).isNotNull //no redundant checks here
        }
        Then("the new prescription is initialized from the prescription information") {
            val newPrescription: Prescription = patientRepository.findByPrescriptionID(newPrescriptionID!!)!!.getPrescription(newPrescriptionID!!)!!
            Assertions.assertThat(newPrescription.prescriberLicenseID).isEqualTo(prescriptionInfo!!.prescriberLicenseID)
            Assertions.assertThat(newPrescription.drugIdentificationNumber).isEqualTo(prescriptionInfo!!.DIN)
            Assertions.assertThat(newPrescription.originDate).isEqualTo(prescriptionInfo!!.originDate)
            Assertions.assertThat(newPrescription.drugDoseMg).isEqualTo(prescriptionInfo!!.drugDoseMg)
            Assertions.assertThat(newPrescription.frequencyInstruction).isEqualTo(prescriptionInfo!!.frequencyInstruction)
            Assertions.assertThat(newPrescription.notes).isEqualTo(prescriptionInfo!!.notes)
            when(prescriptionInfo!!.refillable.toInt()) {
                0 -> Assertions.assertThat(newPrescription.refillable == RefillableStatus.REFILLABLE).isTrue
                1 -> Assertions.assertThat(newPrescription.refillable == RefillableStatus.REFILLABLE_WITH_REAUTH).isTrue
                2 -> Assertions.assertThat(newPrescription.refillable == RefillableStatus.NON_REFILLABLE).isTrue
                else -> {
                    Assertions.assertThat(newPrescription.refillable == RefillableStatus.NON_REFILLABLE).isTrue
                }
            }
            Assertions.assertThat(newPrescription.refillCount).isEqualTo(prescriptionInfo!!.refillCount)
            Assertions.assertThat(newPrescription.adminMethod).isEqualTo(AdministrationMethod.valueOf(prescriptionInfo!!.adminMethod.uppercase().replace(' ', '_')))

            val drug: Drug? = drugDatabaseAdaptor.getDrugByDIN(newPrescription.drugIdentificationNumber)
            Assertions.assertThat(drug).isNotNull
            Assertions.assertThat(newPrescription.drugName).isEqualTo(drug!!.name)
            Assertions.assertThat(newPrescription.drugStrengthMgPerMl).isEqualTo(drug!!.strengthMgPerMl)
        }
        Then("the new prescription's patient is set to the provided patient") {
            val patient: Patient = patientRepository.find(prescriptionInfo!!.patientID)!!
            val newPrescription: Prescription = patientRepository.findByPrescriptionID(newPrescriptionID!!)!!.getPrescription(newPrescriptionID!!)!!
            Assertions.assertThat(newPrescription.patient).isEqualTo(patient)
        }
        Then("the new prescription is added to the patient's prescriptions") {
            val patient: Patient = patientRepository.find(prescriptionInfo!!.patientID)!!
            val newPrescription: Prescription = patientRepository.findByPrescriptionID(newPrescriptionID!!)!!.getPrescription(newPrescriptionID!!)!!
            Assertions.assertThat(patient.prescriptions).contains(newPrescription)
        }

        After { _: Scenario ->
            patientRepository = PatientRepositoryStub()
            userRepository = UserRepositoryStub()

            prescriberRegistryAdaptor = PrescriberRegistryAdaptorStub()
            drugDatabaseAdaptor = DrugProductDatabaseAdaptorStub()
            eventEmitter = EventEmitterStub()
            hashService = HashServiceImpl()

            patientFactory = PatientFactoryStub()
            prescriptionFactory = PrescriptionFactoryStub()
            prescriptionFillFactory = PrescriptionFillFactoryStub()
            userFactory = UserFactoryStub(hashService)

            patientInfo = null
            prescriptionInfo = null
            prescriptionFillInfo = null
            agentInfo = null

            patientID = null
            prescriberID = null
            prescriptionFillID = null
            userID = null
            identifier = null
            verification = null
            pickUpSummary = null
            clinicalCheck = null

            newPatientID = null
            newUserID = null
            newPrescriptionID = null
            newPrescriptionFillID = null
            success = null
            fetchedContactInfo = null
            fetchedPrescriptionDocs = null
            verifiedDrugID = null
            verifiedPatientID = null
            verifiedPrescriberID = null

            initialUserRole = null
            initialRefillCount = null
        }

    }
}
