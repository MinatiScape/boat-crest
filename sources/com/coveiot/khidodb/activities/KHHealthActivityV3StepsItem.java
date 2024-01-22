package com.coveiot.khidodb.activities;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes8.dex */
public final class KHHealthActivityV3StepsItem {

    /* renamed from: a  reason: collision with root package name */
    public int f7085a;
    public int b;
    public int c;

    public final int getCalories() {
        return this.b;
    }

    public final int getDistance() {
        return this.c;
    }

    public final int getSteps() {
        return this.f7085a;
    }

    public final void setCalories(int i) {
        this.b = i;
    }

    public final void setDistance(int i) {
        this.c = i;
    }

    public final void setSteps(int i) {
        this.f7085a = i;
    }

    @NotNull
    public String toString() {
        return "KHHealthActivityV3StepsItem{steps=" + this.f7085a + ", calories=" + this.b + ", distance=" + this.c + '}';
    }
}
