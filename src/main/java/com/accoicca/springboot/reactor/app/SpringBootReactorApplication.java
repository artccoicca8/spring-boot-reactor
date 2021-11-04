package com.accoicca.springboot.reactor.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootReactorApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(SpringBootReactorApplication.class); 
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 
		Flux<String> nombres = Flux.just("ana","malu","maria","anel","denis")
				.doOnNext(e -> {
					
					if (e.isEmpty()) {
						throw new RuntimeException("names can't be empty ");
					}else {
						System.out.println(e); // imprime la collecion 	
					}
					
					
					
				
				});
		// subcribe maneja hasta 3 argumento de entrada 
		nombres.subscribe(
				e -> log.info(e), // se tiene que suscribir al obervable para poder obetenr el resutado de la operacion , el primer valor retorna datos corrwcto 
				error -> log.error(error.getMessage()), //  aquie se maneja el error 
				new Runnable() { // este argumento es la tarea completada 
					
					@Override
					public void run() {
						log.info("Task complete !!"); 
						
					}
				} 
				);
		
		
		
	}

}
