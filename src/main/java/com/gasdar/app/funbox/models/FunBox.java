package com.gasdar.app.funbox.models;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.gasdar.app.funbox.models.dtos.ProductDto;
import com.gasdar.app.funbox.models.dtos.UserDto;

@Document(collection="funboxes")
public class FunBox {

    @Id
    private ObjectId id;

    private String name;
    private String code;
    private Integer rangeOption;
    private String boxSize;
    private Integer prodPrice;
    private Integer boxPrice;
    private String state;
    private UserDto userDto;
    private List<ProductDto> prodsDto;
    private Map<String, Object> data;
    
    public FunBox() {
    }
    public FunBox(String name, String code, Integer rangeOption, String boxSize, Integer prodPrice, Integer boxPrice,
    String state, UserDto userDto) {
        this.name = name;
        this.code = code;
        this.rangeOption = rangeOption;
        this.boxSize = boxSize;
        this.prodPrice = prodPrice;
        this.boxPrice = boxPrice;
        this.state = state;
        this.userDto = userDto;
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
    public Integer getRangeOption() {
        return rangeOption;
    }
    public void setRangeOption(Integer rangeOption) {
        this.rangeOption = rangeOption;
    }
    public String getBoxSize() {
        return boxSize;
    }
    public void setBoxSize(String boxSize) {
        this.boxSize = boxSize;
    }
    public Integer getProdPrice() {
        return prodPrice;
    }
    public void setProdPrice(Integer prodPrice) {
        this.prodPrice = prodPrice;
    }
    public Integer getBoxPrice() {
        return boxPrice;
    }
    public void setBoxPrice(Integer boxPrice) {
        this.boxPrice = boxPrice;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public UserDto getUserDto() {
        return userDto;
    }
    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
    public List<ProductDto> getProdsDto() {
        return prodsDto;
    }
    public void setProdsDto(List<ProductDto> prodsDto) {
        this.prodsDto = prodsDto;
    }
    public Map<String, Object> getData() {
        return data;
    }
    public void setData(Map<String, Object> data) {
        this.data = data;
    }
    public void adjustData(String key, Object value) {
        Map<String, Object> json = new HashMap<>();
        if(this.data == null) {
            json.put(key, value);
            this.data = json;
        } else {
            this.data.put(key, value);
        }
    }
    
}
