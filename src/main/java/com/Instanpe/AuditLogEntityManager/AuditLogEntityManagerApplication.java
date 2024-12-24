package com.Instanpe.AuditLogEntityManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class AuditLogEntityManagerApplication {



	public static void main(String[] args) {
		SpringApplication.run(AuditLogEntityManagerApplication.class, args);
	}

}
