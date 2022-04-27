package com.gianca1994.umcredits;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
@SpringBootApplication
public class UmcreditsApplication {
    public static void main(String[] args) {

        Log log = LogFactory.getLog(UmcreditsApplication.class);

        SpringApplication.run(UmcreditsApplication.class, args);

        log.info("UM-CREDITS API INICIALIZADA!!!");


    }
}