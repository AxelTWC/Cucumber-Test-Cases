package seg3102team3.project.application.usecases

import seg3102team3.project.application.dtos.queries.PrescriptionFillDto
import java.util.ArrayList
import java.util.UUID

interface PreparePrescriptionFill {
    fun preparePrescriptionFill(prescriptionFillInfo: PrescriptionFillDto, agentID: UUID): UUID? //agent client
    fun fetchPrescriberContactInfo(prescriberID: String): String?
    fun verifyPrescriptionFill(prescriptionFillID: UUID, pharmacistID: UUID, clinicalCheck: String, verification: Boolean): Boolean //pharmacist client - clinical check info and pharmacist ID is now set in the Prescription Fill domain object and its status is changed (Cancelled/Verified) depending on verification boolean
    fun fetchPrescriptionDocs(prescriptionFillID: UUID): MutableList<ByteArray>? //agent client - ByteArray for returning both PDFs, as these are binary file formats
}