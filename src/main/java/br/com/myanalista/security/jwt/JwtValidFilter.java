//package br.com.myanalista.security.jwt;
//
//import br.com.myanalista.configs.CP;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class JwtValidFilter extends BasicAuthenticationFilter {
//
//    public JwtValidFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain chain) throws IOException, ServletException {
//
//        String attribute = request.getHeader(CP.HEADER_ATTRIBUTE);
//
//        if (attribute == null) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        if (!attribute.startsWith(CP.ATTIBUTE_BEARER)) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        String token = attribute.replace(CP.ATTIBUTE_BEARER, "");
//        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);
//
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        chain.doFilter(request, response);
//    }
//
//    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
//
//        String user = JWT.require(Algorithm.HMAC512(CP.SIGNATURE_KEY))
//                .build()
//                .verify(token)
//                .getSubject();
//
//        if (user == null) {
//            return null;
//        }
//
//        return new UsernamePasswordAuthenticationToken(user,null, new ArrayList<>());
//    }
//}
