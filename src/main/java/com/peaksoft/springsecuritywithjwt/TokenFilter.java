package com.peaksoft.springsecuritywithjwt;

import com.peaksoft.springsecuritywithjwt.model.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.EntityNotFoundException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class TokenFilter  extends OncePerRequestFilter {

     private  final  TokenUtilities tokenUtilities;

     private  final  UserRepository userRepository;
    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) {

        String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {

            String token = tokenHeader.substring(7);
            if (StringUtils.hasText(token)) {
                try {
                    String email = tokenUtilities.validateToken(token);
                    User user = userRepository.findByEmail(email);

                    if (user == null) {
                        throw new EntityNotFoundException("Entity not found");
                    }
                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(
                                    user.getEmail(),
                                    null,
                                    Collections.singleton(user.getRole())
                            )

                    );

                } catch (Exception e) {
                    response.sendError(403, e.getMessage());
                    return;
                }
            }
        }
            filterChain.doFilter(request, response);

        }

    }

