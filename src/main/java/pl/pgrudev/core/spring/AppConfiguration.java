package pl.pgrudev.core.spring;


import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static pl.pgrudev.core.spring.SpringExtension.SPRING_EXTENSION_PROVIDER;

@PropertySource("classpath:application.properties")
@Configuration
public class AppConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem system = ActorSystem.create("akka-spring-demo");
        SPRING_EXTENSION_PROVIDER.get(system).initialize(applicationContext);
        return system;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
