package com.coveiot.coveaccess.tappy.model;

import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class TappyCoveApiErrorModel extends CoveApiErrorModel {
    @SerializedName("Guid")
    @Expose
    private String guid;
    @SerializedName("tappyErrorCode")
    @Expose
    private String tappyErrorCode;

    public TappyCoveApiErrorModel(String str, int i, String str2, String str3) {
        super(str, i);
        this.tappyErrorCode = str2;
        this.guid = str3;
    }

    public String getGuid() {
        return this.guid;
    }

    public String getTappyErrorCode() {
        return this.tappyErrorCode;
    }

    public void setGuid(String str) {
        this.guid = str;
    }

    public void setTappyErrorCode(String str) {
        this.tappyErrorCode = str;
    }
}
