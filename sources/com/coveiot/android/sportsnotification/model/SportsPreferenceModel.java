package com.coveiot.android.sportsnotification.model;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SportsPreferenceModel {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f5877a;
    @Nullable
    public Boolean b;
    @Nullable
    public Integer c;

    @Nullable
    public final Integer getInterval() {
        return this.c;
    }

    @Nullable
    public final String getSport() {
        return this.f5877a;
    }

    @Nullable
    public final Boolean isEnable() {
        return this.b;
    }

    public final void setEnable(@Nullable Boolean bool) {
        this.b = bool;
    }

    public final void setInterval(@Nullable Integer num) {
        this.c = num;
    }

    public final void setSport(@Nullable String str) {
        this.f5877a = str;
    }
}
