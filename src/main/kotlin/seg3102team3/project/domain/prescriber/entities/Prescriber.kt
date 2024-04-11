package seg3102team3.project.domain.prescriber.entities

import seg3102team3.project.domain.common.Address
import seg3102team3.project.domain.common.Gender
import seg3102team3.project.domain.common.LanguagePreference
import seg3102team3.project.domain.common.Name
import java.lang.StringBuilder
import java.time.LocalDateTime
import java.util.*

class Prescriber(
        val id: String,
        var phoneNumber: String,
        var emailAddress: String? = null,
        var faxNumber: String? = null,
        var address: Address,
        var fullName: Name,
        var title: PrescriberTitle,
        var gender: Gender,
        var languagePref: LanguagePreference
) {
        fun compileContactInfo(): String {
                val sb = StringBuilder()
                sb.append("Phone Number: ").append(phoneNumber).append("\n")
                if(emailAddress != null) sb.append("Email Address: ").append(emailAddress).append("\n")
                if(faxNumber != null) sb.append("Fax Number: ").append(faxNumber).append("\n")
                sb.append("Mailing Address: ").append(address.toString()).append("\n")
                return sb.toString()
        }
}
