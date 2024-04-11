package seg3102team3.project.domain.patient.factory

import seg3102team3.project.application.dtos.queries.PrescriptionFillDto
import seg3102team3.project.domain.patient.entities.PrescriptionFill
import java.util.*

interface PrescriptionFillFactory {
    fun createPrescriptionFill(prescriptionFillInfo: PrescriptionFillDto): PrescriptionFill
}