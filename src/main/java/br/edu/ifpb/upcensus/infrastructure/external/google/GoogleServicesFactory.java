package br.edu.ifpb.upcensus.infrastructure.external.google;


import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.context.annotation.Bean;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.forms.v1.Forms;

public class GoogleServicesFactory {
	
	private static final String APPLICATION_NAME = "teste";
	
	private static final JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
	
	@Bean
	public static Drive createDriveService() throws GeneralSecurityException, IOException {
		Drive drv = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                jsonFactory, null)
                .setApplicationName(APPLICATION_NAME).build();
		return drv;
		
	}
	
	@Bean
	public static Forms createFormsService() throws GeneralSecurityException, IOException {
		
		Forms form = new Forms.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                jsonFactory, null)
                .setApplicationName(APPLICATION_NAME).build();
		return form;
		
	}
	

}
