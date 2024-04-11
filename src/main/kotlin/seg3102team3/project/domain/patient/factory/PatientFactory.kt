package seg3102team3.project.domain.patient.factory

import seg3102team3.project.application.dtos.queries.PatientDto
import seg3102team3.project.domain.patient.entities.Patient

interface PatientFactory {
    fun createPatient(newPatientInfo: PatientDto): Patient
}