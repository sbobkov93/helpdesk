package com.example.helpdesk.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class EmployeeDTO {

    private Integer id;
    @NotNull
//    @Size(min = 8, max = 16)
    private String userName;
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8,20}$")
    @NotNull
//    @Size(min = 8, max = 20)
    private String password;
    @NotNull
//    @Size(min = 8, max = 20)
    private String matchingPassword;
    @NotNull
    private Boolean enabled = true;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String patronymic;
    private String phone;
    @NotNull
    private String email;
    @NotNull
    private Integer role;
    private boolean isReadOnly = false;
    private Integer detailsId;
    private Integer authenticationDataId;
}
