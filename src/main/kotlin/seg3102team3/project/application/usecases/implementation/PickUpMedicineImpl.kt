package seg3102team3.project.application.usecases.implementation

import seg3102team3.project.application.usecases.PickUpMedicine
import seg3102team3.project.domain.patient.facade.PatientFacade
import java.util.*

class PickUpMedicineImpl(
    private var patientFacade: PatientFacade): PickUpMedicine {

    override fun pickUpMedicine(prescriptionFillID: UUID, pharmacistID: UUID, pickUpSummary: String): Boolean {
        val success: Boolean = patientFacade.pickUpPrescriptionFill(prescriptionFillID, pharmacistID, pickUpSummary)
        return success
    }
}