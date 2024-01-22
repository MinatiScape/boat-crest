package com.coveiot.android.bleabstract.response;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class LiveStepsData {

    /* renamed from: a  reason: collision with root package name */
    public int f3644a;
    public int b;
    public double c;
    public long d = System.currentTimeMillis();

    public final double getCalories() {
        return this.c;
    }

    public final int getLiveSteps() {
        return this.f3644a;
    }

    public final int getMeters() {
        return this.b;
    }

    public final long getTimeStamp() {
        return this.d;
    }

    public final void setCalories(double d) {
        this.c = d;
    }

    public final void setLiveSteps(int i) {
        this.f3644a = i;
    }

    public final void setMeters(int i) {
        this.b = i;
    }

    public final void setTimeStamp(long j) {
        this.d = j;
    }

    @NotNull
    public String toString() {
        return "LiveStepsData(liveSteps=" + this.f3644a + ", meters=" + this.b + ", calories=" + this.c + ", timeStamp=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
    }
}
