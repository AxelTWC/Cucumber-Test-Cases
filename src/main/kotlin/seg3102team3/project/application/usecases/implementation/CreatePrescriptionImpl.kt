package seg3102team3.project.application.usecases.implementation

import seg3102team3.project.application.dtos.queries.PrescriptionDto
import seg3102team3.project.application.usecases.CreatePrescription
import seg3102team3.project.domain.drug.facade.DrugFacade
import seg3102team3.project.domain.patient.facade.PatientFacade
import seg3102team3.project.domain.prescriber.facade.PrescriberFacade
import java.util.*

class CreatePrescriptionImpl(
    private var patientFacade: PatientFacade,
    private var prescriberFacade: PrescriberFacade,
    private var drugFacade: DrugFacade): CreatePrescription {

    override fun verifyPatientInfo(identifier: String): String? {
        var phin: String? = patientFacade.identifyPatientByName(identifier)
        if(phin != null) return phin
        phin = patientFacade.identifyPatientByEmail(identifier)
        if(phin != null) return phin
        return patientFacade.identifyPatientByPHIN(identifier)
    }

    override fun verifyPrescriberInfo(identifier: String): String? {
        var licenseNum: String? = prescriberFacade.identifyPrescriberByName(identifier)
        if(licenseNum != null) return licenseNum
        licenseNum = prescriberFacade.identifyPrescriberByEmail(identifier)
        if(licenseNum != null) return licenseNum
        return prescriberFacade.identifyPrescriberByLicenseNumber(identifier)
    }

    override fun verifyDrugInfo(identifier: String): UInt? {
        var din: UInt? = drugFacade.identifyDrugByName(identifier)
        if(din != null) return din
        din = drugFacade.identifyDrugByATC(identifier)
        if(din != null) return din
        return drugFacade.identifyDrugByDIN(identifier.toUInt())
    }

    override fun createPrescription(prescriptionInfo: PrescriptionDto): UUID? {
        var uuid: UUID? = patientFacade.addPrescription(prescriptionInfo)
        return uuid
    }
}