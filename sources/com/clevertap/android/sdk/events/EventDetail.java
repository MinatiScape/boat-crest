package com.clevertap.android.sdk.events;
/* loaded from: classes2.dex */
public class EventDetail {

    /* renamed from: a  reason: collision with root package name */
    public final int f2608a;
    public final int b;
    public final int c;
    public final String d;

    public EventDetail(int i, int i2, int i3, String str) {
        this.f2608a = i;
        this.b = i2;
        this.c = i3;
        this.d = str;
    }

    public int getCount() {
        return this.f2608a;
    }

    public int getFirstTime() {
        return this.b;
    }

    public int getLastTime() {
        return this.c;
    }

    public String getName() {
        return this.d;
    }
}
