package com.coveiot.android.bleabstract.models;

import java.util.List;
/* loaded from: classes2.dex */
public class ActivityConfigMetaData {

    /* renamed from: a  reason: collision with root package name */
    public int f3389a;
    public int b;
    public int c;
    public String d;
    public int e;
    public int f;
    public String g;
    public int h;
    public ActivityDataType i;
    public int j;
    public int k;
    public List<METData> l;

    public ActivityConfigMetaData(int i, int i2, int i3, String str, int i4, int i5, String str2, int i6, ActivityDataType activityDataType, int i7, int i8, List<METData> list) {
        this.f3389a = i;
        this.b = i2;
        this.c = i3;
        this.d = str;
        this.e = i4;
        this.f = i5;
        this.g = str2;
        this.h = i6;
        this.i = activityDataType;
        this.j = i7;
        this.k = i8;
        this.l = list;
    }

    public int getActivityId() {
        return this.e;
    }

    public int getActivityImageId() {
        return this.f;
    }

    public int getActivityNum() {
        return this.f3389a;
    }

    public String getActivityUniCode() {
        return this.g;
    }

    public List<METData> getBleMetDataList() {
        return this.l;
    }

    public int getCategoryId() {
        return this.b;
    }

    public int getCategoryImageId() {
        return this.c;
    }

    public String getCategoryUnicode() {
        return this.d;
    }

    public ActivityDataType getDataType() {
        return this.i;
    }

    public int getMET_NUM() {
        return this.k;
    }

    public int getMET_TYPE() {
        return this.j;
    }

    public int getOrderId() {
        return this.h;
    }
}
