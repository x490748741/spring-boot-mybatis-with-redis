package com.wooyoo.learning.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "src")
public class CustomConfig {
    private String excelSrc;
    private String imgSrc;

    public String getExcelSrc() {
        return excelSrc;
    }

    public void setExcelSrc(String excelSrc) {
        this.excelSrc = excelSrc;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}
