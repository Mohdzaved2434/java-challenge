package jp.co.axa.apidemo.controller;

import jp.co.axa.apidemo.entity.Employee;
import jp.co.axa.apidemo.model.request.EmployeePostRequest;
import jp.co.axa.apidemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> get() {
        List<Employee> employees = employeeService.retrieveEmployees();
        return employees;
    }

    @GetMapping("/employees/{employeeId}")
    public Employee get(@PathVariable(name = "employeeId") Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @PostMapping("/employees")
    public void post(@Valid @RequestBody EmployeePostRequest employeePostRequest) {

        Employee employee = new Employee();
        employee.setId(employeePostRequest.getId());
        employee.setName(employeePostRequest.getName());
        employee.setDepartment(employeePostRequest.getDepartment());
        employee.setSalary(employeePostRequest.getSalary());

        employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void delete(@PathVariable(name = "employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        System.out.println("Employee Deleted Successfully");
    }

    @PutMapping("/employees/{employeeId}")
    public void put(@RequestBody Employee employee,
                    @PathVariable(name = "employeeId") Long employeeId) {
        Employee emp = employeeService.getEmployee(employeeId);
        if (emp != null) {
            employeeService.updateEmployee(employee);
        }

    }

}
