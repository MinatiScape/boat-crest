package com.coveiot.android.sportsnotificationsdk.soccer.models.matchinfo;

import com.coveiot.android.sportsnotificationsdk.soccer.models.common.MatchInfo;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes7.dex */
public class Items {
    @SerializedName("match_info")

    /* renamed from: a  reason: collision with root package name */
    private List<MatchInfo> f5987a;
    @SerializedName("match_projection")
    private List<MatchProjection> b;
    @SerializedName("event")
    private List<Event> c;

    public List<Event> getEvent() {
        return this.c;
    }

    public List<MatchInfo> getMatchInfo() {
        return this.f5987a;
    }

    public List<MatchProjection> getMatchProjection() {
        return this.b;
    }

    public void setEvent(List<Event> list) {
        this.c = list;
    }

    public void setMatchInfo(List<MatchInfo> list) {
        this.f5987a = list;
    }

    public void setMatchProjection(List<MatchProjection> list) {
        this.b = list;
    }
}
