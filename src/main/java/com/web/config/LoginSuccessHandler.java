package com.web.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.web.dao.AccountDao;
import com.web.dao.UserInfoDAO;
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

    @Autowired
    private UserInfoDAO userInfoDAO;



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        String username = authentication.getName();

        List<String> roles = (userInfoDAO.getUserRoles(username));

        String redirectURL = request.getContextPath();

        if (checkRole("ADMIN", roles)) {
            redirectURL = "admin/home";
        } else if (checkRole("USER", roles)) {
            redirectURL = "home";
        }

        response.sendRedirect(redirectURL);

    }



    public boolean checkRole(String role,List<String> list) {
        for(String s : list) {
            if(s.equals(role)) {
                return true;
            }
        }
        return false;
    }
}

