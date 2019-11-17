package com.wooyoo.learning.dao.domain;

import java.util.Date;

public class AprojectDesign {
    private Long id;
    private Long aprojectId;
    private String imgSrc;
    private Double width;
    private Double height;
    private Double widthRatio;
    private Double heightRatio;
    private Double x;
    private Double y;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAprojectId() {
        return aprojectId;
    }

    public void setAprojectId(Long aprojectId) {
        this.aprojectId = aprojectId;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidthRatio() {
        return widthRatio;
    }

    public void setWidthRatio(Double widthRatio) {
        this.widthRatio = widthRatio;
    }

    public Double getHeightRatio() {
        return heightRatio;
    }

    public void setHeightRatio(Double heightRatio) {
        this.heightRatio = heightRatio;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
