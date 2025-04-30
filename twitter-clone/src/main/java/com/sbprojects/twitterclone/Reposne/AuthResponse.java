package com.sbprojects.twitterclone.Reposne;

import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;

    private boolean status;

    public AuthResponse(String jwt, boolean status) {
        this.jwt = jwt;
        this.status = status;
    }

    public AuthResponse() {
    }

    public String toString() {
        return "AuthResponse(jwt=" + this.getJwt() + ", status=" + this.isStatus() + ")";
    }
//
//    public void setJwt(String jwt) {
//        this.jwt = jwt;
//    }
//
//    public void setStatus(boolean status) {
//        this.status = status;
//    }
//
//    public String getJwt() {
//        return this.jwt;
//    }
//
//    public boolean isStatus() {
//        return this.status;
//    }

//    private String message;
}
