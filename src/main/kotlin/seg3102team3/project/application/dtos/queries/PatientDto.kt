package seg3102team3.project.application.dtos.queries

import java.time.LocalDate

data class PatientDto(val patientID: String,
                      val fullName: String,
                      val fullAddress: String,
                      val email: String?,
                      val phoneNumber: String?,
                      val gender: String, //this matches to a Gender enum
                      val dateOfBirth: LocalDate,
                      val languagePref: String, //this matches to a Language enum
                      val healthHistoryNote: String?, //for both drug allergies and intolerances, if any
                      val prescriptionMeds: Array<UInt>?,
                      val nonPrescriptionMeds: Array<UInt>?,
                      val insuranceNumber: String?)