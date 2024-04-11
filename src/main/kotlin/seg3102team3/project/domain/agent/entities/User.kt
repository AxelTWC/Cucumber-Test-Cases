package seg3102team3.project.domain.agent.entities

import seg3102team3.project.domain.common.Name;
import seg3102team3.project.domain.common.LanguagePreference;
import seg3102team3.project.domain.common.Gender;
import java.util.UUID

class User(
    val id: UUID,
    var username: String,
    var email: String,
    var password: String,
    var name: Name,
    var language: LanguagePreference,
    var gender: Gender,
    var role: UserRole
) {
    var activated: Boolean = true
    var passwordTemporary: Boolean = true

    /* unused
    fun update(agent: User) {
        this.username = agent.username
        this.name = agent.name
    }

    fun updateDetails(email: String, password: String) {
        this.email = email
        this.password = password
    }

    fun setFirstPassword(password: String) {
        this.password = password
        passwordTemporary = false
    }

    fun setTempPassword(password: String) {
        this.password = password
    }
    */

    fun deactivate() {
        this.activated = false
    }
}
