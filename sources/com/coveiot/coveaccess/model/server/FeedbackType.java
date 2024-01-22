package com.coveiot.coveaccess.model.server;
/* loaded from: classes8.dex */
public enum FeedbackType {
    SLEEP_COMPUTATION("SLEEP_COMPUTATION"),
    ENERGY_COMPUTATION("ENERGY_COMPUTATION"),
    STRESS_DAILY_DATA("STRESS_DAILY_DATA"),
    AUTO_RECOGNIZED_ACTIVITY("AUTO_RECOGNIZED_ACTIVITY"),
    FITNESS_VIDEO("FITNESS_VIDEO"),
    CRICKET_BOWLING("CRICKET_BOWLING"),
    CRICKET_BATTING("CRICKET_BATTING"),
    APP_RATING("APP_RATING");
    
    private String remindersType;

    FeedbackType(String str) {
        this.remindersType = str;
    }

    public String getRemindersType() {
        return this.remindersType;
    }
}
