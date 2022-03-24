package jp.co.axa.apidemo.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class EmployeePutRequest {
    @NotBlank
    @Min(1)
    @Max(9999999)
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    public String department;

    @Min(1)
    @Max(9999999)
    @Setter
    @Getter
    private Integer salary;

}