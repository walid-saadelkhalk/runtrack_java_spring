package com.example.jour4.controller;

import com.example.jour4.dto.PersonDTO;
import com.example.jour4.model.Person;
import com.example.jour4.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@Controller
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // 👉 Liste des personnes
    @GetMapping("/persons")
    public String showPersons(Model model) {
        List<Person> personnes = personService.findAll();
        model.addAttribute("personnes", personnes);
        return "persons";
    }

    // 👉 Formulaire d'ajout
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("person", new Person());
        return "form";
    }

    // 👉 Sauvegarde de la personne depuis le formulaire
    @PostMapping("/add")
    public String addPerson(@ModelAttribute Person person) {
        personService.save(person);
        return "redirect:/persons";
    }

    // 👉 Affichage de la page de login personnalisée
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // correspond à login.html dans /templates
    }

    // 👉 Export JSON (sans ID)
    @GetMapping("/export")
    @ResponseBody
    public String exportJson() {
        try {
            List<PersonDTO> personnesDTO = personService.findAllAsDTO();

            ObjectMapper mapper = new ObjectMapper();
            File file = new File("personnes.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, personnesDTO);

            return "✅ Données exportées sans les ID !";
        } catch (Exception e) {
            return "❌ Erreur lors de l'export : " + e.getMessage();
        }
    }
}
