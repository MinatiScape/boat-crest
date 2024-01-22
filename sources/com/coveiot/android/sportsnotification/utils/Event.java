package com.coveiot.android.sportsnotification.utils;

import com.jstyle.blesdk1860.constant.BleConst;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class Event {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public EventType f5882a;
    public boolean c;
    @NotNull
    public String b = "";
    @NotNull
    public String d = "";
    @NotNull
    public String e = "";
    @NotNull
    public String f = "";
    @NotNull
    public String g = "";
    @NotNull
    public String h = BleConst.GetDeviceTime;

    @NotNull
    public final String getInjuryTime() {
        return this.h;
    }

    @NotNull
    public final String getPlayerIn() {
        return this.e;
    }

    @NotNull
    public final String getPlayerName() {
        return this.d;
    }

    @NotNull
    public final String getPlayerOut() {
        return this.f;
    }

    @NotNull
    public final String getTeamName() {
        return this.b;
    }

    @NotNull
    public final String getTime() {
        return this.g;
    }

    @Nullable
    public final EventType getType() {
        return this.f5882a;
    }

    public final boolean isHomeTeam() {
        return this.c;
    }

    public final void setHomeTeam(boolean z) {
        this.c = z;
    }

    public final void setInjuryTime(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.h = str;
    }

    public final void setPlayerIn(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.e = str;
    }

    public final void setPlayerName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.d = str;
    }

    public final void setPlayerOut(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f = str;
    }

    public final void setTeamName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.b = str;
    }

    public final void setTime(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.g = str;
    }

    public final void setType(@Nullable EventType eventType) {
        this.f5882a = eventType;
    }
}
