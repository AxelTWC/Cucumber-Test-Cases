package seg3102team3.project.contracts.testStubs.services

import seg3102team3.project.domain.drug.entities.Drug
import seg3102team3.project.domain.drug.services.DrugProductDatabaseAdaptor
import java.util.HashMap

class DrugProductDatabaseAdaptorStub: DrugProductDatabaseAdaptor {
    //let's imagine we populated this list from the internet
    private val drugs: MutableMap<UInt, Drug> = HashMap()

    override fun getDrugByDIN(din: UInt): Drug? = drugs[din]

    override fun getDrugByName(name: String): Drug? {
        for((_, value) in drugs)
            if(value.name == name) return value
        return null
    }

    override fun getDrugByATC(atc: String): Drug? {
        for((_, value) in drugs)
            if(value.ATC == atc) return value
        return null
    }


}