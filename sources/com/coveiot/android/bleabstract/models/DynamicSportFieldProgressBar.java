package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class DynamicSportFieldProgressBar extends DynamicSportsField {
    public int g;
    public int h;
    public int i;
    public int j;

    public DynamicSportFieldProgressBar(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        super(i, i2, i3, i4);
        this.g = i5;
        this.h = i6;
        this.i = i7;
        this.j = i8;
    }

    public int getBarBackgroundImageId() {
        return this.g;
    }

    public int getBarEndImageId() {
        return this.i;
    }

    public int getBarPercentage() {
        return this.j;
    }

    public int getBarSlideImageId() {
        return this.h;
    }
}
