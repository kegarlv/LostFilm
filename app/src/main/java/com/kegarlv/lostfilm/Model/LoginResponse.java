package com.kegarlv.lostfilm.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ivan on 29.03.17.
 */

public class LoginResponse {
    @SerializedName("name")
    private String name;

    @SerializedName("success")
    private boolean success;

    @SerializedName("result")
    private String result;

    public String getName() {
        return name;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getResult() {
        return result;
    }

    public LoginResponse() {
    }
}
