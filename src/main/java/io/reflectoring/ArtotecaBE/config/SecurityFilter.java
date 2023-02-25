package io.reflectoring.ArtotecaBE.config;

import com.google.firebase.auth.FirebaseAuthException;
import io.reflectoring.ArtotecaBE.user.UserDetailsImpl;
import io.reflectoring.ArtotecaBE.utils.JwtTokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        UserDetailsImpl user = new UserDetailsImpl();
        final String jwtToken= request.getHeader("Authorisation");

        if(jwtToken != null && jwtToken.startsWith("Bearer")){
            try {
                user = jwtTokenUtils.getUserDetails(jwtToken);
            } catch (FirebaseAuthException e) {
                throw new RuntimeException(e);
            }
        }
        if(SecurityContextHolder.getContext().getAuthentication() == null){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
