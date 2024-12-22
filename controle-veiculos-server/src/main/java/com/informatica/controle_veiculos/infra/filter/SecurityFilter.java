package com.informatica.controle_veiculos.infra.filter;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.informatica.controle_veiculos.data.exception.NotFoundException;
import com.informatica.controle_veiculos.domain.model.Admin;
import com.informatica.controle_veiculos.domain.usecase.token.TokenUseCase;
import com.informatica.controle_veiculos.infra.repository.AdminRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  TokenUseCase tokenService;

  @Autowired
  AdminRepository adminRepository;

  @SuppressWarnings("unchecked")
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    var token = this.recoverToken(request);
    var login = tokenService.validateToken(token);

    if (login != null) {
      Admin admin = adminRepository.findByEmail(login)
          .orElseThrow(() -> new NotFoundException("Usuário não encontrado!"));
      ArrayList<SimpleGrantedAuthority> authorities = (ArrayList<SimpleGrantedAuthority>) admin.getAuthorities();
      var authentication = new UsernamePasswordAuthenticationToken(admin, null, authorities);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(request, response);
  }

  private String recoverToken(HttpServletRequest request) {
    var authHeader = request.getHeader("Authorization");
    if (authHeader == null)
      return null;
    return authHeader.replace("Bearer ", "");
  }
}
