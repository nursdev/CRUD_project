package kz.nurs.dao;

import kz.nurs.models.Book;
import kz.nurs.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nurs Tech
 * @project CRUD_project;
 */
@Component
public class BookDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT * FROM Book",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES(?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public Book getById(int id) {
        return jdbcTemplate.query("SELECT * FROM Book where book_id = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book set name = ?, author = ?, year = ? where book_id = ?",
                updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    public void setRelease(int id) {
        jdbcTemplate.update("UPDATE Book set person_id = ? where book_id = ?",
                null, id);
    }

    public void setAppoint(int book_id, int person_id) {
        jdbcTemplate.update("UPDATE Book set person_id = ? where book_id = ?",
                person_id, book_id);
    }

    public List<Book> getByPerson_id(int person_id) {
        return jdbcTemplate.query("SELECT * FROM Book where person_id = ?", new Object[]{person_id},
                new BeanPropertyRowMapper<>(Book.class));
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book where book_id = ?", id);
    }
}
