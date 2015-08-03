package pl.java.scalatech.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class StringProcessor implements ItemProcessor<String, String> {

    @Override
    public String process(String item) throws Exception {
        return item.toUpperCase();
    }

}
