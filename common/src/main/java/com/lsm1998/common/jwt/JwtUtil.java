package com.lsm1998.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtUtil
{
    private static final long EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000;

    public static String signature(String tokenSecret, Map<String, Object> payloadClaims)
    {
        try
        {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            JWTCreator.Builder builder = JWT.create();
            payloadClaims.forEach((k, v) ->
            {
                if (v instanceof String)
                {
                    builder.withClaim(k, (String) v);
                } else if (v instanceof Integer)
                {
                    builder.withClaim(k, (Integer) v);
                } else if (v instanceof Double)
                {
                    builder.withClaim(k, (Double) v);
                } else if (v instanceof Boolean)
                {
                    builder.withClaim(k, (Boolean) v);
                } else if (v instanceof Long)
                {
                    builder.withClaim(k, (Long) v);
                } else if (v instanceof Date)
                {
                    builder.withClaim(k, (Date) v);
                } else if (v instanceof List)
                {
                    builder.withClaim(k, (List<?>) v);
                }
            });
            // 返回token字符串
            return builder.withHeader(header)
                    .withClaim("createdTime", new Date())
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, Claim> parse(String tokenSecret, String token)
    {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims();
        } catch (Exception e)
        {
            return null;
        }
    }
}
