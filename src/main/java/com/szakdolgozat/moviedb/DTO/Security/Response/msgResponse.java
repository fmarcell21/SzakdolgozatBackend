package com.szakdolgozat.moviedb.DTO.Security.Response;

public class msgResponse {

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
    public msgResponse(String msg){
        this.msg = msg;
    }


}
