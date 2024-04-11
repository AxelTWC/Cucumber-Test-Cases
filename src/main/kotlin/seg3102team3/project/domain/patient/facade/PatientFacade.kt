package seg3102team3.project.domain.patient.facade

import seg3102team3.project.application.dtos.queries.PatientDto;
import seg3102team3.project.application.dtos.queries.PrescriptionDto;
import seg3102team3.project.application.dtos.queries.PrescriptionFillDto
import seg3102team3.project.domain.patient.entities.Patient
import java.util.UUID

interface PatientFacade {
    fun addPatient(newPatientInfo: PatientDto): String
    fun addPrescription(prescriptionInfo: PrescriptionDto): UUID?
    fun addPrescriptionFill(prescriptionFillInfo: PrescriptionFillDto, agentID: UUID): UUID?
    fun updatePatient(patientID: String, patientInfo: PatientDto): Boolean
    /*Commenting out until I find the use for this
    fun getPatient(patientId: String): Patient?
    fun getPrescription(prescriptionId: UUID): Prescription?*/
    fun identifyPatientByName(name: String): String?
    fun identifyPatientByEmail(email: String): String?
    fun identifyPatientByPHIN(phin: String): String?
    fun fetchPrescriptionFillDIN(prescriptionFillID: UUID): UInt?
    fun pickUpPrescriptionFill(prescriptionFillID: UUID, pharmacistID: UUID, pickUpSummary: String): Boolean
    fun verifyPatientIdentifier(someIdentifier: String):Patient?
    fun verifyPrescriptionFill(prescriptionFillID: UUID, pharmacistID: UUID, clinicalCheck: String, verification: Boolean): Boolean
}