package com.example.financials.filter;

import com.example.financials.service.FundAllocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TimingFilter implements Filter{
    static Logger logger = LoggerFactory.getLogger(TimingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        long duration = System.currentTimeMillis() - start;
        logger.info("Request to " + ((HttpServletRequest) request).getRequestURI() + " took " + duration + " ms");
    }

    @Override
    public void destroy() {
    }
}
