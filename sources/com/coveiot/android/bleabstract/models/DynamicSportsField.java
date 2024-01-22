package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public abstract class DynamicSportsField {

    /* renamed from: a  reason: collision with root package name */
    public int f3427a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;

    public DynamicSportsField(int i, int i2, int i3, int i4, int i5, int i6) {
        this.f3427a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = i6;
    }

    public int getColor_rgb_565() {
        return this.b;
    }

    public int getDynamicFieldId() {
        return this.f3427a;
    }

    public int getLength() {
        return this.e;
    }

    public int getWidth() {
        return this.f;
    }

    public int getxPosition() {
        return this.c;
    }

    public int getyPosition() {
        return this.d;
    }

    public DynamicSportsField(int i, int i2, int i3, int i4) {
        this.f3427a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }
}
