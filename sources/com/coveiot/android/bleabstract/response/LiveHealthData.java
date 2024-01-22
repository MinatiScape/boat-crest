package com.coveiot.android.bleabstract.response;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class LiveHealthData {

    /* renamed from: a  reason: collision with root package name */
    public int f3641a;
    public int b;
    public int c;
    public int d;
    public int e;

    public final int getDiastolicBp() {
        return this.c;
    }

    public final int getHeartRate() {
        return this.f3641a;
    }

    public final int getRr() {
        return this.e;
    }

    public final int getStress() {
        return this.d;
    }

    public final int getSystolicBp() {
        return this.b;
    }

    public final void setDiastolicBp(int i) {
        this.c = i;
    }

    public final void setHeartRate(int i) {
        this.f3641a = i;
    }

    public final void setRr(int i) {
        this.e = i;
    }

    public final void setStress(int i) {
        this.d = i;
    }

    public final void setSystolicBp(int i) {
        this.b = i;
    }

    @NotNull
    public String toString() {
        return "LiveHealthData(heartRate=" + this.f3641a + ", systolicBp=" + this.b + ", diastolicBp=" + this.c + ", stress=" + this.d + ", rr=" + this.e + HexStringBuilder.COMMENT_END_CHAR;
    }
}
