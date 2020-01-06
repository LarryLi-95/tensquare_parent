package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.protocol.RequestContent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @projectName:tensquare_parent
 * @packageName:com.tensquare.web.filter
 * @className:WebFilter
 * @author:larry
 * @date:2020/1/3 11:22
 * @description:
 */
@Component
public class WebFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //得到request上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        //得到request域
        HttpServletRequest request = requestContext.getRequest();
        //得到头信息
        String header = request.getHeader("Authorization");
        //判断是否有头信息
        if (header != null && !"".equals(header)) {
            //把头信息继续往下传
            requestContext.addZuulRequestHeader("Authorization", header);
        }
        return null;
    }
}
