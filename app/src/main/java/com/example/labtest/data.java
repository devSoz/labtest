package com.example.labtest;

public class data {

    // string variable for
    // storing employee name.
    private String email;

    // string variable for storing
    // employee contact number
    private String password;
    private String age;
    private String city;
    private String pno;
    // string variable for storing
    // employee address.

    // an empty constructor is
    // required when using
    // Firebase Realtime Database.
    public data() {

    }

    // created getter and setter methods
    // for all our variables.

    // created getter and setter methods
    // for all our variables.
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }


}

