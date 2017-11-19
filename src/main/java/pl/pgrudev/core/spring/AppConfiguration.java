package pl.pgrudev.core.spring;


import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static pl.pgrudev.core.spring.SpringExtension.SPRING_EXTENSION_PROVIDER;

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
/*
    @Bean
    @ConditionalOnMissingBean(CustomConversions.class)
    public CustomConversions customConversions(){
        return new CustomConversions(null);
    }*/
}
