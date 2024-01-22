package com.coveiot.coveaccess.activitysession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class fitnessActivitySessions {
    @SerializedName("fitnessActivitySessions")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private List<PostActivitySessionDataRequest> f6405a = null;

    public List<PostActivitySessionDataRequest> getFitnessActivitySessions() {
        return this.f6405a;
    }

    public void setFitnessActivitySessions(List<PostActivitySessionDataRequest> list) {
        this.f6405a = list;
    }
}
