package seg3102team3.project.infrastructure.web.forms

class PrescriptionForm {
    var prescriberLicenceID: String = ""
    var DIN: UInt = 0u
    var originDate: String = ""
    var drugDoseMg: Float = 0F
    var adminMethod: String = ""
    var frequencyInstructions: String = ""
    var notes: String = ""
    var refillableStatus: String = ""
    var refillCount: UShort = 0u
}