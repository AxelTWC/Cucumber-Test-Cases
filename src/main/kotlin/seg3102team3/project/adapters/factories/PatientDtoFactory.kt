package seg3102team3.project.adapters.factories

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import seg3102team3.project.application.dtos.queries.PatientDto
import seg3102team3.project.domain.common.Address
import seg3102team3.project.domain.common.Gender
import seg3102team3.project.domain.common.LanguagePreference
import seg3102team3.project.domain.common.Name
import seg3102team3.project.domain.patient.entities.Patient
import seg3102team3.project.domain.patient.factory.PatientFactory

@Primary
@Component
class PatientDtoFactory : PatientFactory {

    override fun createPatient(newPatientInfo: PatientDto): Patient {
        val addressComponents = newPatientInfo.fullAddress.split(", ").toTypedArray()
        return Patient(
            newPatientInfo.patientID,
            newPatientInfo.dateOfBirth,
            newPatientInfo.email,
            newPatientInfo.phoneNumber,
            newPatientInfo.healthHistoryNote,
            newPatientInfo.prescriptionMeds,
            newPatientInfo.nonPrescriptionMeds,
            newPatientInfo.insuranceNumber,
            Name(
                newPatientInfo.fullName.substring(0, newPatientInfo.fullName.indexOf(' ')),
                newPatientInfo.fullName.substring(newPatientInfo.fullName.indexOf(' ')+1, newPatientInfo.fullName.lastIndexOf(' ')),
                newPatientInfo.fullName.substring(newPatientInfo.fullName.lastIndexOf(' ')+1),
            ),
            LanguagePreference.valueOf(newPatientInfo.languagePref.uppercase()),
            Gender.valueOf(newPatientInfo.gender.uppercase().replace(' ', '_')),
            Address(addressComponents[0].toUInt(), addressComponents[1], addressComponents[2], addressComponents[3], addressComponents[4]),
        )
    }
}