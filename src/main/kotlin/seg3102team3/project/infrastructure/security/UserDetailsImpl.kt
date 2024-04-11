package seg3102team3.project.infrastructure.security

import com.fasterxml.jackson.annotation.JsonIgnore
import org.slf4j.LoggerFactory
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import seg3102team3.project.domain.agent.entities.User
import seg3102team3.project.domain.agent.entities.UserRole
import java.util.*
import kotlin.collections.ArrayList

class UserDetailsImpl(val id: UUID, private val username: String,
                      @field:JsonIgnore private val password: String,
                      private val enabled: Boolean,
                      private val authorities: Collection<GrantedAuthority>) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return enabled
    }
}

fun build(user: User): UserDetailsImpl {
    val authorities = ArrayList<GrantedAuthority>()
    authorities.add(SimpleGrantedAuthority(user.role.name))

    LoggerFactory.getLogger("MY COOL TESTING").debug(user.role.name);

    /*Logic if we want admins to have pharmacist features and pharmacists to have assistant features
    if(user.role != UserRole.ASSISTANT){
        if(user.role == UserRole.ADMINISTRATOR) authorities.add(SimpleGrantedAuthority(UserRole.PHARMACIST.name))
        authorities.add(SimpleGrantedAuthority(UserRole.ASSISTANT.name))
    }*/

    //Logic if we want pharmacist to have assistant features but not administrators
    if(user.role == UserRole.PHARMACIST) authorities.add(SimpleGrantedAuthority(UserRole.ASSISTANT.name))

    return UserDetailsImpl(
        user.id,
        user.username,
        user.password,
        user.activated,
        authorities)
}

