//package balla.marek.kredite24.employees;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("employees")
//public class EmployeeController {
//
//    private final EmployeeRepository repository;
//
//    @PostConstruct
//    public void init() {
//        repository.save(new Employee("Test1"));
//        repository.save(new Employee("Test2"));
//    }
//
//    private final ModelMapper mapper;
//
//    public EmployeeController(EmployeeRepository repository, ModelMapper mapper) {
//        this.repository = repository;
//        this.mapper = mapper;
//    }
//
//    @GetMapping
//    public List<EmployeeDto> getEmployees() {
//        return this.repository
//                .findAll()
//                .stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    private EmployeeDto convertToDto(Employee employee) {
//        EmployeeDto dto = this.mapper.map(employee, EmployeeDto.class);
//        return dto;
//    }
//}
