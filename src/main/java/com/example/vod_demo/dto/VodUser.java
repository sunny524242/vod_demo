package com.example.vod_demo.dto;

public class VodUser {
    //视频终端用户id
    private String accid;
    //视频终端用户姓名
    private String name;
    //视频终端用户属性
    private String props;
    //视频终端用户token
    private String token;

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProps() {
        return props;
    }

    public void setProps(String props) {
        this.props = props;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
