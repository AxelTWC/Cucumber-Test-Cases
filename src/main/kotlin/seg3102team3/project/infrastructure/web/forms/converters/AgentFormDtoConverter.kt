package seg3102team3.project.infrastructure.web.forms.converters

import org.springframework.stereotype.Component
import seg3102team3.project.application.dtos.queries.AgentDto
import seg3102team3.project.infrastructure.web.forms.AgentForm

@Component
class AgentFormDtoConverter{
    fun convertFormAccount(formData: AgentForm): AgentDto {
        return AgentDto(
            formData.firstname + " " + if(formData.middlenames != null) formData.middlenames + " " else "" + formData.lastname,
            formData.userName, formData.gender, formData.language, formData.email, formData.password, formData.role
        )
    }
}
