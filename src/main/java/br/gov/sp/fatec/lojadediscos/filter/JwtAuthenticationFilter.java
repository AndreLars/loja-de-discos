package br.gov.sp.fatec.lojadediscos.filter;

import br.gov.sp.fatec.lojadediscos.utils.JwtUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(
            ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authorization != null) {
                Authentication credentials =
                        JwtUtils.parseToken(authorization.replace("Bearer ", ""));
                SecurityContextHolder.getContext().setAuthentication(credentials);
            }
            filterChain.doFilter(request, servletResponse);
        } catch (Throwable t) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, t.getMessage());
        }
    }
}
