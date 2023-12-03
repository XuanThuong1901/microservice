package com.example.employee.command.model.request;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    private String id;
    private String fullName;
    private String kin;
    private Boolean isDisciplined;
}
