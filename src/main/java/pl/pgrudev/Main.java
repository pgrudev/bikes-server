package pl.pgrudev;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.pgrudev.core.WebsocketGateway;

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

