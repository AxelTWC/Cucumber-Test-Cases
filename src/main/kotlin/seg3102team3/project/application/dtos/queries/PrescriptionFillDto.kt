package seg3102team3.project.application.dtos.queries

import java.time.LocalDate
import java.util.UUID

data class PrescriptionFillDto (
    val prescriptionID: UUID,
    val lotNumber: UInt,
    val expiryDate: LocalDate)