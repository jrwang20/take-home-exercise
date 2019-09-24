package com.validity.takehome.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeVO {

    private String id;

    private String firstName;

    private String lastName;

    private String company;

    private String email;

    private String address1;

    private String address2;

    private String city;

    private String zip;

    private String stateLong;

    private String state;

    private String phone;


}
