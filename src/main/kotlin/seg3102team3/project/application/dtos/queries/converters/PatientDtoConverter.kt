package seg3102team3.project.application.dtos.queries.converters

import org.mapstruct.Mapper
import seg3102team3.project.application.dtos.queries.PatientDto
import seg3102team3.project.domain.patient.entities.Patient

interface PatientDtoConverter {

    fun convertDTO(patientDto: PatientDto): Patient

    //@Mapping(target = "name", source = "refillableStatus" )
    //fun refillableMap(refillableStatus: RefillableStatus): RefillableStatus

    //@Mapping(target = "name", source = "administrationMethod" )
    //fun adminMethodMap(adminMethod: AdministrationMethod): AdministrationMethod
}