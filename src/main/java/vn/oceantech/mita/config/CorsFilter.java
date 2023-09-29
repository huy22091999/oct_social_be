package vn.oceantech.mita.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Integer.MIN_VALUE)
public class CorsFilter implements Filter {
    @Autowired
    private Environment env;

    public CorsFilter() {
    }

    private Boolean isInWhileList(String clientUrl, String allowedOrigins) {
        return allowedOrigins.contains(clientUrl);
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        String allowedHeaders = this.env.getProperty("endpoints.cors.allowed-headers");
        String allowedMethods = this.env.getProperty("endpoints.cors.allowed-methods");
        String allowedOrigins = this.env.getProperty("endpoints.cors.allowed-origins");
        String allowedCredentials = this.env.getProperty("endpoints.cors.allow-credentials");
        String maxAge = this.env.getProperty("endpoints.cors.max-age");
        HttpServletResponse response = (HttpServletResponse)res;
        response.setHeader("Access-Control-Allow-Methods", allowedMethods);
        response.setHeader("Access-Control-Allow-Headers", allowedHeaders);
        response.setHeader("Access-Control-Allow-Credentials", allowedCredentials);
        response.setHeader("Access-Control-Max-Age", maxAge);
        response.setHeader("Access-Control-Allow-Origin", allowedOrigins);
        if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest)req).getMethod())) {
            response.setStatus(200);
        } else {
            chain.doFilter(req, res);
        }

    }

    public void destroy() {
    }

    public void init(FilterConfig config) throws ServletException {
    }
}
