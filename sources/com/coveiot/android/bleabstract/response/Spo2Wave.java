package com.coveiot.android.bleabstract.response;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class Spo2Wave {

    /* renamed from: a  reason: collision with root package name */
    public int f3665a;
    public int b;

    public Spo2Wave(int i, int i2) {
        this.f3665a = i;
        this.b = i2;
    }

    public final int getData() {
        return this.f3665a;
    }

    public final int getFlag() {
        return this.b;
    }

    public final void setData(int i) {
        this.f3665a = i;
    }

    public final void setFlag(int i) {
        this.b = i;
    }

    @NotNull
    public String toString() {
        return "Wave [data=" + this.f3665a + ", flag=" + this.b + ']';
    }
}
