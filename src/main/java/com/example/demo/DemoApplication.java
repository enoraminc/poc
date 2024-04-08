package com.example.demo;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		FileInputStream serviceAccount;

		FirebaseOptions options;
		try {
			serviceAccount = new FileInputStream("serviceAccountKey.json");
			options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();

			FirebaseApp.initializeApp(options);
		} catch (IOException e) {
			e.printStackTrace();
		}

		SpringApplication.run(DemoApplication.class, args);
	}

}
