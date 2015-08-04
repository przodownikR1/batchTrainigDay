package pl.java.scalatech.tasklet;

import org.springframework.batch.core.step.tasklet.SystemCommandTasklet;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component(value="system")
@Profile("system")
public class SystemTasklet  extends SystemCommandTasklet {

    @Override
    public void afterPropertiesSet() throws Exception {
      this.setCommand("/usr/bin/unzip -o " + new ClassPathResource("customers.csv.zip").getFile().getAbsolutePath());
      this.setTimeout(60000L);
      this.setWorkingDirectory("/tmp");
      super.afterPropertiesSet();
    }
  }