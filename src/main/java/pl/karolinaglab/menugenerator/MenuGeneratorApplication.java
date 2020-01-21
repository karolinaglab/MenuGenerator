package pl.karolinaglab.menugenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
//@PropertySource(value = {"classpath:application.properties"})
public class MenuGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MenuGeneratorApplication.class, args);
    }

}
