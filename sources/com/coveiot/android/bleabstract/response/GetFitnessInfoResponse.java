package com.coveiot.android.bleabstract.response;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class GetFitnessInfoResponse {

    /* renamed from: a  reason: collision with root package name */
    public int f3609a;
    public int b;
    public int c;
    public boolean d = true;
    public boolean e = true;
    @NotNull
    public String f = "";

    public final int getAge() {
        return this.f3609a;
    }

    public final int getHeight() {
        return this.b;
    }

    @NotNull
    public final String getSkinColor() {
        return this.f;
    }

    public final int getWeight() {
        return this.c;
    }

    public final boolean isLeftHand() {
        return this.d;
    }

    public final boolean isMale() {
        return this.e;
    }

    public final void setAge(int i) {
        this.f3609a = i;
    }

    public final void setHeight(int i) {
        this.b = i;
    }

    public final void setLeftHand(boolean z) {
        this.d = z;
    }

    public final void setMale(boolean z) {
        this.e = z;
    }

    public final void setSkinColor(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f = str;
    }

    public final void setWeight(int i) {
        this.c = i;
    }
}
