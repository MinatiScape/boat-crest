package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class DynamicSportFieldTextV2 extends DynamicSportsField {
    public int g;
    public int h;
    public String i;
    public int j;

    public DynamicSportFieldTextV2(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str, int i9) {
        super(i, i2, i3, i4, i5, i6);
        this.g = i7;
        this.h = i8;
        this.i = str;
        this.j = i9;
    }

    public int getFont() {
        return this.g;
    }

    public int getFormat() {
        return this.j;
    }

    public String getText() {
        return this.i;
    }

    public int getTypeFace() {
        return this.h;
    }
}
