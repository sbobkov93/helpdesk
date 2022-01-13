package com.example.helpdesk.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class NoteDTO {

    private Integer id;
    private Integer ticket;
    @NotNull(message = "Обязательное поле")
    @Size(min = 1, message = "Обязательное поле")
    private String comment;
    private Integer creator;
    @Min(value = 1, message = "Выберите владельца")
    private Integer owner;
    private Integer status = 1;

    private boolean readOnly;

}
