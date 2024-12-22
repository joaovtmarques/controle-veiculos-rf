package com.informatica.controle_veiculos.infra.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NotFoundFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    chain.doFilter(request, response);

    if (!httpRequest.getRequestURI().startsWith("/api/v1")
        && httpResponse.getStatus() == HttpServletResponse.SC_FORBIDDEN) {
      httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
      httpResponse.getWriter().write("Rota não encontrada");
    }
  }
}
