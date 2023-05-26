package com.bootswana.employeejpaproject;

import com.bootswana.employeejpaproject.logging.LogSetup;
import com.bootswana.employeejpaproject.model.repositories.SalaryRepository;
import com.bootswana.employeejpaproject.services.SalariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class EmployeeJpaProjectApplication {

	@Autowired
	SalariesService salariesService;

    static Logger logger = Logger.getLogger(EmployeeJpaProjectApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(EmployeeJpaProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner() {
		Map<Integer, String[]> dataMap = CSVReader.readCSV();

		//3
		String[] data = dataMap.get(3);
		String department = data[1];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.parse(data[2], formatter);
		salariesService.logAverageSalaryForDepartmentOnGivenDate(department, date);

		//4
		data = dataMap.get(4);
		String jobTitle = data[1];
		int year = Integer.parseInt(data[2]);
		salariesService.logLowestAndHighestSalaryForJobTitleDuringAYear(jobTitle, year);

		//6
		data = dataMap.get(6);
		int fromYear = Integer.parseInt(data[1]);
		int toYear = Integer.parseInt(data[2]);
		salariesService.logGenderPayGapPercentageBetweenTwoYearsForEachJobTitle(fromYear, toYear);

		//9
		data = dataMap.get(9);
		int empNo = Integer.parseInt(data[1]);
		salariesService.logFirstFiveSalariesOfAnEmployeeByEmployeeNumber(empNo);
		return args -> logger.log(Level.SEVERE, "Test");
	}
}
