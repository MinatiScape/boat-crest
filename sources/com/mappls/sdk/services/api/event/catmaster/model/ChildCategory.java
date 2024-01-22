package com.mappls.sdk.services.api.event.catmaster.model;

import androidx.annotation.Keep;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@Keep
/* loaded from: classes2.dex */
public class ChildCategory {
    @SerializedName("desc")
    @Expose
    private Object desc;
    @SerializedName("expiry_in_hours")
    @Expose
    private double expiryInHours;
    @SerializedName(Constants.KEY_ICON)
    @Expose
    private String icon;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    private String name;
    @SerializedName("subChildCategories")
    @Expose
    private List<ChildCategory> subChildCategories;

    public Object getDesc() {
        return this.desc;
    }

    public double getExpiryInHours() {
        return this.expiryInHours;
    }

    public String getIcon() {
        return this.icon;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<ChildCategory> getSubChildCategories() {
        return this.subChildCategories;
    }

    public void setDesc(Object obj) {
        this.desc = obj;
    }

    public void setExpiryInHours(double d) {
        this.expiryInHours = d;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setSubChildCategories(List<ChildCategory> list) {
        this.subChildCategories = list;
    }
}
