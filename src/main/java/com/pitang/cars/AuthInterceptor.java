package com.pitang.cars;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class AuthInterceptor implements HandlerInterceptor {
	
	 private String secretKey = "awfx78dd78454545415251x";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestUri = request.getRequestURI();
        if (requestUri.startsWith("/api/cars")) {
            String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring(7);
           
                try {
                    Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
                    System.out.println("Token válido para o usuário: " + claims.getSubject());
                    request.setAttribute("claims", claims);
                    return true;
                } catch (SignatureException e) {
                    // Token inválido
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token JWT inválido");
                    return false;
                }
            
            
            
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token JWT não fornecido");
                return false; // Interrompe a execução do handler
            }
        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // Não é necessário implementar nada aqui se não for necessário
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {
        // Não é necessário implementar nada aqui se não for necessário
    }
}