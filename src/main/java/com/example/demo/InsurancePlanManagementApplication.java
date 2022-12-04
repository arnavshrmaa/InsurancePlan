package com.example.demo;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InsurancePlanManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsurancePlanManagementApplication.class, args);
		System.out.println("MAIN CLASS - Successfully Executed");
	}
	

	//For XML Spread Sheet Format (Excel)
	@Bean
	public XSSFWorkbook getXSSFWorkbook() {
		return new XSSFWorkbook();
	}
 

}
