package com.coveiot.leaderboard.model;

import com.coveiot.coveaccess.leaderboard.model.FilterType;
import com.coveiot.coveaccess.leaderboard.model.RankType;
/* loaded from: classes9.dex */
public class FilterEvent {
    private FilterType filterType;
    private RankType rankType;

    public FilterEvent(RankType rankType, FilterType filterType) {
        this.rankType = rankType;
        this.filterType = filterType;
    }

    public FilterType getFilterType() {
        return this.filterType;
    }

    public RankType getRankType() {
        return this.rankType;
    }
}
