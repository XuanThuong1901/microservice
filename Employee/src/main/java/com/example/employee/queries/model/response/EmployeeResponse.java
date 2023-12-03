package com.example.employee.queries.model.response;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private String id;
    private String fullName;
    private String kin;
    private Boolean isDisciplined;
    private Date createdDate;
}
