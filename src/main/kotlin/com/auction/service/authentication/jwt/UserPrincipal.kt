package com.auction.service.authentication.jwt

import com.auction.service.entities.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.function.Consumer

class UserPrincipal(private val user: User) : UserDetails {
    override fun getAuthorities(): MutableCollection<GrantedAuthority> {
        val authorities: MutableList<GrantedAuthority> = ArrayList()
        user.getPermissionList().forEach(Consumer { permission: String ->
            val authority: GrantedAuthority = SimpleGrantedAuthority(permission)
            authorities.add(authority)
        })
        user.getRoleList().forEach(Consumer { role: String ->
            val authority: GrantedAuthority = SimpleGrantedAuthority(role)
            authorities.add(authority)
        })
        return authorities
    }

    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}