package com.coveiot.coveaccess.model;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class CoveApiErrorModel extends CoveApiResponseBaseModel {
    @SerializedName(Constants.KEY_MESSAGE)
    private String msg;

    public CoveApiErrorModel(String str) {
        super(-1);
        this.msg = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public CoveApiErrorModel(String str, int i) {
        super(i);
        this.msg = str;
    }
}
