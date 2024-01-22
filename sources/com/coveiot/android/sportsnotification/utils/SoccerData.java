package com.coveiot.android.sportsnotification.utils;

import com.jstyle.blesdk1860.constant.BleConst;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SoccerData {

    /* renamed from: a  reason: collision with root package name */
    public int f5883a;
    @NotNull
    public String b = "";
    @NotNull
    public String c = "";
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
    public String i = BleConst.GetDeviceTime;
    @NotNull
    public String j = "";
    @NotNull
    public String k = BleConst.GetDeviceTime;
    @Nullable
    public Event l;
    @Nullable
    public SoccerSummary m;

    @Nullable
    public final Event getEvent() {
        return this.l;
    }

    public final int getGameState() {
        return this.f5883a;
    }

    @NotNull
    public final String getInjuryTime() {
        return this.k;
    }

    @NotNull
    public final String getLeageName() {
        return this.c;
    }

    @NotNull
    public final String getMatchTime() {
        return this.j;
    }

    @Nullable
    public final SoccerSummary getSoccerSummary() {
        return this.m;
    }

    @NotNull
    public final String getStartTime() {
        return this.b;
    }

    @NotNull
    public final String getTeamAPenaltyScore() {
        return this.h;
    }

    @NotNull
    public final String getTeamAScore() {
        return this.f;
    }

    @NotNull
    public final String getTeamAShortName() {
        return this.d;
    }

    @NotNull
    public final String getTeamBPenaltyScore() {
        return this.i;
    }

    @NotNull
    public final String getTeamBScore() {
        return this.g;
    }

    @NotNull
    public final String getTeamBShortName() {
        return this.e;
    }

    public final void setEvent(@Nullable Event event) {
        this.l = event;
    }

    public final void setGameState(int i) {
        this.f5883a = i;
    }

    public final void setInjuryTime(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.k = str;
    }

    public final void setLeageName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.c = str;
    }

    public final void setMatchTime(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.j = str;
    }

    public final void setSoccerSummary(@Nullable SoccerSummary soccerSummary) {
        this.m = soccerSummary;
    }

    public final void setStartTime(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.b = str;
    }

    public final void setTeamAPenaltyScore(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.h = str;
    }

    public final void setTeamAScore(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f = str;
    }

    public final void setTeamAShortName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.d = str;
    }

    public final void setTeamBPenaltyScore(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.i = str;
    }

    public final void setTeamBScore(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.g = str;
    }

    public final void setTeamBShortName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.e = str;
    }
}
