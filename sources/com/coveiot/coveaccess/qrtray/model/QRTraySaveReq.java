package com.coveiot.coveaccess.qrtray.model;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class QRTraySaveReq implements Serializable {
    @SerializedName("applied")
    @Expose
    public Boolean applied;
    @SerializedName("categoryId")
    @Expose
    public String categoryId;
    @SerializedName("imageRefId")
    @Expose
    public Integer imageRefId;
    @SerializedName("mediaId")
    @Expose
    public String mediaId;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    public String name;
    @SerializedName("previousAppliedId")
    @Expose
    public Object previousAppliedId;

    public QRTraySaveReq(String str, String str2, String str3, Boolean bool, Integer num, Object obj) {
        this.name = str;
        this.categoryId = str2;
        this.mediaId = str3;
        this.applied = bool;
        this.imageRefId = num;
        this.previousAppliedId = obj;
    }

    public Boolean getApplied() {
        return this.applied;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public Integer getImageRefId() {
        return this.imageRefId;
    }

    public String getMediaId() {
        return this.mediaId;
    }

    public String getName() {
        return this.name;
    }

    public Object getPreviousAppliedId() {
        return this.previousAppliedId;
    }

    public void setApplied(Boolean bool) {
        this.applied = bool;
    }

    public void setCategoryId(String str) {
        this.categoryId = str;
    }

    public void setImageRefId(Integer num) {
        this.imageRefId = num;
    }

    public void setMediaId(String str) {
        this.mediaId = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPreviousAppliedId(Object obj) {
        this.previousAppliedId = obj;
    }
}
