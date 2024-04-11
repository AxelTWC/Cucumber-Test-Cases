package seg3102team3.project.adapters.services.implementation.domain.prescriber

import org.springframework.stereotype.Service
import seg3102team3.project.domain.prescriber.entities.Prescriber
import seg3102team3.project.domain.prescriber.services.PrescriberRegistryAdaptor

@Service
class PrescriberRegistryAdaptorImpl: PrescriberRegistryAdaptor {
    override fun getPrescriberByLicenseNumber(licenseNumber: String): Prescriber? {
        TODO("Not yet implemented")
    }

    override fun getPrescriberByName(name: String): Prescriber? {
        TODO("Not yet implemented")
    }

    override fun getPrescriberByEmail(email: String): Prescriber? {
        TODO("Not yet implemented")
    }
}