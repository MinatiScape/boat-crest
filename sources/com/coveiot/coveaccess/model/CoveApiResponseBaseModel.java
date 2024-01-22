package com.coveiot.coveaccess.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class CoveApiResponseBaseModel implements Serializable {
    @SerializedName("httpCode")
    private int code;

    public CoveApiResponseBaseModel(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }
}
