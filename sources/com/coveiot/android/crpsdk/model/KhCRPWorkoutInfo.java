package com.coveiot.android.crpsdk.model;

import java.io.Serializable;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u001e\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b%\u0010&R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\n\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR$\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R$\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018R$\u0010\u001c\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u0014\u001a\u0004\b\u001d\u0010\u0016\"\u0004\b\u001e\u0010\u0018R$\u0010\u001f\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010\u0014\u001a\u0004\b \u0010\u0016\"\u0004\b!\u0010\u0018R\"\u0010\"\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\"\u0010\u0004\u001a\u0004\b#\u0010\u0006\"\u0004\b$\u0010\b¨\u0006'"}, d2 = {"Lcom/coveiot/android/crpsdk/model/KhCRPWorkoutInfo;", "Ljava/io/Serializable;", "", "type", "I", "getType", "()I", "setType", "(I)V", "", "startTime", "Ljava/lang/Long;", "getStartTime", "()Ljava/lang/Long;", "setStartTime", "(Ljava/lang/Long;)V", "endTime", "getEndTime", "setEndTime", "validTime", "Ljava/lang/Integer;", "getValidTime", "()Ljava/lang/Integer;", "setValidTime", "(Ljava/lang/Integer;)V", "steps", "getSteps", "setSteps", "distance", "getDistance", "setDistance", "calories", "getCalories", "setCalories", "syncStatus", "getSyncStatus", "setSyncStatus", "<init>", "()V", "crpSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class KhCRPWorkoutInfo implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public int f4123a;
    @Nullable
    public Long b;
    @Nullable
    public Long c;
    @Nullable
    public Integer d;
    @Nullable
    public Integer e;
    @Nullable
    public Integer f;
    @Nullable
    public Integer g;
    public int h;

    @Nullable
    public final Integer getCalories() {
        return this.g;
    }

    @Nullable
    public final Integer getDistance() {
        return this.f;
    }

    @Nullable
    public final Long getEndTime() {
        return this.c;
    }

    @Nullable
    public final Long getStartTime() {
        return this.b;
    }

    @Nullable
    public final Integer getSteps() {
        return this.e;
    }

    public final int getSyncStatus() {
        return this.h;
    }

    public final int getType() {
        return this.f4123a;
    }

    @Nullable
    public final Integer getValidTime() {
        return this.d;
    }

    public final void setCalories(@Nullable Integer num) {
        this.g = num;
    }

    public final void setDistance(@Nullable Integer num) {
        this.f = num;
    }

    public final void setEndTime(@Nullable Long l) {
        this.c = l;
    }

    public final void setStartTime(@Nullable Long l) {
        this.b = l;
    }

    public final void setSteps(@Nullable Integer num) {
        this.e = num;
    }

    public final void setSyncStatus(int i) {
        this.h = i;
    }

    public final void setType(int i) {
        this.f4123a = i;
    }

    public final void setValidTime(@Nullable Integer num) {
        this.d = num;
    }
}
