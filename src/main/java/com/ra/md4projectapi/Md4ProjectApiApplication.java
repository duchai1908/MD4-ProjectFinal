package com.ra.md4projectapi;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class Md4ProjectApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Md4ProjectApiApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	//firebase
	@Bean
	public Storage storage() throws IOException {
		InputStream serviceAccount = new ClassPathResource("firebase-config.json").getInputStream();
		return StorageOptions.newBuilder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build()
				.getService();
	}


}
