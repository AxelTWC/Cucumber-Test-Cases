package seg3102team3.project.domain.prescriber.services

import seg3102team3.project.domain.prescriber.entities.Prescriber

interface PrescriberRegistryAdaptor {
    fun getPrescriberByLicenseNumber(licenseNumber: String): Prescriber?
    fun getPrescriberByName(name: String): Prescriber?
    fun getPrescriberByEmail(email: String): Prescriber?
}
