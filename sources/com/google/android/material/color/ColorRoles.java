package com.google.android.material.color;

import androidx.annotation.ColorInt;
/* loaded from: classes10.dex */
public final class ColorRoles {

    /* renamed from: a  reason: collision with root package name */
    public final int f10250a;
    public final int b;
    public final int c;
    public final int d;

    public ColorRoles(@ColorInt int i, @ColorInt int i2, @ColorInt int i3, @ColorInt int i4) {
        this.f10250a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    @ColorInt
    public int getAccent() {
        return this.f10250a;
    }

    @ColorInt
    public int getAccentContainer() {
        return this.c;
    }

    @ColorInt
    public int getOnAccent() {
        return this.b;
    }

    @ColorInt
    public int getOnAccentContainer() {
        return this.d;
    }
}
