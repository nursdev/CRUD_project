package kz.nurs.models;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Nurs Tech
 * @project CRUD_project;
 */
@Component
public class Person {

    private int person_id;
    @NotEmpty(message = "ФИО не может быть пустым")
    @Size(min = 10, message = "длина ФИО должна быть больше 10 символом")
    private String fio;
    @Min(value = 0, message = "Год рождения не корректно")
    private int birth_of_year;

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getBirth_of_year() {
        return birth_of_year;
    }

    public void setBirth_of_year(int birth_of_year) {
        this.birth_of_year = birth_of_year;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }
}
