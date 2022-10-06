package com.ocbc.auctionservice.loggings;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static java.lang.String.format;

@Slf4j
@Order(1)
public class LoggingDiagnosticFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {
        MDC.clear();

        Long startTime = System.currentTimeMillis();
        try {
            MDC.putCloseable("requestId", getRequestId(request));
            log.info(format("Starting request %s", request.getRequestURI()));
            filterChain.doFilter(request, response);
        } finally {
            Long endTime = System.currentTimeMillis();
            Long duration = endTime - startTime;
            MDC.putCloseable("responseCode", response.getStatus() + "");
            MDC.putCloseable("responseTimeMillis", duration + "");
            log.info(format("Completed Request %s", request.getRequestURI()));
            log.info(format("Completed Request in %s duration", duration + ""));
        }
    }

    private String getRequestId(HttpServletRequest request) {
        String header = request.getHeader("X-B3-TraceId");
        if (header != null) {
            return header;
        }
        return UUID.randomUUID().toString();
    }
}
