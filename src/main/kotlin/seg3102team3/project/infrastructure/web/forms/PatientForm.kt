package seg3102team3.project.infrastructure.web.forms

import javax.validation.constraints.Email

class PatientForm {
    var firstname: String? = null
    var middlenames: String? = null
    var lastname: String? = null
    var insuranceNumber: String? = null
    var dateOfBirth: String? = null
    var phoneNumber: String? = null
    var gender: String? = null
    var language: String? = null
    var address: String? = null
    @Email(message = "{account-create-wrong-email-format}")
    var email: String? = null
    var prescriptionMeds: String? = null
    var nonPrescriptionMeds: String? = null
    var healthHistoryNote: String? = null
}