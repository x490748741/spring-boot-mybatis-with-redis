package com.wooyoo.learning.dao.domain;

import java.io.Serializable;

public class Mvalidate implements Serializable {

    private static final long serialVersionUID = -206759878525069813L;

    private Long id;
    private String name;
    private String validateCode;
    private String result;
    private int isSelectSuccess;

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }

    public int getIsSelectSuccess() {
        return isSelectSuccess;
    }

    public void setIsSelectSuccess(int isSelectSuccess) {
        this.isSelectSuccess = isSelectSuccess;
    }
}
