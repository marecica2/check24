//package balla.marek.kredite24.employee;
//
//import balla.marek.kredite24.Kredite24Application;
//import balla.marek.kredite24.employees.Employee;
//import balla.marek.kredite24.employees.EmployeeController;
//import balla.marek.kredite24.employees.EmployeeRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.List;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {Kredite24Application.class})
//@WebMvcTest(controllers = EmployeeController.class)
//public class EmployeeControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    EmployeeRepository repository;
//
//    @Test
//    public void shouldRespondWithOk() throws Exception {
//        List<Employee> employees = List.of(new Employee("Test1"), new Employee("Test2"));
//        when(repository.findAll()).thenReturn(employees);
//        mvc.perform(MockMvcRequestBuilders
//                            .get("/employees"))
//           .andExpect(status().isOk())
//           .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
//           .andExpect(MockMvcResultMatchers.jsonPath("$[0]['name']").exists())
//           .andExpect(MockMvcResultMatchers.jsonPath("$[0]['name']").value("Test1"));
//    }
//}
