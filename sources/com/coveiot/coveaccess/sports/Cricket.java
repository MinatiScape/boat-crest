package com.coveiot.coveaccess.sports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class Cricket {
    @SerializedName("enable")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private Boolean f6741a;
    @SerializedName("matchUpdates")
    @Expose
    private List<MatchUpdateRequest> b = null;
    @SerializedName("updateConfigs")
    private List<UpdateConfigsBean> c;

    public Boolean getEnable() {
        return this.f6741a;
    }

    public List<MatchUpdateRequest> getMatchUpdates() {
        return this.b;
    }

    public List<UpdateConfigsBean> getUpdateConfigs() {
        return this.c;
    }

    public void setEnable(Boolean bool) {
        this.f6741a = bool;
    }

    public void setMatchUpdates(List<MatchUpdateRequest> list) {
        this.b = list;
    }

    public void setUpdateConfigs(List<UpdateConfigsBean> list) {
        this.c = list;
    }
}
