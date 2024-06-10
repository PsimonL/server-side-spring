//package com.example.serversidespring.config;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.FilterConfig;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.UUID;
//
//@Component
//public class CustomCsrfFilter implements Filter {
//
////    @Value("${csrf.token.cookie.name}")
//    private String csrfTokenCookieName;
//
////    @Value("${csrf.token.header.name}")
//    private String csrfTokenHeaderName;
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//        // No initialization required
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        if (httpRequest.getMethod().equalsIgnoreCase("GET")) {
//            String csrfToken = UUID.randomUUID().toString();
//            Cookie csrfCookie = new Cookie(csrfTokenCookieName, csrfToken);
//            csrfCookie.setPath("/");
//            csrfCookie.setHttpOnly(false);
//            csrfCookie.setSecure(false);
//            httpResponse.addCookie(csrfCookie);
//        } else {
//            String csrfToken = httpRequest.getHeader(csrfTokenHeaderName);
//            if (csrfToken == null || !csrfToken.equals(getCsrfTokenFromCookies(httpRequest.getCookies()))) {
//                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "CSRF token missing or invalid");
//                return;
//            }
//        }
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//        // No resource cleanup required
//    }
//
//    private String getCsrfTokenFromCookies(Cookie[] cookies) {
//        if (cookies == null) return null;
//        for (Cookie cookie : cookies) {
//            if (csrfTokenCookieName.equals(cookie.getName())) {
//                return cookie.getValue();
//            }
//        }
//        return null;
//    }
//}
