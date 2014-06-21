package org.mytraffic.notifications;

import org.mytraffic.notifications.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Launches the application.
 *
 * @author avasquez
 * @author mariobarque
 */
public class Bootstrap {

    public static void main(String... args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ctx.registerShutdownHook();

        hangUntilShutdown(ctx);
    }

    private static void hangUntilShutdown(ApplicationContext ctx) {
        synchronized (ctx) {
            while (true) {
                try {
                    ctx.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }

}
