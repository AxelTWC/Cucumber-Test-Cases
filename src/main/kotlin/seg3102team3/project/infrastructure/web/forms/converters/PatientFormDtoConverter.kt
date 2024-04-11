package seg3102team3.project.infrastructure.web.forms.converters

import org.springframework.stereotype.Component
import seg3102team3.project.application.dtos.queries.PatientDto
import seg3102team3.project.infrastructure.web.forms.PatientForm
import java.time.LocalDate

@Component
class PatientFormDtoConverter{
    fun convertFormPatient(formData: PatientForm): PatientDto {
        return PatientDto("","","","","","", LocalDate.EPOCH,"","",emptyArray(),emptyArray(),"")
    }
}
