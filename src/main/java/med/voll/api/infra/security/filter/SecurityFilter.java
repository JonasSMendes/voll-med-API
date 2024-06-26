package med.voll.api.infra.security.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.TokenService;
import med.voll.api.repository.UsuarioRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepositoy repositoy;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null){
            var subject = tokenService.getSubject(tokenJWT);
            var usuario = repositoy.findByLogin(subject);

            var authetication = new UsernamePasswordAuthenticationToken(usuario, null,usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authetication);
            System.out.println("Logado");
        }

        filterChain.doFilter(request, response);
    }



    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null){
            return  authorizationHeader.replace("Bearer ", "");
        }

       return null;
    }
}
