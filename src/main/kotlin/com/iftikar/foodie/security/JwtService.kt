package com.iftikar.foodie.security

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*

/**
 * performs operations with jwt
 */
@Service
class JwtService {
    companion object {
        private val EXPIRATION_TIME = 15 * 60 * 1000L
        private val SECRET = "ncsficnfhscdhnfnfncuamfncnashdjadjhanxhanxasdasndhaskdhkasjhda"
    }

    /**
     * Generate token
     */
    fun generateToken(username: String): String {
        return Jwts.builder()
            .subject(username)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(Keys.hmacShaKeyFor(SECRET.toByteArray()), Jwts.SIG.HS256)
            .compact()
    }

    /**
     * Get username from token
     */
    fun getUsername(token: String): String {
        return Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(SECRET.toByteArray()))
            .build()
            .parseSignedClaims(token)
            .payload
            .subject
    }

    /**
     * validate token
     */
    fun validateToken(token: String): Boolean {
        if (this.isTokenExpired(token)) {
            return false
        }
        try {
            Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(SECRET.toByteArray()))
                .build()
                .parseSignedClaims(token)
            return true
        } catch (ex: JwtException) {
            ex.printStackTrace()
            return false
        }
    }

    fun isTokenExpired(token: String): Boolean {
        val expiration = Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(SECRET.toByteArray()))
            .build()
            .parseSignedClaims(token)
            .payload
            .expiration
        return expiration.before(Date(System.currentTimeMillis()))
    }
}