package seg3102team3.project.application.usecases.implementation

import seg3102team3.project.application.dtos.queries.PrescriptionFillDto
import seg3102team3.project.application.usecases.PreparePrescriptionFill
import seg3102team3.project.domain.drug.facade.DrugFacade
import seg3102team3.project.domain.patient.facade.PatientFacade
import seg3102team3.project.domain.prescriber.facade.PrescriberFacade
import java.util.*

class PreparePrescriptionFillImpl(
    private var patientFacade: PatientFacade,
    private var prescriberFacade: PrescriberFacade,
    private var drugFacade: DrugFacade): PreparePrescriptionFill {

    override fun preparePrescriptionFill(prescriptionFillInfo: PrescriptionFillDto, agentID: UUID): UUID? {
        var uuid: UUID? = patientFacade.addPrescriptionFill(prescriptionFillInfo, agentID)
        return uuid
    }

    override fun fetchPrescriberContactInfo(prescriberID: String): String? {
        var contactInfo: String? = prescriberFacade.fetchContactInfo(prescriberID)
        return contactInfo
    }

    override fun verifyPrescriptionFill(
        prescriptionFillID: UUID,
        pharmacistID: UUID,
        clinicalCheck: String,
        verification: Boolean
    ): Boolean {
        var result: Boolean = patientFacade.verifyPrescriptionFill(prescriptionFillID, pharmacistID, clinicalCheck, verification)
        return result
    }

    override fun fetchPrescriptionDocs(prescriptionFillID: UUID): MutableList<ByteArray>? {
        var din: UInt? = patientFacade.fetchPrescriptionFillDIN(prescriptionFillID);
        if(din == null) return null
        var documents: MutableList<ByteArray>? = drugFacade.fetchDrugDocuments(din)
        return documents;
    }
}