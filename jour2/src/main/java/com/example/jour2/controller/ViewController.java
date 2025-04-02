package com.example.jour2.controller;

import com.example.jour2.model.UserForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class ViewController {

    @GetMapping("/view")
    public String showForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        model.addAttribute("consoles", getConsoles());
        return "view";
    }

    @PostMapping("/view")
    public String submitForm(@ModelAttribute("userForm") @Valid UserForm form,
                             BindingResult result,
                             Model model) {

        model.addAttribute("consoles", getConsoles());

        if (result.hasErrors()) {
            return "view";
        }

        String message = "Bienvenue " + form.getPseudo() + ", tu as " + form.getAge() + " ans !";
        model.addAttribute("message", message);
        return "view";
    }

    private List<String> getConsoles() {
        return List.of("Playstation", "Nintendo", "Sega", "Xbox");
    }
}