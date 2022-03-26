package jp.co.axa.apidemo.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ApiModel("EmployeePutRequest")
public class EmployeePutRequest {

    @ApiModelProperty(name = "name", dataType = "String", required = false, example = "Tom", position = 1)
    @Setter
    @Getter
    private String name;

    @ApiModelProperty(name = "department", dataType = "String", required = false, example = "Production", position = 2)
    @Setter
    @Getter
    public String department;

    @ApiModelProperty(name = "salary", dataType = "Int", required = false, example = "123", position = 3)
    @Min(1)
    @Setter
    @Getter
    private Integer salary;

}