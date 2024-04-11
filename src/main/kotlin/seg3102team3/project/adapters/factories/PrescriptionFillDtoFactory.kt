package seg3102team3.project.adapters.factories

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import seg3102team3.project.application.dtos.queries.PrescriptionFillDto
import seg3102team3.project.domain.patient.entities.PrescriptionFill
import seg3102team3.project.domain.patient.factory.PrescriptionFillFactory
import java.util.*

@Primary
@Component
class PrescriptionFillDtoFactory: PrescriptionFillFactory {

    override fun createPrescriptionFill(prescriptionFillInfo: PrescriptionFillDto): PrescriptionFill {
        return PrescriptionFill(UUID.randomUUID(), prescriptionFillInfo.lotNumber, prescriptionFillInfo.expiryDate)
    }
}