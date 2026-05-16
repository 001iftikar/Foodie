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
        private const val EXPIRATION_TIME = 15 * 60 * 1000L
        private const val REFRESH_EXPIRATION_TIME = 24 * 60 * 60 * 1000L
        private const val SECRET = "ncsficnfhscdhnfnfncuamfncnashdjadjhanxhanxasdasndhaskdhkasjhda"
    }

    /**
     * Generate token
     */
    fun generateToken(username: String, isAccessToken: Boolean): String {
        val expTime = if (isAccessToken) EXPIRATION_TIME else REFRESH_EXPIRATION_TIME
        val tokenType = if (isAccessToken) "Bearer" else "Refresh-token"
        val claims = mapOf(
            "type" to tokenType
        )
        return Jwts.builder()
            .subject(username)
            .claims(claims)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + expTime))
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

    fun isRefreshToken(token: String): Boolean {
        val claims = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(SECRET.toByteArray())).build()
            .parseSignedClaims(token).payload
        val type = claims["type"].toString()
        return type == "Refresh-token"
    }

    fun isAccessToken(token: String): Boolean {
        val claims = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(SECRET.toByteArray())).build()
            .parseSignedClaims(token).payload
        val type = claims["type"].toString()
        return type == "Bearer"
    }

}