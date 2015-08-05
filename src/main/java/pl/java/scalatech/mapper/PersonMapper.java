package pl.java.scalatech.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import pl.java.scalatech.entity.Person;

public class PersonMapper implements FieldSetMapper<Person> {
    @Override
    public Person mapFieldSet(FieldSet fieldSet) throws BindException {
        Person person = new Person();
        person.setLogin(fieldSet.readString("login"));
        person.setPasswd(fieldSet.readString("passwd"));
        person.setAge(fieldSet.readInt(2));
        return person;
    }

}
