package com.coveiot.coveaccess.onekactivity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.util.List;
/* loaded from: classes8.dex */
public class CategoryItem {
    @SerializedName("categoryId")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private Integer f6671a;
    @SerializedName("title")
    @Expose
    private String b;
    @SerializedName(SavingTrackHelper.POINT_COL_DESCRIPTION)
    @Expose
    private String c;
    @SerializedName("iconUrl")
    @Expose
    private String d;
    @SerializedName("deviceIcons")
    @Expose
    private List<DeviceIcon> e = null;

    public Integer getCategoryId() {
        return this.f6671a;
    }

    public String getDescription() {
        return this.c;
    }

    public List<DeviceIcon> getDeviceIcons() {
        return this.e;
    }

    public String getIconUrl() {
        return this.d;
    }

    public String getTitle() {
        return this.b;
    }

    public void setCategoryId(Integer num) {
        this.f6671a = num;
    }

    public void setDescription(String str) {
        this.c = str;
    }

    public void setDeviceIcons(List<DeviceIcon> list) {
        this.e = list;
    }

    public void setIconUrl(String str) {
        this.d = str;
    }

    public void setTitle(String str) {
        this.b = str;
    }
}
