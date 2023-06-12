package com.uts.gogreen;

import com.google.gson.annotations.SerializedName;

public class ValueNoData {
    @SerializedName("success")
    private int success;

    @SerializedName("message")
    private int message;

    public int getSuccess() {
        return success;
    }

    public int getMessage() {
        return message;
    }
}
