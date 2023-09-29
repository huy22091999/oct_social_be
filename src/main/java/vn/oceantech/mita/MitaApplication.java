package vn.oceantech.mita;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import vn.oceantech.mita.config.StorageProperties;
import vn.oceantech.mita.service.StorageService;

import java.io.FileInputStream;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@EnableScheduling
public class MitaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MitaApplication.class, args);
	}
	@Bean
	public CommandLineRunner init(StorageService ss) {
		return(args->{
			ss.init();
		});
	}
	@Bean
	public Docket docketAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("vn.oceantech.mita.rest"))
				.paths(PathSelectors.any())
				.build();
	}



}
