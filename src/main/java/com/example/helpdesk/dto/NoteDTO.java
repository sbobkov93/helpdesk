package com.example.helpdesk.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class NoteDTO {

    private Integer id;
    @NotNull(message = "Обязательное поле")
    private Integer ticket;
    private String comment;
    private Integer creator;
    @Min(value = 1, message = "Выберите владельца")
    private Integer owner;
    @NotNull
    @Min(value = 1, message = "Выберите клиента")
    @NotNull
    @Min(1)
    private Integer status = 1;

    private boolean readOnly;

}
