package com.example.employee.command.event;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeEvent {
    private String id;
    private String fullName;
    private String kin;
    private Boolean isDisciplined;
    private Date createdDate;
}
