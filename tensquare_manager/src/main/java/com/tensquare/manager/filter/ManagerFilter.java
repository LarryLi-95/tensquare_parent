package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @projectName:tensquare_parent
 * @packageName:com.tensquare.manager.filter
 * @className:ManagerFilter
 * @author:larry
 * @date:2020/1/3 11:07
 * @description:
 */
@Component
public class ManagerFilter extends ZuulFilter {
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 在请求前或者后执行,前执行pre 后执行post
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 多个过滤器的执行顺序,数字越小,优先级越高
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 当前过滤器是否开启 true为开启
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器内执行的操作,return任何Object的值都表示继续执行
     * setsendnullResponse表示不再继续执行
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("经过后台过滤器!");
        RequestContext currentContext = RequestContext.getCurrentContext();
        //request域
        HttpServletRequest request = currentContext.getRequest();
        if ("OPTIONS".equals(request.getMethod())) {
            return null;
        }
        if (request.getRequestURL().indexOf("login") > 0) {
            return null;
        }
        //得到头信息
        String header = request.getHeader("Authorization");
        if (header != null && !"".equals(header)) {
            if (header.startsWith("Bearer ")) {
                String token = header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if ("admin".equals(roles)) {
                        //把头信息转发下去,并且放行
                        currentContext.addZuulRequestHeader("Authorization", header);
                        return null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    currentContext.setSendZuulResponse(false); //终止运行
                }
            }
        }
        currentContext.setSendZuulResponse(false); //终止运行
        currentContext.setResponseStatusCode(403);
        currentContext.setResponseBody("权限不足!");
        currentContext.getResponse().setContentType("text/html;charset=utf-8");
        return null;
    }
}
