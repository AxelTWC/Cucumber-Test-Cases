package seg3102team3.project.application.usecases

import seg3102team3.project.application.dtos.queries.PatientDto

interface RegisterPatient {
    fun registerPatient(newPatientInfo: PatientDto): String?
}