package seg3102team3.project.domain.patient.entities
import seg3102team3.project.application.dtos.queries.PatientDto
import seg3102team3.project.domain.common.Address
import seg3102team3.project.domain.common.Gender
import seg3102team3.project.domain.common.LanguagePreference
import seg3102team3.project.domain.common.Name
import java.util.*
import java.time.LocalDate

class Patient(
    var id: String,
    var dateOfBirth: LocalDate,
    var email: String? = null,
    var phoneNumber: String? = null,
    var healthHistoryNote: String? = null,
    var prescriptionMeds: Array<UInt>? = emptyArray(),
    var nonPrescriptionMeds: Array<UInt>? = emptyArray(),
    var insuranceNumber: String? = null,
    var fullName: Name,
    var languagePref: LanguagePreference,
    var gender: Gender,
    var address: Address
){
    val prescriptions: MutableList<Prescription> = ArrayList()

    fun update(patientInfo: PatientDto) {
        val n = Name(
            patientInfo.fullName.substring(0, patientInfo.fullName.indexOf(' ')),
            patientInfo.fullName.substring(patientInfo.fullName.indexOf(' ')+1, patientInfo.fullName.lastIndexOf(' ')),
            patientInfo.fullName.substring(patientInfo.fullName.lastIndexOf(' ')+1),
        )
        val addressComponents = patientInfo.fullAddress.split(", ").toTypedArray()

        id = patientInfo.patientID
        fullName = n
        address = Address(addressComponents[0].toUInt(), addressComponents[1], addressComponents[2], addressComponents[3], addressComponents[4])
        email = patientInfo.email
        phoneNumber = patientInfo.phoneNumber
        gender = Gender.valueOf(patientInfo.gender)
        dateOfBirth = patientInfo.dateOfBirth
        languagePref = LanguagePreference.valueOf(patientInfo.languagePref)
        healthHistoryNote = patientInfo.healthHistoryNote
        prescriptionMeds = patientInfo.prescriptionMeds
        nonPrescriptionMeds = patientInfo.nonPrescriptionMeds
        insuranceNumber = patientInfo.insuranceNumber
    }

    fun getPrescription(prescriptionID: UUID): Prescription? {
        for(prescription in prescriptions)
            if(prescription.id == prescriptionID) return prescription
        return null
    }

    fun getPrescriptionFill(prescriptionFillID: UUID): PrescriptionFill? {
        for(prescription in prescriptions)
            for(fill in prescription.fills)
                if(fill.id == prescriptionFillID) return fill
        return null
    }
}