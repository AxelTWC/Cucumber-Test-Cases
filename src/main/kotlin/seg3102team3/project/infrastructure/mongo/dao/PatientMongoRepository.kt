package seg3102team3.project.infrastructure.mongo.dao

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import seg3102team3.project.domain.patient.entities.Patient

@Repository
interface PatientMongoRepository: MongoRepository<Patient, String>