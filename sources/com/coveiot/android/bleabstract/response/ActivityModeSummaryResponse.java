package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.models.ActivityGPSSample;
import com.coveiot.android.bleabstract.models.ActivityHeartRateSample;
import com.coveiot.android.bleabstract.models.ActivityStepsSample;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityModeSummaryResponse {
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    @Nullable
    public TimeSpentHeartRateZone F;
    public int G;
    public int H;
    public int I;
    public int J;
    public int K;
    public int L;
    public int M;
    public int N;
    public int O;
    public int P;
    public int Q;
    @Nullable
    public String c;
    @Nullable
    public String d;
    public int e;
    public int f;
    public double g;
    public double h;
    public int i;
    public int j;
    public int k;
    public int l;
    public boolean m;
    @Nullable
    public String n;
    @Nullable
    public String o;
    @Nullable
    public Integer p;
    @Nullable
    public Integer q;
    @Nullable
    public String v;
    @Nullable
    public String w;
    public int x;
    public int y;
    public int z;
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Long f3576a = 0L;
    @Nullable
    public Long b = 0L;
    @Nullable
    public List<ActivityHeartRateSample> r = new ArrayList();
    @Nullable
    public List<ActivityStepsSample> s = new ArrayList();
    @Nullable
    public List<ActivityGPSSample> t = new ArrayList();
    public int u = 60;

    public final int getActivityDuration() {
        return this.e;
    }

    @Nullable
    public final Integer getActivityId() {
        return this.p;
    }

    @Nullable
    public final String getActivityMode() {
        return this.d;
    }

    @Nullable
    public final String getActivitySite() {
        return this.v;
    }

    @Nullable
    public final String getAppConnectivityCode() {
        return this.w;
    }

    public final int getAverageSWOLF() {
        return this.H;
    }

    public final int getAvgFrequency() {
        return this.M;
    }

    public final int getAvgPace() {
        return this.D;
    }

    public final int getAvgSpeed() {
        return this.z;
    }

    public final int getAvgStepFrequency() {
        return this.x;
    }

    public final int getAvgStrideLength() {
        return this.B;
    }

    @Nullable
    public final Integer getCategoryId() {
        return this.q;
    }

    public final int getConfirmDistance() {
        return this.L;
    }

    public final int getCounter() {
        return this.P;
    }

    @Nullable
    public final String getDate() {
        return this.c;
    }

    @Nullable
    public final Long getEndDateTime() {
        return this.b;
    }

    public final int getFrequency() {
        return this.O;
    }

    @Nullable
    public final List<ActivityGPSSample> getGpsSampleList() {
        return this.t;
    }

    public final int getHeartRate() {
        return this.j;
    }

    @Nullable
    public final List<ActivityHeartRateSample> getHeartRateSampleList() {
        return this.r;
    }

    @Nullable
    public final TimeSpentHeartRateZone getHeartRateTimeZone() {
        return this.F;
    }

    public final int getLowSamplingRate() {
        return this.u;
    }

    @Nullable
    public final String getMacAddress() {
        return this.n;
    }

    public final int getMaxHeartRate() {
        return this.l;
    }

    public final int getMaxPace() {
        return this.E;
    }

    public final int getMaxSpeed() {
        return this.A;
    }

    public final int getMaxStepFrequency() {
        return this.y;
    }

    public final int getMaxStrideLength() {
        return this.C;
    }

    public final int getMinHeartRate() {
        return this.k;
    }

    public final int getMinPace() {
        return this.Q;
    }

    public final int getPaceInSeconds() {
        return this.i;
    }

    public final int getPoolDistance() {
        return this.K;
    }

    @Nullable
    public final String getSessionID() {
        return this.o;
    }

    @Nullable
    public final Long getStartDateTime() {
        return this.f3576a;
    }

    @Nullable
    public final List<ActivityStepsSample> getStepsSampleList() {
        return this.s;
    }

    public final int getSwimmingPosture() {
        return this.J;
    }

    public final double getTotalCalories() {
        return this.h;
    }

    public final double getTotalDistance() {
        return this.g;
    }

    public final int getTotalSteps() {
        return this.f;
    }

    public final int getTotalStrokesNumber() {
        return this.I;
    }

    public final int getTrips() {
        return this.G;
    }

    public final boolean isComplete() {
        return this.m;
    }

    public final int isFromHAR() {
        return this.N;
    }

    public final void setActivityDuration(int i) {
        this.e = i;
    }

    public final void setActivityId(@Nullable Integer num) {
        this.p = num;
    }

    public final void setActivityMode(@Nullable String str) {
        this.d = str;
    }

    public final void setActivitySite(@Nullable String str) {
        this.v = str;
    }

    public final void setAppConnectivityCode(@Nullable String str) {
        this.w = str;
    }

    public final void setAverageSWOLF(int i) {
        this.H = i;
    }

    public final void setAvgFrequency(int i) {
        this.M = i;
    }

    public final void setAvgPace(int i) {
        this.D = i;
    }

    public final void setAvgSpeed(int i) {
        this.z = i;
    }

    public final void setAvgStepFrequency(int i) {
        this.x = i;
    }

    public final void setAvgStrideLength(int i) {
        this.B = i;
    }

    public final void setCategoryId(@Nullable Integer num) {
        this.q = num;
    }

    public final void setComplete(boolean z) {
        this.m = z;
    }

    public final void setConfirmDistance(int i) {
        this.L = i;
    }

    public final void setCounter(int i) {
        this.P = i;
    }

    public final void setDate(@Nullable String str) {
        this.c = str;
    }

    public final void setEndDateTime(@Nullable Long l) {
        this.b = l;
    }

    public final void setFrequency(int i) {
        this.O = i;
    }

    public final void setFromHAR(int i) {
        this.N = i;
    }

    public final void setGpsSampleList(@Nullable List<ActivityGPSSample> list) {
        this.t = list;
    }

    public final void setHeartRate(int i) {
        this.j = i;
    }

    public final void setHeartRateSampleList(@Nullable List<ActivityHeartRateSample> list) {
        this.r = list;
    }

    public final void setHeartRateTimeZone(@Nullable TimeSpentHeartRateZone timeSpentHeartRateZone) {
        this.F = timeSpentHeartRateZone;
    }

    public final void setLowSamplingRate(int i) {
        this.u = i;
    }

    public final void setMacAddress(@Nullable String str) {
        this.n = str;
    }

    public final void setMaxHeartRate(int i) {
        this.l = i;
    }

    public final void setMaxPace(int i) {
        this.E = i;
    }

    public final void setMaxSpeed(int i) {
        this.A = i;
    }

    public final void setMaxStepFrequency(int i) {
        this.y = i;
    }

    public final void setMaxStrideLength(int i) {
        this.C = i;
    }

    public final void setMinHeartRate(int i) {
        this.k = i;
    }

    public final void setMinPace(int i) {
        this.Q = i;
    }

    public final void setPaceInSeconds(int i) {
        this.i = i;
    }

    public final void setPoolDistance(int i) {
        this.K = i;
    }

    public final void setSessionID(@Nullable String str) {
        this.o = str;
    }

    public final void setStartDateTime(@Nullable Long l) {
        this.f3576a = l;
    }

    public final void setStepsSampleList(@Nullable List<ActivityStepsSample> list) {
        this.s = list;
    }

    public final void setSwimmingPosture(int i) {
        this.J = i;
    }

    public final void setTotalCalories(double d) {
        this.h = d;
    }

    public final void setTotalDistance(double d) {
        this.g = d;
    }

    public final void setTotalSteps(int i) {
        this.f = i;
    }

    public final void setTotalStrokesNumber(int i) {
        this.I = i;
    }

    public final void setTrips(int i) {
        this.G = i;
    }

    @NotNull
    public String toString() {
        return "ActivityModeSummaryResponse(startDateTime=" + this.f3576a + ", endDateTime=" + this.b + ", date=" + this.c + ", activityMode=" + this.d + ", activityDuration=" + this.e + ", totalSteps=" + this.f + ", totalDistance=" + this.g + ", totalCalories=" + this.h + ", paceInSeconds=" + this.i + ", heartRate=" + this.j + ", minHeartRate=" + this.k + ", maxHeartRate=" + this.l + ", isComplete=" + this.m + ", macAddress=" + this.n + ", sessionID=" + this.o + ", activityId=" + this.p + ", categoryId=" + this.q + ", lowSamplingRate=" + this.u + ", activitySite=" + this.v + ", appConnectivityCode=" + this.w + ", avgStepFrequency=" + this.x + ", maxStepFrequency=" + this.y + ", avgSpeed=" + this.z + ", maxSpeed=" + this.A + ", avgStrideLength=" + this.B + ", maxStrideLength=" + this.C + ", avgPace=" + this.D + ", maxPace=" + this.E + ", heartRateTimeZone=" + this.F + ", isFromHAR=" + this.N + ",avgFrequency=" + this.M + ", frequency=" + this.O + ", counter=" + this.P + ", minPace=" + this.Q;
    }
}
