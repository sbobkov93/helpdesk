package com.example.helpdesk.dto;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class TicketDTO {

    private Integer id;
    @NotNull(message = "Обязательное поле")
    private String title;
    private String description;
    private Integer creator;
    @Min(value = 1, message = "Выберите владельца")
    private Integer owner;
    @NotNull
    @Min(value = 1, message = "Выберите клиента")
    private Integer client;
    @NotNull
    @Min(1)
    private Integer status = 1;
    private boolean readOnly;
    private boolean isLastUpdate = true;
}
