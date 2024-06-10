package com.example.serversidespring.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ContentSecurityPolicyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        // No initialization required
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Content-Security-Policy", "default-src 'self'; " +
                "connect-src 'self' http://localhost:3000 ws://localhost:3000 wss://localhost:3000 https://localhost:3000; " +
                "script-src 'self' 'unsafe-inline' http://localhost:3000; " +
                "img-src 'self'; " +
                "style-src 'self' 'unsafe-inline';");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // No resource cleanup required
    }
}
