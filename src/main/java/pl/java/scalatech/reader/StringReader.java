package pl.java.scalatech.reader;

import java.util.List;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

import org.assertj.core.util.Lists;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StringReader implements ItemReader<String> {

    private List<String> names;

    @PostConstruct
    public void init() {
        names = Lists.newArrayList("slawek", "tomek", "ania", "marcin1", "marcin2", "michal");
    }

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        String str = !names.isEmpty() ? names.remove(0) : null;
        log.info("+++ reader {} ", str);
        return str;
    }

}
