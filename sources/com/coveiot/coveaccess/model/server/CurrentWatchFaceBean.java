package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class CurrentWatchFaceBean {
    @SerializedName("appliedDate")
    private String appliedDate;
    @SerializedName("faceId")
    private String faceId;
    @SerializedName("faceType")
    private String faceType;
    @SerializedName("uid")
    private String uid;

    public String getAppliedDate() {
        return this.appliedDate;
    }

    public String getFaceId() {
        return this.faceId;
    }

    public String getFaceType() {
        return this.faceType;
    }

    public String getUid() {
        return this.uid;
    }

    public void setAppliedDate(String str) {
        this.appliedDate = str;
    }

    public void setFaceId(String str) {
        this.faceId = str;
    }

    public void setFaceType(String str) {
        this.faceType = str;
    }

    public void setUid(String str) {
        this.uid = str;
    }
}
