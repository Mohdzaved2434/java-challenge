package jp.co.axa.apidemo.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class EmployeePostRequest {

    @Min(1)
    @Max(9999999)
    @Setter
    @Getter
    private Long id;


    @NotEmpty
    @Setter
    @Getter
    private String name;

    @NotEmpty
    @Setter
    @Getter
    public String department;

    @Min(1)
    @Max(9999999)
    @Setter
    @Getter
    private Integer salary;

}