package com.coveiot.coveaccess.leaderboard.model;

import com.mappls.sdk.services.api.weather.WeatherCriteria;
/* loaded from: classes8.dex */
public enum RankType {
    DAY(WeatherCriteria.UNIT_TYPE_DAY),
    MONTH("month"),
    WEEK("week");
    
    private String rankType;

    RankType(String str) {
        this.rankType = str;
    }

    public String getRankType() {
        return this.rankType;
    }
}
