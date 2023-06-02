package com.bootswana.employeejpaproject.model.repositories;

import com.bootswana.employeejpaproject.model.dtos.EmployeeDTO;
import com.bootswana.employeejpaproject.model.dtos.IManagerProjection;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDTO, Integer> {
    @Query(value = "SELECT e.* FROM employees.employees e WHERE e.last_name = :lastName", nativeQuery = true)
    List<EmployeeDTO> getEmployeesByLastName(String lastName);

    @Query(value = "SELECT e.* FROM employees.employees e JOIN employees.dept_emp de ON e.emp_no = de.emp_no JOIN employees.departments d ON de.dept_no = d.dept_no WHERE d.dept_name = :departmentName AND de.from_date <= :chosenDate AND de.to_date >= :chosenDate", nativeQuery = true)
    List<EmployeeDTO> findEmployeesByDepartmentNameOnDate(String departmentName, LocalDate chosenDate);

    @Query(value = "SELECT e.emp_no as empNo, e.birth_date as birthDate, e.first_name as firstName, e.last_name as lastName, e.gender as gender, e.hire_date as hireDate, dm.from_date as fromDate, dm.to_date as toDate FROM employees.employees e JOIN employees.dept_manager dm on e.emp_no = dm.emp_no JOIN employees.departments d ON dm.dept_no = d.dept_no WHERE d.dept_name = :departmentName ORDER BY dm.from_date", nativeQuery = true)
    List<IManagerProjection> findManagersAndDatesByDepartmentNameChronologically(String departmentName);

    @Query(value = "SELECT e.* FROM employees.employees e JOIN employees.dept_manager dm on e.emp_no = dm.emp_no JOIN employees.departments d ON dm.dept_no = d.dept_no WHERE d.dept_name = :departmentName ORDER BY dm.from_date", nativeQuery = true)
    List<EmployeeDTO> findManagersByDepartmentNameChronologically(String departmentName);



    @Modifying
    @Query(value="INSERT INTO employees.employees (emp_no,birth_date,first_name,last_name,gender,hire_date) values (:empNo,:birthDate,:firstName,:lastName,:gender,:hireDate)",nativeQuery = true)
    void insertEmployee(@Param("empNo") int empNo,
                        @Param("birthDate")LocalDate birthDate,
                        @Param("firstName")String firstName,
                        @Param("lastName")String lastName,
                        @Param("gender")String gender,
                        @Param("hireDate") LocalDate hireDate);

//    @Modifying
//    @Query(
//            value =
//                    "INSERT INTO employees (name, age, email, status) values (:name, :age, :email, :status)",
//            nativeQuery = true)
//    void insertUser(@Param("name") String name, @Param("age") Integer age,
//                    @Param("status") Integer status, @Param("email") String email);

}