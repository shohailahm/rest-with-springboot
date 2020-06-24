package org.mvn;


import org.mvn.config.FileStorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@EnableConfigurationProperties({FileStorageConfig.class})
public class Startup {

    public static void main(final String[] args) {
        SpringApplication.run(Startup.class,args);
    }

//    BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(16);
//    String result= encoder.encode("admin123");

}
