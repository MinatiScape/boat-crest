package com.coveiot.android.sportsnotification.model;
/* loaded from: classes7.dex */
public final class SelectedMatchLastStateData {

    /* renamed from: a  reason: collision with root package name */
    public int f5874a;
    public int b;
    public int c;
    public long d;

    public final int getGameStatus() {
        return this.c;
    }

    public final long getLastStateTimestamp() {
        return this.d;
    }

    public final int getMatchId() {
        return this.f5874a;
    }

    public final int getMatchStatus() {
        return this.b;
    }

    public final void setGameStatus(int i) {
        this.c = i;
    }

    public final void setLastStateTimestamp(long j) {
        this.d = j;
    }

    public final void setMatchId(int i) {
        this.f5874a = i;
    }

    public final void setMatchStatus(int i) {
        this.b = i;
    }
}
