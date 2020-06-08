package com.mentaldoctor.mentaldoctor.util;

import com.mentaldoctor.mentaldoctor.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil {

    /**
     * JWT前缀
     */
    public static final String TOKEN_PREFIX="Bearer";

    /**
     * Header Key
     */
    public static final String HEADER_STRING="Token";

    /**
     * 有效时间5天
     */
    private static final long EXPIRATION_TIME=5*24*60*60*1000;

    /**
     * JWT密码
     */
    private static final String SECRET="MentalDoctor";

    /**
     * claim中username对应的key
     */
    private static final String CLAIM_KEY_USERNAME="sub";

    /**
     * 创建日期的key
     */
    private static final String CLAIM_KEY_CREATED = "created";

    /**
     * 从Token中解析Username
     * @param token
     * @return
     */
    public static String getUsernameFromToken(String token){
        Claims claims=getClaimsFromToken(token);
        String username=claims.getSubject();
        return username;
    }

    /**
     * 验证Tokens是否有效
     * @param token
     * @param userDetails
     * @return
     */
    public static boolean validateToken(String token, UserDetails userDetails){
        User user=(User)userDetails;
        String username=getUsernameFromToken(token);
        return (username.equals(user.getUsername())&&!isTokenExpired(token));
    }

    /**
     * 生成JWT Token
     * @param userDetails
     * @return
     */
    public static String generateToken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

    /**
     * 生成JWT Token
     * @param claims
     * @return
     */
    private static String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }

    /**
     * 生成过期时间
     * @return
     */
    private static Date generateExpirationDate(){
        return new Date(System.currentTimeMillis()+EXPIRATION_TIME);
    }

    /**
     * 获取Jwt的Body
     * @param token
     * @return
     */
    private static Claims getClaimsFromToken(String token){
        Claims claims= Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        return claims;
    }

    /**
     * Token是否过期
     * @param token
     * @return
     */
    private static boolean isTokenExpired(String token){
        Date expiration=getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 获取Token过期时间
     * @param token
     * @return
     */
    private static Date getExpirationDateFromToken(String token) {
        Claims claims=getClaimsFromToken(token);
        Date expiration=claims.getExpiration();
        return expiration;
    }
}
