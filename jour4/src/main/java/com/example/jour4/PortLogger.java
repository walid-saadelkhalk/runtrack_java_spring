package com.example.jour4;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PortLogger implements ApplicationListener<WebServerInitializedEvent> {

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        int port = event.getWebServer().getPort();
        System.out.println("✅ Application démarrée sur : http://localhost:" + port + "/h2-console");
        System.out.println("✅ Application démarrée sur : http://localhost:" + port + "/persons");
        System.out.println("✅ Application démarrée sur : http://localhost:" + port + "/form");
        System.out.println("✅ Application démarrée sur : http://localhost:" + port + "/login");
    }
}

