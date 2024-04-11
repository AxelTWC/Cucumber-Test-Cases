package seg3102team3.project.adapters.services.implementation.domain.drug

import org.springframework.stereotype.Service
import seg3102team3.project.domain.drug.entities.Drug
import seg3102team3.project.domain.drug.services.DrugProductDatabaseAdaptor
import seg3102team3.project.domain.prescriber.entities.Prescriber
import seg3102team3.project.domain.prescriber.services.PrescriberRegistryAdaptor

@Service
class DrugProductDatabaseAdaptorImpl: DrugProductDatabaseAdaptor {
    override fun getDrugByATC(atc: String): Drug? {
        TODO("Not yet implemented")
    }

    override fun getDrugByName(name: String): Drug? {
        TODO("Not yet implemented")
    }

    override fun getDrugByDIN(din: UInt): Drug? {
        TODO("Not yet implemented")
    }
}