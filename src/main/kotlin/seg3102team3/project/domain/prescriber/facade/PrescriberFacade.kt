package seg3102team3.project.domain.prescriber.facade

interface PrescriberFacade {
    fun identifyPrescriberByName(name: String): String?
    fun identifyPrescriberByEmail(email: String): String?
    fun identifyPrescriberByLicenseNumber(licenseNum: String): String?
    fun fetchContactInfo(id: String): String?
}
