package seg3102team3.project.application.dtos.queries

data class AgentDto (
    val fullName: String,
    val username: String,
    val gender: String, //this matches to a Gender enum
    val languagePref: String, //this matches to a Language enum
    val email: String,
    val password: String, //business logic will hash the password before storing in User entity
    var role: String) //this matches to a Role enum