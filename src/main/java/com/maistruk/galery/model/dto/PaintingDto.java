package com.maistruk.galery.model.dto;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.maistruk.galery.model.Style;

public class PaintingDto {
    
    private Integer id;
    private String name;
    private Style style;
    private Integer year;
    private byte[] image;
    
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Style getStyle() {
        return style;
    }
    public void setStyle(Style style) {
        this.style = style;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public byte[] getImage() {
        return image;
    }
    public void setImage(@RequestParam CommonsMultipartFile image) {
        this.image = image.getBytes();
    }
    
    

}
