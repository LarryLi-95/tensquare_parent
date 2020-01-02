package com.tensquare.qa.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @projectName:tensquare_parent
 * @packageName:com.tensquare.user.interceptor
 * @className:JwtInterceptor
 * @author:larry
 * @date:2019/12/31 16:10
 * @description:
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器!");
        // 无论如何都放行,具体能不能操作还是在具体操作中去判断
        //拦截器只是负责把头请求中包含token的令牌进行一个解析验证
        String header = request.getHeader("Authorization");
        if (!header.isEmpty()) {
            //如果包含有Authorization头信息,就对其进行解析
            if (header.startsWith("Bearer ")) {
                //得到token
                String token = header.substring(7);
                //对令牌进行解析
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if (roles != null || "admin".equals(roles)) {
                        request.setAttribute("claims_admin", token);
                    }
                    if (roles != null || "user".equals(roles)) {
                        request.setAttribute("claims_user", token);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("令牌不正确!");
                }
            }
        }
        return true;
    }

}
