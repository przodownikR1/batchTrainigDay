package pl.java.scalatech;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.java.scalatech.entity.Person;
import pl.java.scalatech.repository.PersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BatchStarerApplication.class)
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    List<Person> persons = newArrayList(Person.builder().login("kowalski").passwd("p1").age(34).build(), Person.builder().login("borowiec").passwd("p2")
            .age(36).build(), Person.builder().login("borowiec").passwd("p3").age(23).build());

    @Test
    public void shouldPersistPerson() {
        //given
        //when
        personRepository.save(persons);
        //then
        assertThat(personRepository.count()).isEqualTo(3);

    }
}
