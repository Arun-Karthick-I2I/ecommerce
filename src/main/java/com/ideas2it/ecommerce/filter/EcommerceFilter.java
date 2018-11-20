package com.ideas2it.ecommerce.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ideas2it.ecommerce.common.enums.USER_ROLES;

public class EcommerceFilter {

/*    private static final String ADDITIONAL_FILES_PATTERN = 
            ".*(js|css|jpg|png|gif)";    

    public void init(FilterConfig filterConfig) throws ServletException {
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, 
            FilterChain chain) throws IOException, ServletException {
        
        Integer userId = null;
        USER_ROLES role = null;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String uri = httpRequest.getRequestURI();
        
        httpResponse.setHeader("Cache-Control", "no-cache, no-store");
        httpResponse.setHeader("Pragma", "no-cache");
        
        if (null != session) {
            userId = (Integer) session.getAttribute(Constants.LABEL_USER_ID);
        }

    }
        
    public void destroy() {
    }
*/
}
