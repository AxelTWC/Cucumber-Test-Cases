package seg3102team3.project.domain.prescriber.facade.implementation

import seg3102team3.project.domain.prescriber.facade.PrescriberFacade
import seg3102team3.project.domain.prescriber.services.PrescriberRegistryAdaptor


class PrescriberFacadeImpl(val prescriberRepository: PrescriberRegistryAdaptor) : PrescriberFacade {

    override fun identifyPrescriberByName(name: String): String? {
        return prescriberRepository.getPrescriberByName(name)?.id
    }

    override fun identifyPrescriberByEmail(email: String): String? {
        return prescriberRepository.getPrescriberByEmail(email)?.id
    }

    override fun identifyPrescriberByLicenseNumber(licenseNum: String): String? {
        return prescriberRepository.getPrescriberByLicenseNumber(licenseNum)?.id
    }

    /**
     * Returns the contact info associated with the prescriber with the given ID.
     */
    override fun fetchContactInfo(id: String): String? {
        val info = prescriberRepository.getPrescriberByLicenseNumber(id)?.compileContactInfo();
        return info
    }
}
