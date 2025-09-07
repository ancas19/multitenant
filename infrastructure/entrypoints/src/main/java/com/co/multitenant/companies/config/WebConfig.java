package com.co.multitenant.companies.config;

import com.co.multitenant.companies.filters.TenantFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final TenantFilter tenantFilter;

    public WebConfig(TenantFilter tenantFilter) {
        this.tenantFilter = tenantFilter;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tenantFilter);
    }
}
