package pl.pgrudev;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.pgrudev.core.WebsocketGateway;

@Configuration
@ComponentScan
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
        try {
            ctx.getBean(WebsocketGateway.class).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

