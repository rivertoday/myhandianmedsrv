package com.hrsb.cg.model;
public class Msguser {
    private Integer id;
    
    private String msgname;

    private String msgpass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsgname() {
        return msgname;
    }

    public void setMsgname(String msgname) {
        this.msgname = msgname == null ? null : msgname.trim();
    }

    public String getMsgpass() {
        return msgpass;
    }

    public void setMsgpass(String msgpass) {
        this.msgpass = msgpass == null ? null : msgpass.trim();
    }
}