package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class DynamicSportFieldButtonImage extends DynamicSportsField {
    public final int g;
    public final int h;
    public final int i;
    public final int j;
    public final int k;
    public final String l;

    public DynamicSportFieldButtonImage(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, String str) {
        super(i, i10, i4, i5, i6, i7);
        this.g = i2;
        this.h = i3;
        this.i = i8;
        this.j = i9;
        this.k = i11;
        this.l = str;
    }

    public int getButtonAction() {
        return this.i;
    }

    public int getButtonId() {
        return this.g;
    }

    public int getButtonImageId() {
        return this.j;
    }

    public String getButtonText() {
        return this.l;
    }

    public int getSideBySide() {
        return this.h;
    }

    public int getTextFontSize() {
        return this.k;
    }
}
