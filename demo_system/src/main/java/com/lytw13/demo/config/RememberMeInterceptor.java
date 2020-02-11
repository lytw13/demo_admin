package com.lytw13.demo.config;

import com.lytw13.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 拦截rememberMe的请求，添加user到session中
 * @author echo
 *
 */
public class RememberMeInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    public RememberMeInterceptor() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 获取session中的subject
        Subject user = SecurityUtils.getSubject();

        // 判断是不是通过记住我登录
        if( !user.isAuthenticated() && user.isRemembered()) {
            user.getSession().setAttribute("SESSION_USER", user.getPrincipal());

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

}