package com.status.server.global.filter;

import com.status.server.global.domain.Token;
import com.status.server.global.exception.NoUserException;
import com.status.server.global.service.TokenService;
import com.status.server.user.domain.User;
import com.status.server.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class JwtAuthFilter extends GenericFilterBean {
    private final TokenService tokenService;
    private final UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String access_token = ((HttpServletRequest)request).getHeader("access_token");
        String refresh_token = ((HttpServletRequest)request).getHeader("refresh_token");
        logger.trace("this is access_token !!!!!!! {}", access_token);
        logger.trace("this is refresh !!!!!!! {}", refresh_token);

        if(refresh_token != null && tokenService.verifyToken(refresh_token)){
            logger.debug("토큰 두개 모두 보냄 즉, 갱신 코트 :: {}", refresh_token);
            Long id = tokenService.getId(refresh_token);

            User user = userRepository.findById(id).orElseThrow(()-> new NoUserException("토큰의 사용자가 없습니다."));
            logger.debug("user Info : {}",user);

            if(user.getRefreshToken().equals(refresh_token)){
                logger.debug("저장된 re와 보낸 re가 일치 합니다.");

                Token newToken = tokenService.generateToken(user.getId(), user.getName(), "USER");

                ((HttpServletResponse)response).addHeader("access_token",newToken.getAccess_token());
            } else {
                logger.debug("저장된 re와 보낸 re가 불일치 합니다.");
                logger.debug("저장된 re {}",user.getRefreshToken());
                logger.debug("보낸 re {}",refresh_token);
                throw new IllegalArgumentException("JWT 토큰이 잘못되었습니다.");
            }

            Authentication auth = getAuthentication(user);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } else if (access_token != null && tokenService.verifyToken(access_token)) {
            logger.debug("토큰 한개 보냄 정상 상황:: {}", refresh_token);
            Long pk = tokenService.getId(access_token);

            User user = userRepository.findById(pk).get();
            logger.debug("user Info : {}",user);

            Authentication auth = getAuthentication(user);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }

    // 정상 토큰이면 해당 토큰으로 Authentication 을 가져와서 SecurityContext 에 저장
    public Authentication getAuthentication(User user) {
        return new UsernamePasswordAuthenticationToken(user.getId(), "",
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }

}