package seg3102team3.project.domain.patient.facade.implementation

import seg3102team3.project.domain.drug.facade.DrugFacade
import seg3102team3.project.domain.drug.services.DrugProductDatabaseAdaptor

class DrugFacadeImpl(val drugDB: DrugProductDatabaseAdaptor) : DrugFacade {

    override fun identifyDrugByName(name: String): UInt? {
        val d = drugDB.getDrugByName(name)
        return if (d != null) d.id else null
    }

    override fun identifyDrugByDIN(din: UInt): UInt? {
        val d = drugDB.getDrugByDIN(din)
        return if (d != null) d.id else null
    }

    override fun identifyDrugByATC(atc: String): UInt? {
        val d = drugDB.getDrugByATC(atc)
        return if (d != null) d.id else null
    }
    /**
     * Returns the monograph of the drug with the given DIN.
     *
     */
    override fun fetchDrugDocuments(din: UInt): MutableList<ByteArray>? {
        val drug = drugDB.getDrugByDIN(din)
        if(drug == null) return null

        val res: MutableList<ByteArray> = ArrayList()
        res.add(drug.monographPDF)

        for(pdf in drug.counsellingDocsPDF) res.add(pdf)
        for(pdf in drug.pastReports) res.add(pdf)
        return res
    }
}
