package seg3102team3.project.domain.patient.factory

import seg3102team3.project.application.dtos.queries.PrescriptionDto
import seg3102team3.project.domain.patient.entities.Prescription
import java.util.*

interface PrescriptionFactory {
    fun createPrescription(prescriptionInfo: PrescriptionDto): Prescription
}