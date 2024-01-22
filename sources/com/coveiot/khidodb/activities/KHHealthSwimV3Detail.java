package com.coveiot.khidodb.activities;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes8.dex */
public final class KHHealthSwimV3Detail {

    /* renamed from: a  reason: collision with root package name */
    public int f7086a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;

    public final int getDifference_time() {
        return this.i;
    }

    public final int getDistance() {
        return this.e;
    }

    public final int getDuration() {
        return this.f7086a;
    }

    public final int getFrequency() {
        return this.f;
    }

    public final int getSpeed() {
        return this.g;
    }

    public final int getStop_time() {
        return this.h;
    }

    public final int getStrokesNumber() {
        return this.b;
    }

    public final int getSwimmingPosture() {
        return this.d;
    }

    public final int getSwolf() {
        return this.c;
    }

    public final void setDifference_time(int i) {
        this.i = i;
    }

    public final void setDistance(int i) {
        this.e = i;
    }

    public final void setDuration(int i) {
        this.f7086a = i;
    }

    public final void setFrequency(int i) {
        this.f = i;
    }

    public final void setSpeed(int i) {
        this.g = i;
    }

    public final void setStop_time(int i) {
        this.h = i;
    }

    public final void setStrokesNumber(int i) {
        this.b = i;
    }

    public final void setSwimmingPosture(int i) {
        this.d = i;
    }

    public final void setSwolf(int i) {
        this.c = i;
    }

    @NotNull
    public String toString() {
        return "KHHealthSwimV3Detail(duration=" + this.f7086a + ", strokesNumber=" + this.b + ", swolf=" + this.c + ", swimmingPosture=" + this.d + ", distance=" + this.e + ", frequency=" + this.f + ", speed=" + this.g + ", stop_time=" + this.h + ", difference_time=" + this.i + HexStringBuilder.COMMENT_END_CHAR;
    }
}
