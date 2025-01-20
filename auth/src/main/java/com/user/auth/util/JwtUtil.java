package com.user.auth.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.auth.constant.EnumConstant;
import com.user.auth.dto.JwtClaim;
import com.user.auth.entity.Users;
import com.user.auth.exception.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class JwtUtil {

    private final String secret = "seceretakhileshatulumeshprojectauthseceretakhileshatulumeshprojectauthseceretakhileshatulumeshprojectauth";

    private final long expirationMs = 3600000; // 1 hour

    public String generateToken(Users userObj) throws JsonProcessingException {
        String fullName=userObj.getFirstName()+" "+userObj.getLastName();
        String role= EnumConstant.Role.valueOf(userObj.getRole());
        String userName=userObj.getEmailId();
        JwtClaim claim=new JwtClaim(fullName,userName,role);
        ObjectMapper mapper=new ObjectMapper();
        String subject=mapper.writeValueAsString(claim);
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String extractUsername(String token) throws JsonProcessingException {
        String subject= Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        ObjectMapper mapper=new ObjectMapper();
        JwtClaim claim= mapper.readValue(subject, JwtClaim.class);
        if(claim!=null){
            return claim.getUsername();
        }
        return "";
    }

    public boolean validateToken(String token) {
        try {
           Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            Date expirationDate=claims.getExpiration();
            return expirationDate.after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getRoleFromToken(String token) throws CustomException {
        String subject= Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        String[] subarr=subject.split("|");
        ObjectMapper mapper=new ObjectMapper();

       try {
           JwtClaim claim = mapper.readValue(subject, JwtClaim.class);
           if (claim != null) {
               return claim.getRole();
           }
       }catch (Exception ex){
           throw new CustomException("Invalid token");
       }
        return "";
    }
}
