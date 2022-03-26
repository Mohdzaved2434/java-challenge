package jp.co.axa.apidemo.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel("EmployeePostRequest")
public class EmployeePostRequest {

    @ApiModelProperty(name = "name", dataType = "String", required = true, example = "Tom", position = 1)
    @NotEmpty(message = "Name is required")
    @Setter
    @Getter
    private String name;

    @ApiModelProperty(name = "department", dataType = "String", required = true, example = "Production", position = 2)
    @NotEmpty(message = "Department is required")
    @Setter
    @Getter
    public String department;

    @ApiModelProperty(name = "salary", dataType = "Int", required = true, example = "123", position = 3)
    @Min(1)
    @NotNull(message = "Salary is required")
    @Setter
    @Getter
    private Integer salary;

}