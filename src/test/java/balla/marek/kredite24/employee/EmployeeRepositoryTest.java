//package balla.marek.kredite24.employee;
//
//import balla.marek.kredite24.Kredite24Application;
//import balla.marek.kredite24.employees.Employee;
//import balla.marek.kredite24.employees.EmployeeRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//
//@ActiveProfiles("test")
//@SpringBootTest
//@ContextConfiguration(classes = {Kredite24Application.class})
//public class EmployeeRepositoryTest {
//
//    @Autowired
//    EmployeeRepository repository;
//
//    @Test
//    public void shouldInsertEmployee() {
//        Employee savedEmployee = repository.save(new Employee("Test1"));
//        Assertions.assertNotNull(savedEmployee.getId());
//    }
//}
