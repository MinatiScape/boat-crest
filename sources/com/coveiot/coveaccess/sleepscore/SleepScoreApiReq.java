package com.coveiot.coveaccess.sleepscore;

import com.coveiot.coveaccess.sleepscore.model.SleepData;
import com.coveiot.coveaccess.sleepscore.model.SleepHistory;
import com.coveiot.coveaccess.sleepscore.model.UserInfo;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SleepScoreApiReq {
    @SerializedName("sleepData")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private List<SleepData> f6709a = null;
    @SerializedName("sleepHistory")
    @Expose
    private List<SleepHistory> b = null;
    @SerializedName("userInfo")
    @Expose
    private UserInfo c;

    public List<SleepData> getSleepData() {
        return this.f6709a;
    }

    public List<SleepHistory> getSleepHistory() {
        return this.b;
    }

    public UserInfo getUserInfo() {
        return this.c;
    }

    public void setSleepData(List<SleepData> list) {
        this.f6709a = list;
    }

    public void setSleepHistory(List<SleepHistory> list) {
        this.b = list;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.c = userInfo;
    }

    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
