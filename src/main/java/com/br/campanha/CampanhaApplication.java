/*** @author diegomauricio
 * 
 * Classe que o spring boot inicia o tomcat com a aplicação criada já funcionando.
 */
package com.br.campanha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class CampanhaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampanhaApplication.class, args);
	}
}
