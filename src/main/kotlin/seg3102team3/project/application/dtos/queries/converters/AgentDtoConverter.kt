package seg3102team3.project.application.dtos.queries.converters

import org.mapstruct.Mapping
import seg3102team3.project.application.dtos.queries.AgentDto
import seg3102team3.project.domain.agent.entities.User
import seg3102team3.project.domain.common.Gender
import seg3102team3.project.domain.common.LanguagePreference
import java.util.UUID

interface AgentDtoConverter {

    fun convertDto(agentDto: AgentDto, id: UUID): User

    @Mapping(target = "name", source = "gender" )
    fun genderMap(gender: Gender): Gender

    @Mapping(target = "name", source = "languagePref" )
    fun langaugePrefMap(languagePreference: LanguagePreference): LanguagePreference
}