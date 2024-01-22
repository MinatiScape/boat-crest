package com.coveiot.android.bleabstract.response;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class GetSOSConfigResponse {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3618a;
    public int b;
    @NotNull
    public String c = "";
    @NotNull
    public String d = "";

    @NotNull
    public final String getContactName() {
        return this.c;
    }

    @NotNull
    public final String getContactNumber() {
        return this.d;
    }

    public final int getTimer() {
        return this.b;
    }

    public final boolean isSOSEnable() {
        return this.f3618a;
    }

    public final void setContactName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.c = str;
    }

    public final void setContactNumber(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.d = str;
    }

    public final void setSOSEnable(boolean z) {
        this.f3618a = z;
    }

    public final void setTimer(int i) {
        this.b = i;
    }

    @NotNull
    public String toString() {
        return "GetSOSConfigResponse(isSOSEnable=" + this.f3618a + ", timer=" + this.b + ", contactName='" + this.c + "', contactNumber='" + this.d + "')";
    }
}
