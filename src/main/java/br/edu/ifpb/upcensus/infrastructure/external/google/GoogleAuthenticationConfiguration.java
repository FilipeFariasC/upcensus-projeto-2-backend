package br.edu.ifpb.upcensus.infrastructure.external.google;

import java.io.IOException;
import java.util.Objects;

import org.jboss.jandex.Main;
import org.springframework.context.annotation.Configuration;

import com.google.api.services.forms.v1.FormsScopes;
import com.google.auth.oauth2.GoogleCredentials;

@Configuration
public class GoogleAuthenticationConfiguration {
	
	private static final String CREDENTIALS_FILE = "google_credentials.json";
	

	public static String getAccessToken() throws IOException {
	      GoogleCredentials credential = GoogleCredentials.fromStream(Objects.requireNonNull(
	              Main.class.getResourceAsStream(CREDENTIALS_FILE))).createScoped(FormsScopes.all());
	      return credential.getAccessToken() != null ?
	              credential.getAccessToken().getTokenValue() :
	              credential.refreshAccessToken().getTokenValue();
	}
	
	public static GoogleCredentials getGoogleCredentials() throws IOException {
		GoogleCredentials credential = GoogleCredentials.fromStream(Objects.requireNonNull(
	              Main.class.getResourceAsStream(CREDENTIALS_FILE))).createScoped(FormsScopes.all());
		return credential;
	}
	
	
}
