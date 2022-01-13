package com.example.helpdesk.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class EmployeeDTO {

    private Integer id;
    @NotNull
    @Size(min = 1, message = "Обязательное поле")
    private String userName;
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8,20}$")
    @NotNull
    @Size(min = 5, message = "Обязательное поле, не менее 5 символов")
    private String password;
    @NotNull
//    @Size(min = 8, max = 20)
    @Size(min = 5, message = "Обязательное поле, не менее 5 символов")
    private String matchingPassword;
    @NotNull
    private Boolean enabled = true;
    @NotNull
    @Size(min = 1, message = "Обязательное поле")
    private String firstName;
    @NotNull
    @Size(min = 1, message = "Обязательное поле")
    private String lastName;
    private String patronymic;
    @NotNull
    @Size(min = 11, message = "Обязательное поле, не менее 11 символов")
    private String phone;
    @NotNull
    @Size(min = 7, message = "Обязательное поле, не менее 7 символов")
    private String email;
    @NotNull
    private Integer role;
    private boolean readOnly = false;
    private Integer detailsId;
    private Integer authenticationDataId;
}
