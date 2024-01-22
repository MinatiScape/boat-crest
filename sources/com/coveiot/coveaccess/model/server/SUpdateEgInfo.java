package com.coveiot.coveaccess.model.server;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SUpdateEgInfo {
    @SerializedName("googleAdId")
    private String googleAdId;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    private String name;
    @SerializedName("oldGoogleAdId")
    private String oldGoogleAdId;
    @SerializedName("shortDesc")
    private String shortDesc;

    public String getGoogleAdId() {
        return this.googleAdId;
    }

    public String getName() {
        return this.name;
    }

    public String getOldGoogleAdId() {
        return this.oldGoogleAdId;
    }

    public String getShortDesc() {
        return this.shortDesc;
    }

    public void setGoogleAdId(String str) {
        this.googleAdId = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setOldGoogleAdId(String str) {
        this.oldGoogleAdId = str;
    }

    public void setShortDesc(String str) {
        this.shortDesc = str;
    }
}
