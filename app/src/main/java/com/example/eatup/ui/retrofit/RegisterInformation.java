package com.example.eatup.ui.retrofit;

public class RegisterInformation {
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private String confirmpass;

    public void setEmail(String email){
        this.email = email;
    }

    public void setFirstName(String first_name){
        this.first_name = first_name;
    }

    public void setLastName(String last_name){
        this.last_name = last_name;
    }

    public void setConfirmpass(String confirmpass){
        this.confirmpass = confirmpass;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
