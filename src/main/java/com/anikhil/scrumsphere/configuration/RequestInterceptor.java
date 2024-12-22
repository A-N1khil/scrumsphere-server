package com.anikhil.scrumsphere.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Log4j2
public class RequestInterceptor implements HandlerInterceptor {

    private final AtomicInteger requestCounter;
    private Instant start;

    private RequestInterceptor() {
        requestCounter = new AtomicInteger(0);
        start = Instant.now();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        LOGGER.info("Incoming Request: REQ-{} {} from {}", requestCounter.incrementAndGet(), request.getRequestURI(), request.getRemoteAddr());
        start = Instant.now();
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Duration duration = Duration.between(start, Instant.now());
        LOGGER.info("Request Completed in {} ms", duration.toMillis());
    }
}
