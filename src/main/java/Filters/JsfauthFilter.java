package Filters;

import Dtos.RoleDto;
import Securities.JwtPrincipal;
import Services.JwtService;
import Services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.Set;
import java.util.UUID;

@WebFilter("*.xhtml")
public class JsfauthFilter implements Filter {

    @Inject
    private JwtService jwtService;

    @Inject
    private UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        if (session != null) {
            String token = (String) session.getAttribute("authToken");
            if (token != null) {
                try {
                    Claims claims = jwtService.getClaimsFromToken(token);
                    UUID userId = jwtService.extractUserId(claims);
                    String username = jwtService.extractUsername(claims);
                    Set<RoleDto> roles = userService.getUserRoles(userId);
                    JwtPrincipal principal = new JwtPrincipal(userId, username, roles);

                    // Wrap the request with custom principal
                    HttpServletRequest wrappedRequest = new CustomHttpServletRequestWrapper(httpRequest, principal);
                    chain.doFilter(wrappedRequest, response);
                    return;
                } catch (JwtException e) {
                    // Invalid token, continue without setting principal
                }
            }
        }

        chain.doFilter(request, response);
    }

    // Other filter methods
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

    private static class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {
        private final JwtPrincipal principal;

        public CustomHttpServletRequestWrapper(HttpServletRequest request, JwtPrincipal principal) {
            super(request);
            this.principal = principal;
        }

        @Override
        public Principal getUserPrincipal() {
            return principal;
        }
    }
}
