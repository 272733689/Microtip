package com.microtip;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ServletComponentScan
@MapperScan("com.microtip.Mapper")
public class MicrotipApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicrotipApplication.class, args);


		//PublishSubscribe.subscribe();
	}

}
