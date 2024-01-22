package com.coveiot.android.weeklyreport.utils;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes8.dex */
public enum WeeklyReportConstant {
    VERIFY("VERIFY"),
    SUBSCRIBE_SUCCESS("SUBSCRIBE_SUCCESS"),
    UNSUBSCRIBE("UNSUBSCRIBE"),
    FINISH("FINISH"),
    EDIT_EMAIL("EDIT_EMAIL"),
    UPDATE_EMAIL("UPDATE_EMAIL"),
    HOME_DASHBOARD("HOME_DASHBOARD"),
    EMAIL_ID("EMAIL_ID"),
    EMAIL("EMAIL"),
    WEEKLY_FITNESS_REPORT("WEEKLY_FITNESS_REPORT"),
    EMPTY_STRING("");
    
    @NotNull
    private final String value;

    WeeklyReportConstant(String str) {
        this.value = str;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }
}
