package com.coveiot.covepreferences.data;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes8.dex */
public class AutoRecognitionData {

    /* renamed from: a  reason: collision with root package name */
    public String f7012a;
    public boolean b;
    public boolean c;
    public int d;
    public int e;
    public int f;
    public String g;
    public int h;

    public AutoRecognitionData(@NotNull String str, @NotNull String str2, boolean z, int i, int i2) {
        this.f7012a = str;
        this.b = z;
        this.g = str2;
        this.h = i;
        this.f = i2;
    }

    public int getActivityCategoryId() {
        return this.d;
    }

    public String getActivityCode() {
        return this.g;
    }

    public int getActivityId() {
        return this.e;
    }

    public int getByteOrderInFW() {
        return this.h;
    }

    public int getFwActivityId() {
        return this.f;
    }

    public String getTypeName() {
        return this.f7012a;
    }

    public boolean isEnabled() {
        return this.b;
    }

    public boolean isFromOneKActivity() {
        return this.c;
    }

    public void setActivityCategoryId(int i) {
        this.d = i;
    }

    public void setActivityCode(String str) {
        this.g = str;
    }

    public void setActivityId(int i) {
        this.e = i;
    }

    public void setByteOrderInFW(int i) {
        this.h = i;
    }

    public void setEnabled(boolean z) {
        this.b = z;
    }

    public void setFromOneKActivity(boolean z) {
        this.c = z;
    }

    public void setFwActivityId(int i) {
        this.f = i;
    }

    public void setTypeName(String str) {
        this.f7012a = str;
    }

    public AutoRecognitionData(@NotNull String str, boolean z) {
        this.f7012a = str;
        this.b = z;
    }

    public AutoRecognitionData(@NotNull String str, boolean z, boolean z2, int i, int i2, int i3, String str2, int i4) {
        this.f7012a = str;
        this.b = z;
        this.c = z2;
        this.d = i;
        this.e = i2;
        this.f = i3;
        this.g = str2;
        this.h = i4;
    }
}
