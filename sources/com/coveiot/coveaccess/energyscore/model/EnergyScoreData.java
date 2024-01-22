package com.coveiot.coveaccess.energyscore.model;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class EnergyScoreData {
    @SerializedName("activityData")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private List<ActivityData> f6518a = null;
    @SerializedName("userInfo")
    @Expose
    private UserInfo b;

    public List<ActivityData> getActivityData() {
        return this.f6518a;
    }

    public UserInfo getUserInfo() {
        return this.b;
    }

    public void setActivityData(List<ActivityData> list) {
        this.f6518a = list;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.b = userInfo;
    }

    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
