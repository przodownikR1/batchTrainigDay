package pl.java.scalatech.processor;

import org.springframework.batch.item.ItemProcessor;

import pl.java.scalatech.entity.Person;

public class Person2StringProcessor implements ItemProcessor<Person, String> {

    @Override
    public String process(Person item) throws Exception {
        item.setLogin(item.getLogin().toUpperCase());
        return item.toString();
    }

}
