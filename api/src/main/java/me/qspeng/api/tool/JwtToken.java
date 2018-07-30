package me.qspeng.api.tool;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.NonNull;
import me.qspeng.api.exception.UnauthorizedException;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//TODO Refresh Token
public class JwtToken {

    /**
     * token秘钥，请勿泄露，请勿随便修改 backups:CLisLLH1
     */
    private static final String SECRET = "CLisLLH1";
    /**
     * token 过期时间: 2Hour
     */
    private static final int calendarField = Calendar.HOUR;
    private static final int calendarInterval = 2;


    public static String createToken(@NonNull String userId) throws Exception {
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();

        // header Map
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("alg", "HS256");
        headerMap.put("typ", "JWT");

        // build token
        // param backups {iss:Service, aud:APP}
        return JWT.create().withHeader(headerMap) // header
                .withClaim("iss", "Service") // payload
                .withClaim("aud", "WXAPP")
                .withClaim("userId", userId)
                .withIssuedAt(iatDate) // sign time
                .withExpiresAt(expiresDate) // expire time
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static Map<String, Claim> verifyToken(@NonNull String token) throws UnsupportedEncodingException {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        return jwt.getClaims();
    }

    public static String getUserID(String token) throws UnsupportedEncodingException {
        Map<String, Claim> claims = verifyToken(token);
        Claim userIdClaim = claims.get("userId");

        if (null == userIdClaim || StringUtils.isEmpty(userIdClaim.asString())) {
            throw new UnauthorizedException("unauthorized");
        }
        return userIdClaim.asString();
    }
}

