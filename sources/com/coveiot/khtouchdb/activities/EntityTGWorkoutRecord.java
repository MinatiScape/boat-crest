package com.coveiot.khtouchdb.activities;

import androidx.room.Entity;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutEventBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutFootBallAvgPaceBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutGpsBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutHeartRateBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutKeepTrackBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutPaceBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutRealTimeDataBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutRowingBean;
import com.coveiot.khtouchdb.activities.model.KHTGWorkoutSwimBean;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"date", DeviceKey.MacAddress}, tableName = "entity_tg_workout_record")
/* loaded from: classes8.dex */
public final class EntityTGWorkoutRecord {
    public int A;
    public int B;
    public int C;
    public int D;
    @Nullable
    public List<KHTGWorkoutEventBean> E;
    @Nullable
    public List<KHTGWorkoutHeartRateBean> F;
    @Nullable
    public List<KHTGWorkoutPaceBean> G;
    @Nullable
    public List<KHTGWorkoutRowingBean> H;
    @Nullable
    public List<KHTGWorkoutGpsBean> I;
    @Nullable
    public KHTGWorkoutSwimBean J;
    @Nullable
    public List<KHTGWorkoutGpsBean> K;
    @Nullable
    public KHTGWorkoutFootBallAvgPaceBean L;
    @Nullable
    public KHTGWorkoutRealTimeDataBean M;
    @Nullable
    public KHTGWorkoutKeepTrackBean N;
    public long O;
    public boolean P;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f7169a;
    @NotNull
    public String b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    public int y;
    public int z;

    public EntityTGWorkoutRecord(@NotNull String date, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.f7169a = date;
        this.b = macAddress;
    }

    public final int getAerobicExercise() {
        return this.n;
    }

    public final int getAnaerobicExercise() {
        return this.o;
    }

    public final int getAvgHr() {
        return this.h;
    }

    public final int getAvgPaceSecs() {
        return this.v;
    }

    public final int getAvgSkipFrq() {
        return this.B;
    }

    public final int getAvgSpeed() {
        return this.s;
    }

    public final int getAvgStrideFrequency() {
        return this.q;
    }

    public final int getAvgStrideLength() {
        return this.r;
    }

    public final int getBoxingNum() {
        return this.A;
    }

    public final int getCalories() {
        return this.f;
    }

    @NotNull
    public final String getDate() {
        return this.f7169a;
    }

    public final int getDistance() {
        return this.g;
    }

    public final int getDumbbellNum() {
        return this.D;
    }

    public final int getDuration() {
        return this.c;
    }

    @Nullable
    public final List<KHTGWorkoutEventBean> getEventItems() {
        return this.E;
    }

    public final int getExtremeExercise() {
        return this.p;
    }

    public final int getFatBurning() {
        return this.m;
    }

    @Nullable
    public final KHTGWorkoutFootBallAvgPaceBean getFootballAvgPace() {
        return this.L;
    }

    @Nullable
    public final List<KHTGWorkoutGpsBean> getFootballFieldGpsData() {
        return this.K;
    }

    @Nullable
    public final List<KHTGWorkoutGpsBean> getGpsData() {
        return this.I;
    }

    @Nullable
    public final List<KHTGWorkoutHeartRateBean> getHeartRateItems() {
        return this.F;
    }

    @Nullable
    public final KHTGWorkoutKeepTrackBean getKeepTrack() {
        return this.N;
    }

    @NotNull
    public final String getMacAddress() {
        return this.b;
    }

    public final int getMaxHr() {
        return this.i;
    }

    public final int getMaxPace() {
        return this.w;
    }

    public final int getMaxSpeed() {
        return this.t;
    }

    public final int getMinHr() {
        return this.j;
    }

    public final int getMinPace() {
        return this.x;
    }

    public final int getMinSpeed() {
        return this.u;
    }

    @Nullable
    public final List<KHTGWorkoutPaceBean> getPaceItems() {
        return this.G;
    }

    public final int getPaddleFrq() {
        return this.z;
    }

    public final int getPaddleNum() {
        return this.y;
    }

    @Nullable
    public final KHTGWorkoutRealTimeDataBean getRealTimeData() {
        return this.M;
    }

    public final int getRelax() {
        return this.k;
    }

    @Nullable
    public final List<KHTGWorkoutRowingBean> getRowingItems() {
        return this.H;
    }

    public final int getSkipNum() {
        return this.C;
    }

    public final int getStep() {
        return this.e;
    }

    @Nullable
    public final KHTGWorkoutSwimBean getSwimData() {
        return this.J;
    }

    public final long getTimeStamp() {
        return this.O;
    }

    public final int getType() {
        return this.d;
    }

    public final int getWarmUp() {
        return this.l;
    }

    public final boolean isProcessed() {
        return this.P;
    }

    public final void setAerobicExercise(int i) {
        this.n = i;
    }

    public final void setAnaerobicExercise(int i) {
        this.o = i;
    }

    public final void setAvgHr(int i) {
        this.h = i;
    }

    public final void setAvgPaceSecs(int i) {
        this.v = i;
    }

    public final void setAvgSkipFrq(int i) {
        this.B = i;
    }

    public final void setAvgSpeed(int i) {
        this.s = i;
    }

    public final void setAvgStrideFrequency(int i) {
        this.q = i;
    }

    public final void setAvgStrideLength(int i) {
        this.r = i;
    }

    public final void setBoxingNum(int i) {
        this.A = i;
    }

    public final void setCalories(int i) {
        this.f = i;
    }

    public final void setDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f7169a = str;
    }

    public final void setDistance(int i) {
        this.g = i;
    }

    public final void setDumbbellNum(int i) {
        this.D = i;
    }

    public final void setDuration(int i) {
        this.c = i;
    }

    public final void setEventItems(@Nullable List<KHTGWorkoutEventBean> list) {
        this.E = list;
    }

    public final void setExtremeExercise(int i) {
        this.p = i;
    }

    public final void setFatBurning(int i) {
        this.m = i;
    }

    public final void setFootballAvgPace(@Nullable KHTGWorkoutFootBallAvgPaceBean kHTGWorkoutFootBallAvgPaceBean) {
        this.L = kHTGWorkoutFootBallAvgPaceBean;
    }

    public final void setFootballFieldGpsData(@Nullable List<KHTGWorkoutGpsBean> list) {
        this.K = list;
    }

    public final void setGpsData(@Nullable List<KHTGWorkoutGpsBean> list) {
        this.I = list;
    }

    public final void setHeartRateItems(@Nullable List<KHTGWorkoutHeartRateBean> list) {
        this.F = list;
    }

    public final void setKeepTrack(@Nullable KHTGWorkoutKeepTrackBean kHTGWorkoutKeepTrackBean) {
        this.N = kHTGWorkoutKeepTrackBean;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.b = str;
    }

    public final void setMaxHr(int i) {
        this.i = i;
    }

    public final void setMaxPace(int i) {
        this.w = i;
    }

    public final void setMaxSpeed(int i) {
        this.t = i;
    }

    public final void setMinHr(int i) {
        this.j = i;
    }

    public final void setMinPace(int i) {
        this.x = i;
    }

    public final void setMinSpeed(int i) {
        this.u = i;
    }

    public final void setPaceItems(@Nullable List<KHTGWorkoutPaceBean> list) {
        this.G = list;
    }

    public final void setPaddleFrq(int i) {
        this.z = i;
    }

    public final void setPaddleNum(int i) {
        this.y = i;
    }

    public final void setProcessed(boolean z) {
        this.P = z;
    }

    public final void setRealTimeData(@Nullable KHTGWorkoutRealTimeDataBean kHTGWorkoutRealTimeDataBean) {
        this.M = kHTGWorkoutRealTimeDataBean;
    }

    public final void setRelax(int i) {
        this.k = i;
    }

    public final void setRowingItems(@Nullable List<KHTGWorkoutRowingBean> list) {
        this.H = list;
    }

    public final void setSkipNum(int i) {
        this.C = i;
    }

    public final void setStep(int i) {
        this.e = i;
    }

    public final void setSwimData(@Nullable KHTGWorkoutSwimBean kHTGWorkoutSwimBean) {
        this.J = kHTGWorkoutSwimBean;
    }

    public final void setTimeStamp(long j) {
        this.O = j;
    }

    public final void setType(int i) {
        this.d = i;
    }

    public final void setWarmUp(int i) {
        this.l = i;
    }
}
