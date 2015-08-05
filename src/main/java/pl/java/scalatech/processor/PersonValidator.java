package pl.java.scalatech.processor;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;

import pl.java.scalatech.entity.Person;

public class PersonValidator implements Validator<Person> {

    @Override
    public void validate(Person value) throws ValidationException {
        if (value.getAge() < 18) { throw new ValidationException("not adult enough"); }

    }

}