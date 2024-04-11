package seg3102team3.project.application.dtos.queries.converters

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import seg3102team3.project.application.dtos.queries.PrescriptionFillDto
import seg3102team3.project.domain.patient.entities.PrescriptionFill
import seg3102team3.project.domain.patient.entities.PrescriptionFillStatus
import seg3102team3.project.domain.patient.entities.RefillableStatus
import java.util.*

interface PrescriptionFillDtoConverter {

    fun convertDTO(presFillDto: PrescriptionFillDto, id: UUID): PrescriptionFill

    @Mapping(target = "name", source = "prescriptionFillStatus" )
    fun prescriptionFillStatusMap(status: PrescriptionFillStatus): PrescriptionFillStatus
}