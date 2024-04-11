package seg3102team3.project.application.dtos.queries.converters

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin
import seg3102team3.project.application.dtos.queries.PrescriptionDto
import seg3102team3.project.domain.patient.entities.AdministrationMethod
import seg3102team3.project.domain.patient.entities.Prescription
import seg3102team3.project.domain.patient.entities.RefillableStatus
import java.util.UUID

interface PrescriptionDtoConverter {

    fun convertDTO(presDto: PrescriptionDto, id: UUID): Prescription

    @Mapping(target = "name", source = "refillableStatus" )
    fun refillableMap(refillableStatus: RefillableStatus): RefillableStatus

    @Mapping(target = "name", source = "administrationMethod" )
    fun adminMethodMap(adminMethod: AdministrationMethod): AdministrationMethod
}