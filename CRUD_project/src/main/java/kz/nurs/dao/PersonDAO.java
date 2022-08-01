package kz.nurs.dao;

import kz.nurs.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Nurs Tech
 * @project CRUD_project;
 */

@Component
public class PersonDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople() {
        return jdbcTemplate.query("SELECT * FROM Person",
                new BeanPropertyRowMapper<>(Person.class));
    }


    public Person getById(Integer id) {
        if(id == null) return null;
        return jdbcTemplate.query("SELECT * FROM Person where person_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET fio = ?, birth_of_year=? where person_id = ?",
                updatedPerson.getFio(), updatedPerson.getBirth_of_year(), id);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(fio, birth_of_year) VALUES (?, ?)",
                person.getFio(), person.getBirth_of_year());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person where person_id = ?", id);
    }
}
