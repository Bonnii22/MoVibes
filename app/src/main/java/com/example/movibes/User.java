package com.example.movibes;

public class User {
    public String id;
    public Integer profileimage;
    public String name;
    public String surname;
    public String gender;
    public String citytown;
    public String interests;


    public User(String id, Integer profileimage, String name, String surname, String gender, String citytown, String interests) {
        this.id = id;
        this.profileimage = profileimage;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.citytown = citytown;
        this.interests = interests;
    }
}
