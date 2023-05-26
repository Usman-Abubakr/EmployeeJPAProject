package com.bootswana.employeejpaproject.services;

import com.bootswana.employeejpaproject.model.dtos.IManagerProjection;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UtilityClass {
    public static String getDateAsString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String getManagerAsString(IManagerProjection manager) {
        return "Manager{" +
                "id=" + manager.getEmpNo() +
                ", birthDate=" + manager.getBirthDate() +
                ", firstName='" + manager.getFirstName() + '\'' +
                ", lastName='" + manager.getLastName() + '\'' +
                ", gender='" + manager.getGender() + '\'' +
                ", hireDate=" + manager.getHireDate() + '\'' +
                ", fromDate=" + manager.getFromDate() + '\'' +
                ", toDate=" + manager.getToDate() +
                '}';
    }
}
