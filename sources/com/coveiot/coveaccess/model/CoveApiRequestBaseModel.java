package com.coveiot.coveaccess.model;

import com.coveiot.coveaccess.utils.CoveUtil;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.HashMap;
/* loaded from: classes8.dex */
public class CoveApiRequestBaseModel implements Serializable {
    @SerializedName("headers")
    private HashMap<String, String> headers;

    public CoveApiRequestBaseModel(HashMap<String, String> hashMap) {
        this.headers = CoveUtil.createHeaderCopy(hashMap);
    }

    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    public CoveApiRequestBaseModel() {
        this.headers = null;
    }
}
