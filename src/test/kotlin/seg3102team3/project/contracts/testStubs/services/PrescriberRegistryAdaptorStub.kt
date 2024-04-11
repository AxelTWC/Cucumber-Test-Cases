package seg3102team3.project.contracts.testStubs.services

import seg3102team3.project.domain.common.Name
import seg3102team3.project.domain.prescriber.entities.Prescriber
import seg3102team3.project.domain.prescriber.services.PrescriberRegistryAdaptor
import java.util.HashMap

class PrescriberRegistryAdaptorStub : PrescriberRegistryAdaptor {
    //let's imagine we populated this list from the internet
    private val prescribers: MutableMap<String, Prescriber> = HashMap()

    override fun getPrescriberByLicenseNumber(licenseNumber: String): Prescriber? = prescribers[licenseNumber];

    override fun getPrescriberByName(name: String): Prescriber? {
        val n = Name(
            name.substring(0, name.indexOf(' ')),
            name.substring(name.indexOf(' ')+1, name.lastIndexOf(' ')),
            name.substring(name.lastIndexOf(' ')+1),
        )
        for((_, value) in prescribers)
            if(value.fullName == n) return value
        return null
    }

    override fun getPrescriberByEmail(email: String): Prescriber? {
        for((_, value) in prescribers)
            if(value.emailAddress == email) return value
        return null
    }
}