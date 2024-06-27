package ru.kamil.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kamil.DAO.PersonDAO;
import ru.kamil.models.Person;

@Controller
@RequestMapping("/people")
public class PersonController {
    final PersonDAO personDAO;
    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String showPeople(Model model) {
        model.addAttribute("people",personDAO.getPersonList());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String showPerson(Model model, @PathVariable("id") int id){
        model.addAttribute("person",personDAO.getPerson(id));
        return "people/person";
    }
    @GetMapping("/create")
    public String createNewPerson(@ModelAttribute("person") Person person){
        return "people/create";
    }
    @PostMapping()
    public String addingToDataBase(@ModelAttribute("person") Person person){
        personDAO.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String updatePerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person",personDAO.getPerson(id));
        return "people/update";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person,@PathVariable("id") int id){
        personDAO.update(id,person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}