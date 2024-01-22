package com.coveiot.android.sportsnotification.model;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.io.Serializable;
/* loaded from: classes7.dex */
public class Options implements Serializable {
    @SerializedName(SavingTrackHelper.POINT_COL_CATEGORY)
    private String category;
    @SerializedName("cid")
    private Integer cid;
    @SerializedName("displayName")
    private String displayName;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    private String name;
    @SerializedName("optionId")
    private Integer optionId;

    public String getCategory() {
        return this.category;
    }

    public Integer getCid() {
        return this.cid;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getName() {
        return this.name;
    }

    public Integer getOptionId() {
        return this.optionId;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public void setCid(Integer num) {
        this.cid = num;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setOptionId(Integer num) {
        this.optionId = num;
    }
}
