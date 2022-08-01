package kz.nurs.controllers;

import kz.nurs.dao.BookDAO;
import kz.nurs.dao.PersonDAO;
import kz.nurs.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Nurs Tech
 * @project CRUD_project;
 */
@Controller
@RequestMapping("people")
public class PeopleController {

    private final PersonDAO personDao;
    private final BookDAO bookDAO;

    @Autowired
    public PeopleController(PersonDAO personDao, BookDAO bookDAO) {
        this.personDao = personDao;
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String show(Model model) {
        model.addAttribute("people", personDao.getPeople());
        return "people/show";
    }

    @GetMapping("{id}")
    public String infoPerson(@PathVariable("id") int id, Model model) {

        model.addAttribute("person", personDao.getById(id));
        model.addAttribute("list_of_books", bookDAO.getByPerson_id(id).isEmpty() ? null : bookDAO.getByPerson_id(id));

        return "people/info";
    }

    @GetMapping("{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model) {

        model.addAttribute("person", personDao.getById(id));

        return "people/edit";
    }

    @PatchMapping("{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person updatedPerson,
                               BindingResult bindingResult , @PathVariable("id") int id) {
        if(bindingResult.hasErrors())
            return "people/edit";

        personDao.update(id, updatedPerson);
        return "redirect:/people";
    }

    @GetMapping("new")
    public String createPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "people/new";

        personDao.save(person);
        return "redirect:/people";
    }

    @DeleteMapping("{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personDao.delete(id);
        return "redirect:/people";
    }


}
