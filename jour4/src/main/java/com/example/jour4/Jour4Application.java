package com.example.jour4;

import com.example.jour4.model.Person;
import com.example.jour4.model.User;
import com.example.jour4.repository.PersonRepository;
import com.example.jour4.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.List;

@SpringBootApplication
public class Jour4Application {

	public static void main(String[] args) {
		SpringApplication.run(Jour4Application.class, args);
	}

	// 📥📤 Gestion des Person depuis/vers le fichier JSON
	@Bean
	CommandLineRunner loadAndSaveJson(PersonRepository repository) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			File file = new File("personnes.json");
			TypeReference<List<Person>> typeRef = new TypeReference<>() {};

			// Charger le fichier JSON si présent
			if (file.exists()) {
				try {
					List<Person> personnes = mapper.readValue(file, typeRef);
					repository.saveAll(personnes);
					System.out.println("✅ Données chargées depuis personnes.json");
				} catch (Exception e) {
					System.out.println("❌ Erreur de lecture JSON : " + e.getMessage());
				}
			} else {
				System.out.println("⚠ Aucun fichier personnes.json trouvé. Il sera créé à la sauvegarde.");
			}

			// Sauvegarder la base actuelle vers JSON
			try {
				List<Person> personnes = repository.findAll();
				mapper.writerWithDefaultPrettyPrinter().writeValue(file, personnes);
				System.out.println("✅ Données sauvegardées dans personnes.json");
			} catch (Exception e) {
				System.out.println("❌ Erreur d'écriture JSON : " + e.getMessage());
			}
		};
	}

	// 🧑‍💼 Initialisation des utilisateurs en base
	@Bean
	CommandLineRunner initUsers(UserRepository userRepository) {
		return args -> {
			if (userRepository.findAll().isEmpty()) {
				userRepository.save(new User("admin", "1234", "ROLE_ADMIN"));
				userRepository.save(new User("user", "1234", "ROLE_USER"));
				System.out.println("✅ Utilisateurs ajoutés en base !");
			}
		};
	}
}
