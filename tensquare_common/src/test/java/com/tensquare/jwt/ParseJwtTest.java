package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

/**
 * @projectName:tensquare_parent
 * @packageName:com.tensquare.jwt
 * @className:ParseJwtTest
 * @author:larry
 * @date:2019/12/31 14:57
 * @description:
 */
public class ParseJwtTest {
    public static void main(String[] args) {
        Claims claims = Jwts.parser().setSigningKey("itcast")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLlsI_pqawiLCJpYXQiOjE1Nzc3NzYzMTEsImV4cCI6MTU3Nzc3NjM3MSwicm9sZSI6ImFkbWluIn0.QfOKkV4eesxZk07RurFXUSpBUn_jYm4aCDugp80fH6Q")
                .getBody();
        System.out.println("用户id:" + claims.getId());
        System.out.println("用户名:" + claims.getSubject());
        System.out.println("登陆时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()));
        System.out.println("过期时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration()));
        System.out.println("用户角色:" + claims.get("role"));
    }
}
