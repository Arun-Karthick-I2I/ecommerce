package com.ideas2it.ecommerce.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.common.enums.USER_ROLES;

public class EcommerceFilter {

    private static final String ADDITIONAL_FILES_PATTERN = ".*(js|css|jpg|png|gif|woff2)";

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        Integer userId = null;
        USER_ROLES role = null;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(Boolean.FALSE);
        String uri = httpRequest.getRequestURI();

        httpResponse.setHeader("Cache-Control", "no-cache, no-store");
        httpResponse.setHeader("Pragma", "no-cache");

        if (null != session) {
            userId = (Integer) session.getAttribute(Constants.LABEL_USER_ID);
            role = (USER_ROLES) session.getAttribute(Constants.LABEL_ROLE);
        }

        if (uri.endsWith("/ecommerce") || uri.endsWith("/ecommerce/")
                || uri.endsWith("login")) {
            if (null == userId) {

                // When the page is accessed for the first time
                chain.doFilter(httpRequest, httpResponse);

            } else {

                /*
                 * While the user is logged in already ,he should automatically
                 * be redirected to the corresponding home page
                 */
                if (USER_ROLES.ADMIN == role) {
                    httpRequest.getRequestDispatcher("/category/display")
                            .forward(httpRequest, httpResponse);
                } else if (USER_ROLES.CUSTOMER == role) {
                    chain.doFilter(httpRequest, httpResponse);
                } else {
                    httpRequest.getRequestDispatcher("/seller/home")
                            .forward(httpRequest, httpResponse);
                }
            }
        } else if (uri.endsWith("logout") || uri.endsWith("registerCustomer")
                || uri.endsWith("registerSeller")
                || uri.matches(ADDITIONAL_FILES_PATTERN)) {

            /*
             * Additional files such as javascripts,stylesheets, images are
             * allowed to pass through filter. When user logs out or when he
             * accesses the registration page, the request must be allowed
             */
            chain.doFilter(httpRequest, httpResponse);
        } else if (uri.endsWith("address") && null != userId) {

            /*
             * Address related operations are common to all users needs to be
             * allowed only when the user is logged in.
             */
            chain.doFilter(httpRequest, httpResponse);
        } else if ((null != userId) && (USER_ROLES.ADMIN == role)) {

            // Restricting Admin Access to the specified pages
            if (uri.contains("/admin/") || uri.contains("/category/")
                    || uri.contains("/product/")) {
                chain.doFilter(httpRequest, httpResponse);
            } else {
                httpRequest.setAttribute(Constants.LABEL_MESSAGE,
                        Constants.MSG_UNAUTHORISED_ACCESS);
                httpRequest.getRequestDispatcher("/category/display")
                        .forward(httpRequest, httpResponse);
            }
        } else if ((null != userId) && (USER_ROLES.CUSTOMER == role)) {

            // Restricting Customer Access to the specified pages
            if (uri.contains("/customer/")) {
                chain.doFilter(httpRequest, httpResponse);
            } else {
                httpRequest.setAttribute(Constants.LABEL_MESSAGE,
                        Constants.MSG_UNAUTHORISED_ACCESS);
                httpRequest.getRequestDispatcher("/").forward(httpRequest,
                        httpResponse);
            }
        } else if ((null != userId) && (USER_ROLES.SELLER == role)) {

            // Restricting Seller Access to the specified pages
            if (uri.contains("/seller/")) {
                chain.doFilter(httpRequest, httpResponse);
            } else {
                httpRequest.setAttribute(Constants.LABEL_MESSAGE,
                        Constants.MSG_UNAUTHORISED_ACCESS);
                httpRequest.getRequestDispatcher("/seller/home")
                        .forward(httpRequest, httpResponse);
            }
        } else {

            // When session has expired, the user is redirected to home page
            session = httpRequest.getSession(Boolean.TRUE);
            session.setAttribute(Constants.LABEL_MESSAGE,
                    Constants.MSG_SESSION_EXPIRED);
            httpResponse.sendRedirect("/");
        }
    }

    public void destroy() {
    }

}
