package seg3102team3.project.infrastructure.web.forms.converters

import org.springframework.stereotype.Component
import seg3102team3.project.application.dtos.queries.PrescriptionDto
import seg3102team3.project.infrastructure.web.forms.PrescriptionForm
import java.time.LocalDateTime

@Component
class PrescriptionFormDtoConverter{
    fun convertFormPrescription(patientId: String, formData: PrescriptionForm): PrescriptionDto {
        return PrescriptionDto(patientId, "", 0u, LocalDateTime.now(), 0f, "", "", "", 0u, 0u)
    }
}
