package org.project.springbootbookmarket.config;

import org.project.springbootbookmarket.interceptor.AuditingInterceptor;
import org.project.springbootbookmarket.interceptor.MonitoringInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoggingConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MonitoringInterceptor());
        registry.addInterceptor(new AuditingInterceptor());
    }
}
