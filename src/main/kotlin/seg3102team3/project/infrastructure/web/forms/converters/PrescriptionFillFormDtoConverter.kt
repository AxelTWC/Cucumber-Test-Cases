package seg3102team3.project.infrastructure.web.forms.converters

import org.springframework.stereotype.Component
import seg3102team3.project.application.dtos.queries.PrescriptionFillDto
import seg3102team3.project.infrastructure.web.forms.PrescriptionFillForm
import java.time.LocalDate
import java.util.*

@Component
class PrescriptionFillFormDtoConverter{
    fun convertFormPrescriptionFill(prescriptionId: UUID, formData: PrescriptionFillForm): PrescriptionFillDto {
        return PrescriptionFillDto(prescriptionId, formData.lotNumber, LocalDate.EPOCH)
    }
}
