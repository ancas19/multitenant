package com.co.multitenant.companies.filters;

import com.co.multitenant.companies.domain.utils.TenantContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

import static com.co.multitenant.companies.domain.enums.Constants.X_TENANT;

@Component
public class TenantFilter implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        String  tenantHeader = request.getHeader(X_TENANT.getValue());
        if (Objects.nonNull(tenantHeader)) {
            TenantContext.setCurrentTenant(tenantHeader);
            return true;
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return false;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        TenantContext.setCurrentTenant("");
    }
}
