package pl.java.scalatech.writer;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StringWriter implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> items) throws Exception {
        items.stream().parallel().forEach(item -> log.info("{}", item));

    }

}
