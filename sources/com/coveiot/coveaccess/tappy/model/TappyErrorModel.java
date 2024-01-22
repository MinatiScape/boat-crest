package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class TappyErrorModel implements Serializable {
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("Guid")
    @Expose
    private String guid;
    @SerializedName("HttpStatusCode")
    @Expose
    private Integer httpStatusCode;
    @SerializedName("Message")
    @Expose
    private String message;

    public String getCode() {
        return this.code;
    }

    public String getGuid() {
        return this.guid;
    }

    public Integer getHttpStatusCode() {
        return this.httpStatusCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setGuid(String str) {
        this.guid = str;
    }

    public void setHttpStatusCode(Integer num) {
        this.httpStatusCode = num;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
