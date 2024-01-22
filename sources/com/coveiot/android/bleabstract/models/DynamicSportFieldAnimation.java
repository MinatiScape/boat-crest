package com.coveiot.android.bleabstract.models;

import java.util.ArrayList;
/* loaded from: classes2.dex */
public class DynamicSportFieldAnimation extends DynamicSportsField {
    public int g;
    public int h;
    public int i;
    public ArrayList<Integer> j;

    public DynamicSportFieldAnimation(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, ArrayList<Integer> arrayList) {
        super(i, i2, i3, i4, i5, i6);
        this.j = new ArrayList<>();
        this.g = i7;
        this.h = i8;
        this.i = i9;
        this.j = arrayList;
    }

    public int getFormat() {
        return this.g;
    }

    public int getFrameTime() {
        return this.h;
    }

    public ArrayList<Integer> getImgIdList() {
        return this.j;
    }

    public int getImgNum() {
        return this.i;
    }
}
