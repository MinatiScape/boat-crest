package com.coveiot.coveaccess.watchface.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class WatchfaceByIdRequest implements Serializable {
    @SerializedName("faceType")
    private String faceType = null;
    @SerializedName("uids")
    private List<String> uids = null;

    public String getFaceType() {
        return this.faceType;
    }

    public List<String> getUids() {
        return this.uids;
    }

    public void setFaceType(String str) {
        this.faceType = str;
    }

    public void setUids(List<String> list) {
        this.uids = list;
    }
}
