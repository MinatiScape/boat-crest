package com.coveiot.android.bleabstract;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class NotificationInfo {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f2911a;
    @Nullable
    public String b;
    public int c;
    public int d;
    @Nullable
    public String e;

    public final int getAppColor() {
        return this.d;
    }

    @Nullable
    public final String getAppDesc() {
        return this.b;
    }

    public final int getAppIcon() {
        return this.c;
    }

    @Nullable
    public final String getAppLauncherActivity() {
        return this.e;
    }

    @Nullable
    public final String getAppName() {
        return this.f2911a;
    }

    public final void setAppColor(int i) {
        this.d = i;
    }

    public final void setAppDesc(@Nullable String str) {
        this.b = str;
    }

    public final void setAppIcon(int i) {
        this.c = i;
    }

    public final void setAppLauncherActivity(@Nullable String str) {
        this.e = str;
    }

    public final void setAppName(@Nullable String str) {
        this.f2911a = str;
    }
}
