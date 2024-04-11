package seg3102team3.project.adapters.repositories

import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import seg3102team3.project.domain.agent.entities.User
import seg3102team3.project.domain.agent.repositories.UserRepository
import seg3102team3.project.domain.patient.entities.Patient
import seg3102team3.project.domain.patient.repository.PatientRepository
import seg3102team3.project.infrastructure.mongo.dao.PatientMongoRepository
import seg3102team3.project.infrastructure.mongo.dao.UserMongoRepository
import java.util.*

@Component
@CacheConfig(cacheNames=["users"])
class PatientMongoAdapter(private val patientRepository: PatientMongoRepository): PatientRepository {

    @CachePut(key = "#patient.id")
    override fun save(patient: Patient): Patient {
        return patientRepository.save(patient)
    }

    @Cacheable(key = "#id")
    @Transactional
    override fun find(id: String): Patient? {
        return patientRepository.findByIdOrNull(id)
    }

    override fun findByName(name: String): Patient? {
        TODO("Not yet implemented")
    }

    override fun findByEmail(email: String): Patient? {
        TODO("Not yet implemented")
    }

    override fun findByPrescriptionID(id: UUID): Patient? {
        TODO("Not yet implemented")
    }

    override fun findByPrescriptionFillID(id: UUID): Patient? {
        TODO("Not yet implemented")
    }

    override fun update(patient: Patient): Patient {
        TODO("Not yet implemented")
    }
}