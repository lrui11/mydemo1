package com.example.coolshark.filter;

import com.example.coolshark.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "MyFilter",urlPatterns = {"/insertProduct.html","/admin.html"})
public class Myfilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //执行过滤器
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        HttpSession session = httpServletRequest.getSession();

        User user = (User) session.getAttribute("user");

        if (user!=null){//代表登录过
            filterChain.doFilter(servletRequest,servletResponse);//放行
        }else{
            httpServletResponse.sendRedirect("/login.html");
        }

        System.out.println("执行过滤器");
    }

    @Override
    public void destroy() {

    }
}
