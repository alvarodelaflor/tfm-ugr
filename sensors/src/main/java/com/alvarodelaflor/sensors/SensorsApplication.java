package com.alvarodelaflor.sensors;

import com.tuya.connector.spring.annotations.ConnectorScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@ConnectorScan(basePackages = "com.alvarodelaflor.sensors.connector")
@SpringBootApplication()
public class SensorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorsApplication.class, args);
	}

}
