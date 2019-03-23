package me.ersystem.controller;

import me.ersystem.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author MotYim mohamed.motyim@gmail.com
 * @since 23-Mar-2019
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        System.out.println("## Session");
        HttpSession session = request.getSession();
        if(session == null || session.getAttribute("loginUser") ==null){
            response.sendRedirect("/login");
        }
        return true;
    }
}
