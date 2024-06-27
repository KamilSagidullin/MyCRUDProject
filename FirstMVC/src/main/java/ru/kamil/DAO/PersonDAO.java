package ru.kamil.DAO;

import org.springframework.stereotype.Component;
import ru.kamil.models.Person;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private int PERSON_ID;
    private List<Person> personList;
    {
        personList = new ArrayList<>();
        personList.add(new Person(++PERSON_ID,"Mike"));
        personList.add(new Person(++PERSON_ID,"Kate"));
        personList.add(new Person(++PERSON_ID,"John"));
    }

    public List<Person> getPersonList(){
        return personList;
    }
    public Person getPerson(int id){
        return personList.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
    public void save(Person person){
        person.setId(++PERSON_ID);
        personList.add(person);
    }
    public void update(int id,Person person){
        Person updatingPerson = getPerson(id);
        updatingPerson.setName(person.getName());
    }
    public void delete(int id){
        personList.removeIf(p -> p.getId() == id);
    }
}
