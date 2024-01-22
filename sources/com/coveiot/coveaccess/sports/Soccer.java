package com.coveiot.coveaccess.sports;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class Soccer {
    @SerializedName("enable")

    /* renamed from: a  reason: collision with root package name */
    private Boolean f6748a;
    @SerializedName("matchUpdates")
    private List<MatchUpdateRequest> b;

    public Boolean getEnable() {
        return this.f6748a;
    }

    public List<MatchUpdateRequest> getMatchUpdates() {
        return this.b;
    }

    public void setEnable(Boolean bool) {
        this.f6748a = bool;
    }

    public void setMatchUpdates(List<MatchUpdateRequest> list) {
        this.b = list;
    }
}
