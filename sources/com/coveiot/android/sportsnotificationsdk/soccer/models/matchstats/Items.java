package com.coveiot.android.sportsnotificationsdk.soccer.models.matchstats;

import com.coveiot.android.sportsnotificationsdk.soccer.models.common.MatchInfo;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes7.dex */
public class Items {
    @SerializedName("match_info")

    /* renamed from: a  reason: collision with root package name */
    private List<MatchInfo> f5994a;
    @SerializedName("statistics")
    private List<Statistics> b;

    public List<MatchInfo> getMatchInfo() {
        return this.f5994a;
    }

    public List<Statistics> getStatistics() {
        return this.b;
    }

    public void setMatchInfo(List<MatchInfo> list) {
        this.f5994a = list;
    }

    public void setStatistics(List<Statistics> list) {
        this.b = list;
    }
}
