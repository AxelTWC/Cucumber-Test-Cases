package seg3102team3.project.infrastructure.web.forms

import seg3102team3.project.infrastructure.web.forms.validators.PasswordMatch
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

@PasswordMatch(
    passwordField = "password",
    passwordConfField = "passwordConf",
    message="{account-password-not-match}"
)
class AgentForm {
    @Size(min=2, message = "{account-create-username-short}")
    var userName: String = ""
    @NotEmpty(message ="{account-create-no-password}")
    var password: String = ""
    var passwordConf: String = ""
    @NotEmpty(message ="{account-create-no-firstname}")
    var firstname: String = ""
    var middlenames: String? = null
    @NotEmpty(message ="{account-create-no-lastname}")
    var lastname: String = ""
    @NotEmpty(message ="{account-create-no-email}")
    @Email(message="{account-create-wrong-email-format}")
    var email: String = ""
    var gender: String = ""
    var language: String = ""
    var role: String = ""
}
