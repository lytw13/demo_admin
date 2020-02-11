package com.lytw13.demo.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RemeberFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        // 获取session中的subject
        Subject user = SecurityUtils.getSubject();

        // 判断是不是通过记住我登录
        if( !user.isAuthenticated() && user.isRemembered()) {
            user.getSession().setAttribute("SESSION_USER", user.getPrincipal());
        }
        return super.onLoginSuccess(token, subject, request, response);
    }
}
