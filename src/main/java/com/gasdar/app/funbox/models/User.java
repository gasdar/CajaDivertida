package com.gasdar.app.funbox.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.gasdar.app.funbox.models.dtos.FunBoxDto;

@Document(collection="users")
public class User {

    @Id
    private ObjectId id;
    
    private String name;
    private String password;
    private String profile;
    private String email;
    private String phone;
    private Integer salary;
    private List<FunBoxDto> boxesDto;

    public User() {
    }
    public User(String name, String password, String profile, String email, String phone, Integer salary) {
        this.name = name;
        this.password = password;
        this.profile = profile;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getProfile() {
        return profile;
    }
    public void setProfile(String profile) {
        this.profile = profile;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Integer getSalary() {
        return salary;
    }
    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    public List<FunBoxDto> getBoxesDto() {
        return boxesDto;
    }
    public void setBoxesDto(List<FunBoxDto> boxesDto) {
        this.boxesDto = boxesDto;
    }

}
