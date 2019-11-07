package IOT.medicine.sugar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EntityScan("my.entity.sugarMS")
@EnableSwagger2
public class SugarApplication {

    public static void main(String[] args) {

        SpringApplication.run(SugarApplication.class, args);

    }

}
