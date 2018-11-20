package com.ideas2it.ecommerce.filter;

import java.io.IOException;

import javax.servlet.Filter;
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

/**
 * <p>
 * The {@code EcommerceFilter} filters the incoming requests based on the
 * presence of user ID and restricts user access based on their role. i.e Admin
 * can only access pages that are meant for their use, similarly seller and
 * customer can access only their corresponding pages.
 * </p>
 * 
 * @author Arun Karthick.J
 */
public class EcommerceFilter implements Filter {

    private static final String ADDITIONAL_FILES_PATTERN = ".*(js|css|jpg|png|gif|woff2)";
    private static final String ADDRESS_PATH = "Address";
    private static final String ADMIN_HOME_PATH = "/category/display";
    private static final String ADMIN_PATH = "/admin/";
    private static final String CATEGORY_PATH = "/category/";
    private static final String CUSTOMER_PATH = "/customer/";
    private static final String INITIAL_PATH = "/ecommerce";
    private static final String PRODUCT_PAGE = "/customer/productPage/";
    private static final String PRODUCT_PATH = "/product/";
    private static final String PROJECT_PATH = "/ecommerce/";
    private static final String REGISTER_CUSTOMER = "registerCustomer";
    private static final String REGISTER_SELLER = "registerSeller";
    private static final String SELLER_HOME_PATH = "/seller/home";
    private static final String SELLER_PATH = "/seller/";

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

        // Fetch UserID and User Role if the session is available
        if (null != session) {
            userId = (Integer) session.getAttribute(Constants.LABEL_USER_ID);
            role = (USER_ROLES) session.getAttribute(Constants.LABEL_ROLE);
        }

        if (uri.endsWith(INITIAL_PATH) || uri.endsWith(PROJECT_PATH)
                || uri.endsWith(Constants.LABEL_LOGIN)
                || uri.endsWith(SELLER_PATH) || uri.endsWith(ADMIN_PATH)) {
            if (null == userId) {

                // When the page is accessed for the first time
                chain.doFilter(httpRequest, httpResponse);

            } else {

                /*
                 * While the user is logged in already ,he should automatically
                 * be redirected to the corresponding home page
                 */
                if (USER_ROLES.ADMIN == role) {
                    httpRequest.getRequestDispatcher(ADMIN_HOME_PATH)
                            .forward(httpRequest, httpResponse);
                } else if (USER_ROLES.CUSTOMER == role) {
                    chain.doFilter(httpRequest, httpResponse);
                } else {
                    httpRequest.getRequestDispatcher(SELLER_HOME_PATH)
                            .forward(httpRequest, httpResponse);
                }
            }
        } else if (uri.endsWith(Constants.LABEL_LOGOUT)
                || uri.endsWith(REGISTER_CUSTOMER)
                || uri.endsWith(REGISTER_SELLER)
                || uri.endsWith(PRODUCT_PAGE)
                || uri.matches(ADDITIONAL_FILES_PATTERN)) {

            /*
             * Additional files such as javascripts,stylesheets, images are
             * allowed to pass through filter. When user logs out or when he
             * accesses the registration page, the request must be allowed
             */
            chain.doFilter(httpRequest, httpResponse);
        } else if (uri.endsWith(ADDRESS_PATH) && null != userId) {

            /*
             * Address related operations are common to all users needs to be
             * allowed only when the user is logged in.
             */
            chain.doFilter(httpRequest, httpResponse);
        } else if ((null != userId) && (USER_ROLES.ADMIN == role)) {

            // Restricting Admin Access to the specified pages
            if (uri.contains(ADMIN_PATH) || uri.contains(CATEGORY_PATH)
                    || uri.contains(PRODUCT_PATH)) {
                chain.doFilter(httpRequest, httpResponse);
            } else {
                httpRequest.setAttribute(Constants.LABEL_MESSAGE,
                        Constants.MSG_UNAUTHORISED_ACCESS);
                httpRequest.getRequestDispatcher(ADMIN_HOME_PATH)
                        .forward(httpRequest, httpResponse);
            }
        } else if ((null != userId) && (USER_ROLES.CUSTOMER == role)) {

            // Restricting Customer Access to the specified pages
            if (uri.contains(CUSTOMER_PATH)) {
                chain.doFilter(httpRequest, httpResponse);
            } else {
                httpRequest.setAttribute(Constants.LABEL_MESSAGE,
                        Constants.MSG_UNAUTHORISED_ACCESS);
                httpRequest.getRequestDispatcher("/").forward(httpRequest,
                        httpResponse);
            }
        } else if ((null != userId) && (USER_ROLES.SELLER == role)) {

            // Restricting Seller Access to the specified pages
            if (uri.contains(SELLER_PATH)) {
                chain.doFilter(httpRequest, httpResponse);
            } else {
                httpRequest.setAttribute(Constants.LABEL_MESSAGE,
                        Constants.MSG_UNAUTHORISED_ACCESS);
                httpRequest.getRequestDispatcher(SELLER_HOME_PATH)
                        .forward(httpRequest, httpResponse);
            }
        } else {

            // When session has expired, the user is redirected to home page
            session = httpRequest.getSession(Boolean.TRUE);
            session.setAttribute(Constants.LABEL_MESSAGE,
                    Constants.MSG_SESSION_EXPIRED);
            httpResponse.sendRedirect(PROJECT_PATH);
        }
    }

    public void destroy() {
    }
}
