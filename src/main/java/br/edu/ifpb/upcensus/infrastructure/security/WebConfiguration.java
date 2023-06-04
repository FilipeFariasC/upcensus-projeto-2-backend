package br.edu.ifpb.upcensus.infrastructure.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.edu.ifpb.upcensus.infrastructure.util.HttpUtils;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	
	private final CorsProperties corsProperties;
	
	
	public WebConfiguration(final CorsProperties corsProperties) {
		super();
		this.corsProperties = corsProperties;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowCredentials(corsProperties.getAllowCredentials())
			.allowedMethods(HttpUtils.allHttpMethods())
			.allowedOrigins(corsProperties.getAllowedOrigins().toArray(new String[] {}))
			.allowedHeaders(corsProperties.getAllowedHeaders().toArray(new String[] {}));
	}
	
	
}
