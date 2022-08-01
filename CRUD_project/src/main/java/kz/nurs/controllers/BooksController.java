package kz.nurs.controllers;

import kz.nurs.dao.BookDAO;
import kz.nurs.dao.PersonDAO;
import kz.nurs.models.Book;
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
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }


    @GetMapping
    public String show(Model model) {
        model.addAttribute("books", bookDAO.getBooks());
        return "books/show";
    }

    @GetMapping("/new")
    public String createBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "books/new";
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getById(id));
        return "books/edit";
    }

    @PatchMapping("{id}")
    public String editBook(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book updatedBook,
                           BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(id, updatedBook);
        return "redirect:/books";
    }

    @GetMapping("{id}")
    public String infoBook(@PathVariable("id") int id, Model model, @ModelAttribute("defPerson") Person person) {
        Book book = bookDAO.getById(id);
        model.addAttribute("book", book);
        model.addAttribute("people", personDAO.getPeople());
        model.addAttribute("person", personDAO.getById(book.getPerson_id()));
        return "books/info";
    }

    @PostMapping("{id}")
    public String release(@PathVariable("id") int id) {
        bookDAO.setRelease(id);
        return "redirect:/books";
    }

    @PostMapping("{id}/add")
    public String appointBook(@ModelAttribute("person") Person person, @PathVariable("id") int book_id) {
        bookDAO.setAppoint(book_id, person.getPerson_id());
        return "redirect:/books";
    }

    @DeleteMapping("{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
