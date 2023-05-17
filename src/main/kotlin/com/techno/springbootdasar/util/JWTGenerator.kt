package com.techno.springjwt.util

import com.google.gson.Gson
import com.techno.springbootdasar.dto.response.JWTSubject
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.apache.catalina.User
import org.slf4j.LoggerFactory
import java.util.*
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter

class JWTGenerator {
    companion object {
        private const val SECRET_KEY = "SUPER_SECRETE"
        private val instance: JWTGenerator = JWTGenerator()
    }

    val log = LoggerFactory.getLogger(this::class.java)
        fun createJWT(id : Long, subject: Long): String {
//    fun createJWT(id : Long, name: String,email: String, username:String): String {
        val signatureAlgorithm: SignatureAlgorithm = SignatureAlgorithm.HS256
        val nowMills: Long = System.currentTimeMillis()
        val now = Date(nowMills)

        val apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY)
        val signingKey = SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.jcaName)
//        val subject = JWTSubject(id.toString(),name,username,email)
        val builder: JwtBuilder = Jwts.builder().setId(id.toString())
            .setIssuedAt(now)
//            .setSubject(Gson().toJson(subject))
            .setSubject(subject.toString())
            .setIssuer("technocenter")
            .setAudience("technocenter")
            .signWith(signatureAlgorithm, signingKey)

        val expMills = nowMills + 2628000000L
        val exp = Date(expMills)
        builder.setExpiration(exp)

        return builder.compact()
    }

    fun decodeJWT(jwt: String): Long {
        val claims: Claims = Jwts.parser()
            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
            .parseClaimsJws(jwt).body
        val result = claims.subject.toLong()
        log.info("ID : ${claims.id}")
        log.info("Issuer : ${claims.issuer}")
        log.info("Subject : ${claims.subject}")
        return result
    }
}

