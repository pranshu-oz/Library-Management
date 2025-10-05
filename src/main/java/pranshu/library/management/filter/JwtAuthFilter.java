package pranshu.library.management.filter;

import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pranshu.library.management.util.JwtUtil;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{

	private final JwtUtil jwtUtil=new JwtUtil();
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		String authHeader=request.getHeader("Authorization");
		String username=null;
		String token=null;
		String path = request.getRequestURI();
		
		if (path.startsWith("/login") || path.startsWith("/role")) {
	        filterChain.doFilter(request, response);
	        return;
	    }
		
		if(authHeader !=null && authHeader.startsWith("Bearer ")) {
			
			token = authHeader.substring(7);
			username= jwtUtil.extractUsername(token);
		}
		else if (request.getCookies() != null) {
	        for (Cookie cookie : request.getCookies()) {
	            if ("jwt".equals(cookie.getName())) {
	                token = cookie.getValue();
	                username= jwtUtil.extractUsername(token);
	            }
	        }
	    }
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			if(jwtUtil.validateToken(token, username)) {
				UsernamePasswordAuthenticationToken auth =new UsernamePasswordAuthenticationToken(username,null,null);
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}else {
			response.sendRedirect("/login");
		}
		
		filterChain.doFilter(request, response);
		
	}

}
