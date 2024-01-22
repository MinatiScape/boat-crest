package com.coveiot.coveaccess.fitnessbuddies.model.buddydetails;

import com.coveiot.android.tappy.utils.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class BuddyDetails implements Serializable {
    @SerializedName("dpUrl")
    @Expose
    public String dpUrl;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    public String name;
    @SerializedName(Constants.END_USER_GLOBAL_ID)
    @Expose
    public Integer userId;

    public String getDpUrl() {
        return this.dpUrl;
    }

    public String getName() {
        return this.name;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setDpUrl(String str) {
        this.dpUrl = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setUserId(Integer num) {
        this.userId = num;
    }
}
