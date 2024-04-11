package seg3102team3.project.domain.patient.entities

import java.time.LocalDate
import java.util.UUID

class PrescriptionFill(val id: UUID,
                       val lotNumber: UInt,
                       val expiryDate: LocalDate
    ){
    var status: PrescriptionFillStatus = PrescriptionFillStatus.PREPARED
    var clinicalCheck: String? = null
    var pickUpSummary: String? = null
    var preparationAgentID: UUID? = null
    var checkPharmacistID: UUID? = null
    var retrievalPharmacistID: UUID? = null
    lateinit var prescription: Prescription

    fun pickUp(pharmacistID: UUID, summary: String) {
        retrievalPharmacistID = pharmacistID
        pickUpSummary = summary
        status = PrescriptionFillStatus.RETRIEVED
    }
}
