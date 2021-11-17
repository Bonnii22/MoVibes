package com.example.movibes;


public class Owner {
    public String id;
    public Integer profileimage;
    public String establishmentname;
    public String address;
    public String name;
    public  String surname;
    public Integer contactnumber;



    public Owner(String id,Integer profileimage, String establishmentname, String address, String name, String surname, Integer contactnumber) {
        this.id = id;
        this.profileimage = profileimage;
        this.establishmentname = establishmentname;
        this.address = address;
        this.name = name;
        this.surname = surname;
        this.contactnumber = contactnumber;

    }
}
