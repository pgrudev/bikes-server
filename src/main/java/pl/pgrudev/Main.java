package pl.pgrudev;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.pgrudev.core.WebsocketGateway;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Main {
    private final static Logger logger = org.apache.logging.log4j.LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        installUncaughtExceptionHandler();
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
        try {
            ctx.getBean(WebsocketGateway.class).start();
        } catch (InterruptedException e) {
            logger.fatal("Startup exception: ", e);
        }
    }

    private static void installUncaughtExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(
                (thread, exception) -> {
                    String msg = "Uncaught Exception in thread:" + thread.getName();
                    exception.printStackTrace(); //logger might be shut down
                    logger.fatal(msg, exception);
                });
    }
}

