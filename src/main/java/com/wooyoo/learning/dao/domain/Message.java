package com.wooyoo.learning.dao.domain;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = -2688736002110964365L;

    private Integer id;
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
