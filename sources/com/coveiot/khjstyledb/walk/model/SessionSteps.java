package com.coveiot.khjstyledb.walk.model;

import com.google.android.material.color.c;
import kotlin.Metadata;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u001e\u0010\u001fR\"\u0010\t\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\u0011\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\"\u0010\u0019\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\u001d\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0014\u001a\u0004\b\u001b\u0010\u0016\"\u0004\b\u001c\u0010\u0018¨\u0006 "}, d2 = {"Lcom/coveiot/khjstyledb/walk/model/SessionSteps;", "", "", "a", "I", "getStepsValue", "()I", "setStepsValue", "(I)V", "stepsValue", "", "b", "J", "getStepsTimeStamp", "()J", "setStepsTimeStamp", "(J)V", "stepsTimeStamp", "", c.f10260a, "D", "getDistance", "()D", "setDistance", "(D)V", "distance", "d", "getCalories", "setCalories", "calories", "<init>", "()V", "khjstyledb_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes8.dex */
public final class SessionSteps {

    /* renamed from: a  reason: collision with root package name */
    public int f7133a;
    public long b;
    public double c;
    public double d;

    public final double getCalories() {
        return this.d;
    }

    public final double getDistance() {
        return this.c;
    }

    public final long getStepsTimeStamp() {
        return this.b;
    }

    public final int getStepsValue() {
        return this.f7133a;
    }

    public final void setCalories(double d) {
        this.d = d;
    }

    public final void setDistance(double d) {
        this.c = d;
    }

    public final void setStepsTimeStamp(long j) {
        this.b = j;
    }

    public final void setStepsValue(int i) {
        this.f7133a = i;
    }
}
