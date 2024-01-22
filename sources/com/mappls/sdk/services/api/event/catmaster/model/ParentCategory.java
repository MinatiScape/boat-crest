package com.mappls.sdk.services.api.event.catmaster.model;

import androidx.annotation.Keep;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@Keep
/* loaded from: classes2.dex */
public class ParentCategory {
    @SerializedName("childCategories")
    @Expose
    private List<ChildCategory> childCategories;
    @SerializedName(Constants.KEY_ICON)
    @Expose
    private String icon;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    private String name;

    public List<ChildCategory> getChildCategories() {
        return this.childCategories;
    }

    public String getIcon() {
        return this.icon;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setChildCategories(List<ChildCategory> list) {
        this.childCategories = list;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public void setName(String str) {
        this.name = str;
    }
}
