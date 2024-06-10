package com.example.serversidespring.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.verify;

class ContentSecurityPolicyFilterTest {

    private ContentSecurityPolicyFilter filter;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain chain;

    @BeforeEach
    void setUp() {
        filter = new ContentSecurityPolicyFilter();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        chain = Mockito.mock(FilterChain.class);
    }

    @Test
    void testDoFilter() throws IOException, ServletException {
        filter.doFilter(request, response, chain);
        verify(response).setHeader("Content-Security-Policy", "default-src 'self'; " +
                "connect-src 'self' http://localhost:3000 ws://localhost:3000 wss://localhost:3000 https://localhost:3000; " +
                "script-src 'self' 'unsafe-inline' http://localhost:3000; " +
                "img-src 'self'; " +
                "style-src 'self' 'unsafe-inline';");
        verify(chain).doFilter(request, response);
    }
}
