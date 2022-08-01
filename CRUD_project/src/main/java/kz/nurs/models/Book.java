package kz.nurs.models;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @author Nurs Tech
 * @project CRUD_project;
 */

@Component
public class Book {

    private int book_id;
    private Integer person_id;
    @NotEmpty(message = "Имя книги не может быть пустым")
    private String name;
    @NotEmpty(message = "Автор книги не может быть пустым")
    private String author;
    @Min(value = 0, message = "Год книги не может быть меньше чем 0")
    private int year;

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
