package seg3102team3.project.contracts.testStubs.factories

import seg3102team3.project.application.dtos.queries.PrescriptionFillDto
import seg3102team3.project.domain.patient.entities.PrescriptionFill
import seg3102team3.project.domain.patient.factory.PrescriptionFillFactory
import java.util.*

class PrescriptionFillFactoryStub: PrescriptionFillFactory {
    override fun createPrescriptionFill(prescriptionFillInfo: PrescriptionFillDto): PrescriptionFill {
        return PrescriptionFill(UUID.randomUUID(), prescriptionFillInfo.lotNumber, prescriptionFillInfo.expiryDate)
    }
}