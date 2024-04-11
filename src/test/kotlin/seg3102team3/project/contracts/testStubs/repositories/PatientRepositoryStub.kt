package seg3102team3.project.contracts.testStubs.repositories

import seg3102team3.project.domain.common.Name
import seg3102team3.project.domain.patient.entities.Patient
import seg3102team3.project.domain.patient.repository.PatientRepository
import java.util.*

class PatientRepositoryStub : PatientRepository {
    private val patients: MutableMap<String, Patient> = HashMap()

    override fun save(patient: Patient): Patient {
        patients[patient.id] = patient
        return patient
    }

    override fun find(id: String): Patient? = patients[id]

    override fun findByName(name: String): Patient? {
        val n = Name(
                name.substring(0, name.indexOf(' ')),
                name.substring(name.indexOf(' ')+1, name.lastIndexOf(' ')),
                name.substring(name.lastIndexOf(' ')+1),
        )
        for((_, value) in patients)
            if(value.fullName == n) return value
        return null
    }

    override fun findByEmail(email: String): Patient? {
        for((_, value) in patients)
            if(value.email == email) return value
        return null
    }

    override fun findByPrescriptionID(id: UUID): Patient? {
        for((_, patient) in patients)
            for(prescription in patient.prescriptions)
                if(prescription.id == id) return patient
        return null
    }

    override fun findByPrescriptionFillID(id: UUID): Patient? {
        for((_, patient) in patients)
            for(prescription in patient.prescriptions)
                for(fill in prescription.fills)
                    if(fill.id == id) return patient
        return null
    }

    override fun update(patient: Patient): Patient {
        return save(patient) //equivalent
    }
}