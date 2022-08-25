package com.kruger.vaccination.security.jwt;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.kruger.vaccination.security.services.UserDetailsImpl;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;

@Component
public class JwtUtil {
    private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    public String generateJwtToken(Authentication authentication) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, 30);
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
                .setExpiration(calendar.getTime()).signWith(key).compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("El token a expirado: {}", e.getMessage());
        } catch (ClaimJwtException e) {
            logger.error("JWT Claims string está vacío}}: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Token inválido: {}", e.getMessage());
        } catch (SecurityException e) {
            logger.error("Firma no válida para Token: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Token no soportado: {}", e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }
}