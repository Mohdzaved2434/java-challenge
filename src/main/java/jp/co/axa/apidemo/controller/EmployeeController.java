package jp.co.axa.apidemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jp.co.axa.apidemo.constants.ApiCodes;
import jp.co.axa.apidemo.entity.Employee;
import jp.co.axa.apidemo.model.request.EmployeePostRequest;
import jp.co.axa.apidemo.model.request.EmployeePutRequest;
import jp.co.axa.apidemo.model.response.AxaApiResponse;
import jp.co.axa.apidemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Employee Controller")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "get employees")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Id not found"),
            @ApiResponse(code = 401, message = "Unauthorized")})
    @GetMapping("/employees")
    public ResponseEntity<?> get(@PathParam("employeeId") Long employeeId) {

        List<Employee> employeeList = employeeService.getEmployees(employeeId);

        AxaApiResponse apiResponse = new AxaApiResponse<>();
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setCode(ApiCodes.SUCCESS);
        apiResponse.setData(employeeList);

        return new ResponseEntity<AxaApiResponse<?>>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "save employees")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Something went wrong"),
            @ApiResponse(code = 401, message = "Unauthorized")})
    @PostMapping(value = "/employees", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> post(@Valid @RequestBody EmployeePostRequest employeePostRequest) {

        Employee employee = employeeService.saveEmployee(employeePostRequest);

        AxaApiResponse apiResponse = new AxaApiResponse<>();
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setCode(ApiCodes.SUCCESS);
        apiResponse.setData(employee);

        return new ResponseEntity<AxaApiResponse<?>>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "update employees")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Something went wrong"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 422, message = "Id not found")})
    @PutMapping(value = "//employees/{employeeId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> put(@Valid @RequestBody EmployeePutRequest putEmployee,
                                 @PathVariable(name = "employeeId") Long employeeId) {
        Employee employee = new Employee();
        if (!employeeService.getEmployees(employeeId).isEmpty()) {
            employee = employeeService.updateEmployee(putEmployee);
        }

        AxaApiResponse apiResponse = new AxaApiResponse<>();
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setCode(ApiCodes.SUCCESS);
        apiResponse.setData(employee);
        return new ResponseEntity<AxaApiResponse<?>>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "delete employees")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Id not found"),
            @ApiResponse(code = 401, message = "Unauthorized")})
    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<?> delete(@PathVariable(name = "employeeId") Long employeeId) {

        employeeService.deleteEmployee(employeeId);
        AxaApiResponse apiResponse = new AxaApiResponse<>();
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setCode(ApiCodes.SUCCESS);
        apiResponse.setData("");
        return new ResponseEntity<AxaApiResponse<?>>(apiResponse, HttpStatus.OK);
    }
}
