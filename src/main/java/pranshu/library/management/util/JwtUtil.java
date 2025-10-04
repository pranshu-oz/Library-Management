package pranshu.library.management.util;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

	private final String SECRET="%&kl33d9l#@m&$S$&*(dkowenv$#%^^%";
	
	private final long EXPIRATION= 1000*60;
	
	private Key getKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}
	
	public String generateToken(String username) {
	
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
				.signWith(getKey(),SignatureAlgorithm.HS256).compact();
	}
	
	public String extractUsername(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
	public boolean validateToken(String token, String username) {
		
		return (username.equals(extractUsername(token)) && !isExpired(token));
	}
	
	private boolean isExpired(String token) {
		return Jwts.parserBuilder()
		.setSigningKey(getKey())
		.build()
		.parseClaimsJws(token)
		.getBody()
		.getExpiration().before(new Date());
	}
	
	public Date getExpireTime(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getExpiration();
	}
}
