package com.coveiot.android.sportsnotification;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public enum SportsType {
    Cricket("Cricket"),
    Football("Football");
    
    @NotNull
    private final String sport;

    SportsType(String str) {
        this.sport = str;
    }

    @NotNull
    public final String getSport() {
        return this.sport;
    }
}
