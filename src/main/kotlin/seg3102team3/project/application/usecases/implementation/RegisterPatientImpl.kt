package seg3102team3.project.application.usecases.implementation

import seg3102team3.project.application.dtos.queries.PatientDto
import seg3102team3.project.application.usecases.RegisterPatient
import seg3102team3.project.domain.patient.facade.PatientFacade

class RegisterPatientImpl(
    private var patientFacade: PatientFacade):RegisterPatient {
    override fun registerPatient(newPatientInfo: PatientDto): String? {
        var phin: String = patientFacade.addPatient(newPatientInfo)
        return phin
    }
}