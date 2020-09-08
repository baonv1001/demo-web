package com.webapp.webdemo.security;

import com.webapp.webdemo.security.dto.UserPrincipal;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.webapp.webdemo.utils.CommonUtils.encodeTextByBASE64;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${app.security.jwtSecret}")
    private String jwtSecret;

    @Value("${app.security.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getUserNo()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, encodeTextByBASE64(jwtSecret))
                .compact();
    }

    public Long getUserNoFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(encodeTextByBASE64(jwtSecret))
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken){
        try {
            Jwts.parser()
                    .setSigningKey(encodeTextByBASE64(jwtSecret))
                    .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex){
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex){
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex){
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex){
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex){
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
