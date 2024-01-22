package com.coveiot.android.bleabstract.response;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class GetSensAISummaryDetailsResponse {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3623a;
    public long b;
    public int c;
    public int d;
    public long e;
    @Nullable
    public List<Integer> f;
    @Nullable
    public List<Integer> g;
    @Nullable
    public List<Integer> h;
    @Nullable
    public List<Integer> i;
    @Nullable
    public List<Integer> j;
    @Nullable
    public List<Integer> k;
    @Nullable
    public List<Integer> l;

    public final int getActivityType() {
        return this.c;
    }

    @Nullable
    public final List<Integer> getArmSpeedList() {
        return this.l;
    }

    @Nullable
    public final List<Integer> getCaloriesList() {
        return this.j;
    }

    public final int getDetailsDataNum() {
        return this.d;
    }

    @Nullable
    public final List<Integer> getDistanceList() {
        return this.i;
    }

    @Nullable
    public final List<Integer> getHandSpeedList() {
        return this.f;
    }

    @Nullable
    public final List<Integer> getHitOrMissList() {
        return this.k;
    }

    @Nullable
    public final List<Integer> getHrList() {
        return this.g;
    }

    public final long getSessionId() {
        return this.b;
    }

    @Nullable
    public final List<Integer> getStepsList() {
        return this.h;
    }

    public final long getTimeStamp() {
        return this.e;
    }

    public final boolean isComplete() {
        return this.f3623a;
    }

    public final void setActivityType(int i) {
        this.c = i;
    }

    public final void setArmSpeedList(@Nullable List<Integer> list) {
        this.l = list;
    }

    public final void setCaloriesList(@Nullable List<Integer> list) {
        this.j = list;
    }

    public final void setComplete(boolean z) {
        this.f3623a = z;
    }

    public final void setDetailsDataNum(int i) {
        this.d = i;
    }

    public final void setDistanceList(@Nullable List<Integer> list) {
        this.i = list;
    }

    public final void setHandSpeedList(@Nullable List<Integer> list) {
        this.f = list;
    }

    public final void setHitOrMissList(@Nullable List<Integer> list) {
        this.k = list;
    }

    public final void setHrList(@Nullable List<Integer> list) {
        this.g = list;
    }

    public final void setSessionId(long j) {
        this.b = j;
    }

    public final void setStepsList(@Nullable List<Integer> list) {
        this.h = list;
    }

    public final void setTimeStamp(long j) {
        this.e = j;
    }

    @NotNull
    public String toString() {
        return "GetSensAISummaryDetailsResponse(isComplete=" + this.f3623a + ", sessionId=" + this.b + ", activityType=" + this.c + ", detailsDataNum=" + this.d + ", timeStamp=" + this.e + ", handSpeedList=" + this.f + ", hrList=" + this.g + ", stepsList=" + this.h + ", distanceList=" + this.i + ", caloriesList=" + this.j + ", hitOrMissList=" + this.k + ", armSpeedList=" + this.l + HexStringBuilder.COMMENT_END_CHAR;
    }
}
