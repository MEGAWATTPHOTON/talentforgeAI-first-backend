package com.laudado.talentforgeaibackend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTWebService {

    @Value("${jwt.access-token-expiration}")
    public Long accessTokeLifetime;

    @Value("${jwt.refresh-token-expiration}")
    public Long refreshTokenLifetime;

    String secretKey;
    Object accessToken=null;
    Object refreshToken=null;
    public JWTWebService(){
        try {
            KeyGenerator generator= KeyGenerator.getInstance("HmacSHA256");
            SecretKey secKey=generator.generateKey();
            secretKey= Base64.getEncoder().encodeToString(secKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public String generateAccessToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("tokenType", "access");

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessTokeLifetime))
                .and()
                .signWith(getKey())
                .compact();
    }
    public String generateRefreshToken(String username, int num) {
        return Jwts.builder()
                .subject(username)
                .claim("tokenType", "refresh")
                .issuedAt(new Date())
                .expiration(new Date(
                        System.currentTimeMillis()
                                + refreshTokenLifetime * num
                ))
                .signWith(getKey())
                .compact();
    }
    private Key getKey() {
        byte[] key= Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(key);
    }

    public String getUsername(String token) {
        return extractAllClaims(token).getSubject();
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public Date getExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }
    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
    public boolean tokenIsValid(String token, UserDetails userDetails) {
        String username = getUsername(token);
        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }
    public boolean isRefreshTokenValid(String refreshToken){
        try{
            Claims claims=extractAllClaims(refreshToken);
            String tokenType=claims.get("tokenType",String.class);
            return "refresh".equals(tokenType)&&!claims.getExpiration().before(new Date());
        }catch(Exception e) {
            return false;
        }
    }
}

