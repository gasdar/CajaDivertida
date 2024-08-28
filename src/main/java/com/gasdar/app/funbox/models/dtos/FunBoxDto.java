package com.gasdar.app.funbox.models.dtos;

import org.bson.types.ObjectId;

public class FunBoxDto {

    private ObjectId id;
    private String name;
    private String code;
    private String range;
    private Integer totalProds;
    private Integer priceByProd;
    private Integer priceByBox;

    public FunBoxDto() {
    }
    public FunBoxDto(ObjectId id, String name, String code, String range, Integer totalProds, Integer priceByProd,
            Integer priceByBox) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.range = range;
        this.totalProds = totalProds;
        this.priceByProd = priceByProd;
        this.priceByBox = priceByBox;
    }

    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getRange() {
        return range;
    }
    public void setRange(String range) {
        this.range = range;
    }
    public Integer getTotalProds() {
        return totalProds;
    }
    public void setTotalProds(Integer totalProds) {
        this.totalProds = totalProds;
    }
    public Integer getPriceByProd() {
        return priceByProd;
    }
    public void setPriceByProd(Integer priceByProd) {
        this.priceByProd = priceByProd;
    }
    public Integer getPriceByBox() {
        return priceByBox;
    }
    public void setPriceByBox(Integer priceByBox) {
        this.priceByBox = priceByBox;
    }

}
