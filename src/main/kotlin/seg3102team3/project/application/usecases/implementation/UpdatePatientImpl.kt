package seg3102team3.project.application.usecases.implementation

import seg3102team3.project.application.dtos.queries.PatientDto
import seg3102team3.project.application.usecases.UpdatePatient
import seg3102team3.project.domain.patient.facade.PatientFacade

class UpdatePatientImpl(
    private var patientFacade: PatientFacade):UpdatePatient {
    override fun updatePatient(patientID: String, updatedPatientInfo: PatientDto): Boolean {
        var result: Boolean = patientFacade.updatePatient(patientID, updatedPatientInfo)
        return result
    }
}