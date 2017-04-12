package com.chachati.asistencia;

public class LoginBean {
    private String email;
    private String pass; 

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public LoginBean() {
        
    }

    public LoginBean(String username, String password) {
       this.email = username;
       this.pass = password;
    }

}
