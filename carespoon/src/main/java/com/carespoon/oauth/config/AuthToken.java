package com.carespoon.config;

import io.jsonwebtoken.*;
import lombok.Getter;

import java.security.Key;
import java.util.Date;
import java.util.logging.Logger;

public class AuthToken {

    @Getter
    private final String token;
    private final Key key;

    private final static Logger log = Logger.getGlobal();
    AuthToken(String socialId, Date expiry, Key key){
        this.key = key;
        this.token = createAuthToken(socialId, expiry);
    }

    private String createAuthToken(String socialId, Date expiry){
        return Jwts.builder()
                .setSubject(socialId)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(expiry)
                .compact();
    }

    public boolean validate(){
        return this.getTokenClaims() != null;
    }

    public Claims getTokenClaims(){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch(SecurityException e){
            log.info("Invalid JWT signature.");
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
        }
        return null;
    }
}
