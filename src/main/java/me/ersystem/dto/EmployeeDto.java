package me.ersystem.dto;

import lombok.Data;

/**
 *
 * @since 16-Feb-19
 */
@Data
public class EmployeeDto {

    private int id;

    private String role;

    private String name;

    private String region;

    private String phonenumber;

    private String password;
}
