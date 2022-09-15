package com.cy.store.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class JsonResult <E> implements Serializable {

    private Integer state;
    private String message;
    private E data;

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public JsonResult(Integer state, String message, E data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;

    }
}
