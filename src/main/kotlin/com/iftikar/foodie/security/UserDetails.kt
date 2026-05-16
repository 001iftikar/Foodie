package com.iftikar.foodie.security

import com.iftikar.foodie.enities.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * Our custom user details class to verify user
 */
class UserDetails(
    val user: User
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
       val roleList = user.roleEntities.map {
            SimpleGrantedAuthority(it.name)
        }
        return roleList
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.email
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
        return user.enabled
    }
}