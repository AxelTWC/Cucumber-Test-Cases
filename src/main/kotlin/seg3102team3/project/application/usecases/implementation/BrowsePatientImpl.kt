package seg3102team3.project.application.usecases.implementation

/* Commenting out until I find the use for this code
import seg3102team3.project.application.dtos.queries.PatientDto
import seg3102team3.project.application.dtos.queries.PrescriptionDto
import seg3102team3.project.application.dtos.queries.PrescriptionFillDto
import seg3102team3.project.application.usecases.BrowsePatient
import seg3102team3.project.domain.patient.entities.Patient
import seg3102team3.project.domain.patient.entities.Prescription
import seg3102team3.project.domain.patient.entities.PrescriptionFill
import seg3102team3.project.domain.patient.facade.PatientFacade
import java.util.*

class BrowsePatientImpl(private val patientFacade: PatientFacade): BrowsePatient {

    override fun getPatient(patientId: String): PatientDto? {
        val patient = patientFacade.getPatient(patientId)
        return if (patient != null) {
            patientBrowseDto(patient)
        } else {
            null
        }
    }

    override fun getPatientPrescriptions(patient: Patient): List<PrescriptionDto>? {
        val prescList = ArrayList<Prescription>()
        for (presc in patient.prescriptions) {
                prescList.add(presc)
        }
        return getPrescriptionBrowseList(prescList)
    }

    override fun getPrescription(id: UUID): PrescriptionDto? {
        val presc = patientFacade.getPrescription(id)
        return if (presc != null) {
            prescriptionBrowseDto(presc)
        } else {
            null
        }
    }

    override fun getFillsOfPrescription(prescriptionId: UUID): List<PrescriptionFillDto>? {
        TODO("Not yet implemented")
//        val prescList = ArrayList<Prescription>()
//        for (presc in patient.prescriptions) {
//            prescList.add(presc)
//        }
//        return getPrescriptionBrowseList(prescList)
    }

    override fun getPrescriptionFill(id: UUID): PrescriptionFillDto? {
        TODO("Not yet implemented")
    }

    private fun getPrescriptionBrowseList(prescriptionList: List<Prescription>): MutableList<PrescriptionDto> {
        val prescriptionBrowseList: MutableList<PrescriptionDto> = ArrayList()
        for (presc in prescriptionList) {
            var prescDto: PrescriptionDto? = prescriptionBrowseDto(presc)
            if (prescDto != null) {
                prescriptionBrowseList.add(prescDto)
            }
        }
        return prescriptionBrowseList
    }

    private fun getPrescriptionFillBrowseList(prescriptionFillList: List<PrescriptionFill>): MutableList<PrescriptionFillDto> {
        val prescriptionFillBrowseList: MutableList<PrescriptionFillDto> = ArrayList()
        for (prescFill in prescriptionFillList) {
            var prescFillDto: PrescriptionFillDto? = prescriptionFillBrowseDto(prescFill)
            if (prescFillDto != null) {
                prescriptionFillBrowseList.add(prescFillDto)
            }
        }
        return prescriptionFillBrowseList
    }

    private fun patientBrowseDto(patient: Patient): PatientDto {
        var patientDto: PatientDto? = null
        patientDto = PatientDto(
            patient.id,
            patient.fullName.toString(),
            patient.address.toString(),
            patient.email,
            patient.phoneNumber,
            patient.gender.toString(),
            patient.dateOfBirth,
            patient.languagePref.toString(),
            patient.healthHistoryNote,
            patient.prescriptionMeds,
            patient.nonPrescriptionMeds,
            patient.insuranceNumber
        )
        return patientDto
    }

    private fun prescriptionBrowseDto(presc: Prescription): PrescriptionDto {
        var prescDto: PrescriptionDto? = null
        prescDto = PrescriptionDto(
            presc.patient.id,
            presc.prescriberLicenseID,
            presc.drugIdentificationNumber,
            presc.originDate,
            presc.drugDoseMg,
            presc.adminMethod.toString(),
            presc.frequencyInstruction,
            presc.notes,
            presc.refillable.toString(),
            presc.refillCount
        )
        return prescDto
    }

    private fun prescriptionFillBrowseDto(prescFill: PrescriptionFill): PrescriptionFillDto {
        var prescFillDto: PrescriptionFillDto? = null
        prescFillDto = PrescriptionFillDto(
            prescFill.id,
            prescFill.lotNumber,
            prescFill.expiryDate
        )
        return prescFillDto
    }
}*/