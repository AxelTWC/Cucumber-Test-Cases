package seg3102team3.project.contracts.testStubs.factories

import seg3102team3.project.application.dtos.queries.PrescriptionDto
import seg3102team3.project.contracts.testStubs.services.DrugProductDatabaseAdaptorStub
import seg3102team3.project.domain.patient.entities.AdministrationMethod
import seg3102team3.project.domain.patient.entities.Prescription
import seg3102team3.project.domain.patient.entities.RefillableStatus
import seg3102team3.project.domain.patient.factory.PrescriptionFactory
import java.util.*

class PrescriptionFactoryStub: PrescriptionFactory {
    override fun createPrescription(prescriptionInfo: PrescriptionDto): Prescription {
        var rs: RefillableStatus
        rs = when(prescriptionInfo.refillable.toInt()) {
            0 -> RefillableStatus.REFILLABLE
            1 -> RefillableStatus.REFILLABLE_WITH_REAUTH
            2 -> RefillableStatus.NON_REFILLABLE
            else -> {
                RefillableStatus.NON_REFILLABLE
            }
        }
        var drug = DrugProductDatabaseAdaptorStub().getDrugByDIN(prescriptionInfo.DIN)
        return Prescription(
            UUID.randomUUID(),
            prescriptionInfo.prescriberLicenseID,
            prescriptionInfo.DIN,
            prescriptionInfo.originDate,
            prescriptionInfo.drugDoseMg,
            prescriptionInfo.frequencyInstruction,
            prescriptionInfo.notes,
            rs,
            if(rs != RefillableStatus.NON_REFILLABLE) prescriptionInfo.refillCount else 0u,
            AdministrationMethod.valueOf(prescriptionInfo.adminMethod.uppercase().replace(' ', '_')),
            drug?.name,
            drug?.strengthMgPerMl)
    }
}