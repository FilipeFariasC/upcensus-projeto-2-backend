package br.edu.ifpb.upcensus.infrastructure.security;

import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("cors")
@Component
public class CorsProperties {

	private Set<String> allowedOrigins;
	private Set<String> allowedHeaders;
	private Boolean allowCredentials;
	
	
	public Set<String> getAllowedOrigins() {
		return allowedOrigins;
	}
	public void setAllowedOrigins(Set<String> allowedOrigins) {
		this.allowedOrigins = allowedOrigins;
	}
	
	public Boolean getAllowCredentials() {
		return allowCredentials;
	}
	public void setAllowCredentials(Boolean allowCredentials) {
		this.allowCredentials = allowCredentials;
	}
	
	public Set<String> getAllowedHeaders() {
		return allowedHeaders;
	}
	public void setAllowedHeaders(Set<String> allowedHeaders) {
		this.allowedHeaders = allowedHeaders;
	}
	@Override
	public String toString() {
		return String.format("{allowed_origins: %s, allowed_headers: %s, allow_credentials: %s}", allowedOrigins,
				allowedHeaders, allowCredentials);
	}
	
	
	
}
