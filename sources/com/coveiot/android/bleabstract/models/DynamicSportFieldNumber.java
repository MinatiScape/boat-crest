package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class DynamicSportFieldNumber extends DynamicSportsField {
    public int g;
    public int h;
    public String i;

    public DynamicSportFieldNumber(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str) {
        super(i, i2, i3, i4, i5, i6);
        this.g = i7;
        this.h = i8;
        this.i = str;
    }

    public int getFont() {
        return this.g;
    }

    public String getNumber() {
        return this.i;
    }

    public int getTypeFace() {
        return this.h;
    }
}
