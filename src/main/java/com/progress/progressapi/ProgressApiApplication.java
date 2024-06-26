package com.progress.progressapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author 15101014325_90714
 */
@SpringBootApplication
@Slf4j
public class ProgressApiApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ProgressApiApplication.class);
        Environment env = app.run(args).getEnvironment();

        app.addListeners();
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
                        "External: \thttp://{}:{}\n\t" +
                        "env:\t\t{}\n\t" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                getIpAddress(),
                env.getProperty("server.port"),
                env.getProperty("env"));
    }

    private static String getIpAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return null;
        }
    }
}
