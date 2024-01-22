package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class DynamicSportFieldBBText extends DynamicSportsField {
    public final int g;
    public final int h;
    public final String i;
    public final int j;
    public final int k;
    public final int l;
    public final int m;

    public DynamicSportFieldBBText(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str, int i9, int i10, int i11, int i12) {
        super(i, i2, i3, i4, i5, i6);
        this.g = i7;
        this.h = i8;
        this.i = str;
        this.j = i12;
        this.k = i9;
        this.l = i10;
        this.m = i11;
    }

    public int getAlignment() {
        return this.j;
    }

    public int getBbHeight() {
        return this.l;
    }

    public int getBbWidth() {
        return this.m;
    }

    public int getFont() {
        return this.g;
    }

    public int getFontSize() {
        return this.k;
    }

    public String getText() {
        return this.i;
    }

    public int getTypeFace() {
        return this.h;
    }
}
