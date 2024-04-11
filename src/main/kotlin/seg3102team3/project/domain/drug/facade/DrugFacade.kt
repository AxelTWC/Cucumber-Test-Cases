package seg3102team3.project.domain.drug.facade

interface DrugFacade {
    fun identifyDrugByName(name: String): UInt?
    fun identifyDrugByDIN(din: UInt): UInt?
    fun identifyDrugByATC(atc: String): UInt?
    fun fetchDrugDocuments(din: UInt): MutableList<ByteArray>?
}
