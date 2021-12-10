package com.example.helpdesk.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
