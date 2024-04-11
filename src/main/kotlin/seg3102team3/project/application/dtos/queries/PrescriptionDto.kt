package seg3102team3.project.application.dtos.queries

import java.time.LocalDateTime

data class PrescriptionDto(val patientID: String,
                           val prescriberLicenseID: String,
                           val DIN: UInt,
                           val originDate: LocalDateTime,
                           val drugDoseMg: Float,
                           val adminMethod: String, //this matches to an AdministrationMethod enum
                           val frequencyInstruction: String,
                           val notes: String?,
                           val refillable: UByte, //0, 1 or 2 this matches to a RefillableStatus enum
                           val refillCount: UShort?) //if refillable