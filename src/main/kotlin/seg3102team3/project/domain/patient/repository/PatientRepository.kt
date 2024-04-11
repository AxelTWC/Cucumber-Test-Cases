package seg3102team3.project.domain.patient.repository

import seg3102team3.project.domain.patient.entities.Patient
import java.util.*

interface PatientRepository {
        fun save(patient: Patient): Patient
        fun find(id: String): Patient?
        fun findByName(name: String): Patient?
        fun findByEmail(email: String): Patient?
        fun findByPrescriptionID(id: UUID): Patient?
        fun findByPrescriptionFillID(id: UUID): Patient?
        fun update(patient: Patient): Patient
}
