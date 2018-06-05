package com.art.code.psycare.Compent;

import java.io.Serializable;

public class User implements Serializable {
    private String mNickname;
    private String mPhone;
    private String mToken;

    public User(String nickname, String phone, String token) {
        this.mNickname = nickname;
        this.mPhone = phone;
        this.mToken = token;
    }

    public String getmNickname() {
        return this.mNickname;
    }

    public String getPhone() {
        return this.mPhone;
    }

    public String getmToken() {
        return this.mToken;
    }

    public void setPhone(String phone) {
        this.mPhone = phone;
    }

    public void setmNickname(String nickname) {
        this.mNickname = nickname;
    }

    public void setToken(String token) {
        this.mToken = token;
    }
}
