package com.coveiot.android.activitymodes.database.entities;

import androidx.annotation.NonNull;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.coveiot.android.activitymodes.database.models.HeartRateZoneRanges;
import com.coveiot.android.activitymodes.database.models.TimeSpentHeartRateZone;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.ido.ble.event.stat.one.d;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(indices = {@Index({"client_ref_id"})}, tableName = "workout_session")
/* loaded from: classes2.dex */
public final class EntityWorkoutSession {
    @ColumnInfo(name = "hrZoneRanges")
    @Nullable
    public HeartRateZoneRanges A;
    @ColumnInfo(name = "timespent_per_heartratezone")
    @Nullable
    public TimeSpentHeartRateZone B;
    @androidx.annotation.Nullable
    @ColumnInfo(name = "appConnectivity")
    @Nullable
    public String C;
    @androidx.annotation.Nullable
    @ColumnInfo(name = "avgStepFrequency")
    @Nullable
    public Integer D;
    @androidx.annotation.Nullable
    @ColumnInfo(name = "maxStepFrequency")
    @Nullable
    public Integer E;
    @androidx.annotation.Nullable
    @ColumnInfo(name = "avgSpeed")
    @Nullable
    public Float F;
    @androidx.annotation.Nullable
    @ColumnInfo(name = "maxSpeed")
    @Nullable
    public Float G;
    @androidx.annotation.Nullable
    @ColumnInfo(name = "avgStrideLength")
    @Nullable
    public Integer H;
    @androidx.annotation.Nullable
    @ColumnInfo(name = "maxStrideLength")
    @Nullable
    public Integer I;
    @androidx.annotation.Nullable
    @ColumnInfo(name = "avgPace")
    @Nullable
    public Float J;
    @androidx.annotation.Nullable
    @ColumnInfo(name = "maxPace")
    @Nullable
    public Float K;
    @androidx.annotation.Nullable
    @ColumnInfo(name = "totalStrokes")
    @Nullable
    public Integer L;
    @ColumnInfo(name = "swimmingStyle")
    @Nullable
    public String M;
    @androidx.annotation.Nullable
    @ColumnInfo(name = "poolLength")
    @Nullable
    public Integer N;
    @androidx.annotation.Nullable
    @ColumnInfo(name = "totalLaps")
    @Nullable
    public Integer O;
    @androidx.annotation.Nullable
    @ColumnInfo(name = "avgSwolf")
    @Nullable
    public Integer P;
    @androidx.annotation.Nullable
    @ColumnInfo(name = "avgStrokeFreq")
    @Nullable
    public Integer Q;
    @androidx.annotation.Nullable
    @ColumnInfo(name = "isFromHAR")
    @Nullable
    public Integer R;
    @androidx.annotation.Nullable
    @ColumnInfo(name = "minPace")
    @Nullable
    public Float S;
    @androidx.annotation.Nullable
    @ColumnInfo(name = "categoryId")
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Integer f2810a;
    @androidx.annotation.Nullable
    @ColumnInfo(name = "activityId")
    @Nullable
    public Integer b;
    @ColumnInfo(name = "serial_no")
    @Nullable
    public String c;
    @ColumnInfo(name = "client_ref_id")
    @Nullable
    public String d;
    @ColumnInfo(name = "date")
    @Nullable
    public String e;
    @ColumnInfo(name = "start_time")
    public long f;
    @ColumnInfo(name = "end_time")
    public long g;
    @ColumnInfo(name = "activity_type")
    @Nullable
    public String h;
    @ColumnInfo(name = TypedValues.AttributesType.S_TARGET)
    @Nullable
    public String i;
    @ColumnInfo(name = "target_baseunit")
    @Nullable
    public String j;
    @ColumnInfo(name = WorkoutConstants.INDOOR_OUTDOOR)
    @Nullable
    public String k;
    @ColumnInfo(name = "session_duration")
    public int l;
    @ColumnInfo(name = "steps_sampling_rate")
    public int m;
    @ColumnInfo(name = "hr_sampling_rate")
    public int n;
    @ColumnInfo(name = "total_steps")
    public int o;
    @ColumnInfo(name = "total_calories")
    public float p;
    @ColumnInfo(name = "total_distance")
    public int q;
    @ColumnInfo(name = "max_hr")
    public int r;
    @ColumnInfo(name = "min_hr")
    public int s;
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = WorkoutConstants.SESSION_ID)
    public String session_id;
    @ColumnInfo(name = "avg_hr")
    public int t;
    @ColumnInfo(name = "pace")
    public float u;
    @ColumnInfo(name = "fatigue_level")
    public int v;
    @ColumnInfo(name = "sent_to_server")
    public boolean w;
    @ColumnInfo(name = "session_place")
    @Nullable
    public String x;
    @ColumnInfo(name = "mood_after_session")
    @Nullable
    public String y;
    @ColumnInfo(name = d.O)
    @Nullable
    public String z;

    @Nullable
    public final Integer getActivityId() {
        return this.b;
    }

    @Nullable
    public final String getActivity_type() {
        return this.h;
    }

    @Nullable
    public final String getAppConnectivityCode() {
        return this.C;
    }

    @Nullable
    public final Float getAvgPace() {
        return this.J;
    }

    @Nullable
    public final Float getAvgSpeed() {
        return this.F;
    }

    @Nullable
    public final Integer getAvgStepFrequency() {
        return this.D;
    }

    @Nullable
    public final Integer getAvgStrideLength() {
        return this.H;
    }

    @Nullable
    public final Integer getAvgStrokeFreq() {
        return this.Q;
    }

    @Nullable
    public final Integer getAvgSwolf() {
        return this.P;
    }

    public final int getAvg_hr() {
        return this.t;
    }

    @Nullable
    public final Integer getCategoryId() {
        return this.f2810a;
    }

    @Nullable
    public final String getClient_ref_id() {
        return this.d;
    }

    public final long getEnd_time() {
        return this.g;
    }

    public final int getFatigue_level() {
        return this.v;
    }

    @Nullable
    public final String getFeedback() {
        return this.z;
    }

    @Nullable
    public final Integer getFromHAR() {
        return this.R;
    }

    @Nullable
    public final HeartRateZoneRanges getHrZoneRanges() {
        return this.A;
    }

    public final int getHr_sampling_rate() {
        return this.n;
    }

    @Nullable
    public final String getIndoor_outdoor() {
        return this.k;
    }

    public final boolean getIssenttoserver() {
        return this.w;
    }

    @Nullable
    public final Float getMaxPace() {
        return this.K;
    }

    @Nullable
    public final Float getMaxSpeed() {
        return this.G;
    }

    @Nullable
    public final Integer getMaxStepFrequency() {
        return this.E;
    }

    @Nullable
    public final Integer getMaxStrideLength() {
        return this.I;
    }

    public final int getMax_hr() {
        return this.r;
    }

    @Nullable
    public final Float getMinPace() {
        return this.S;
    }

    public final int getMin_hr() {
        return this.s;
    }

    @Nullable
    public final String getMoodaftersession() {
        return this.y;
    }

    public final float getPace() {
        return this.u;
    }

    @Nullable
    public final Integer getPoolLength() {
        return this.N;
    }

    @Nullable
    public final String getSerialNo() {
        return this.c;
    }

    @Nullable
    public final String getSession_date() {
        return this.e;
    }

    public final int getSession_duration() {
        return this.l;
    }

    @NotNull
    public final String getSession_id() {
        String str = this.session_id;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException(WorkoutConstants.SESSION_ID);
        return null;
    }

    @Nullable
    public final String getSession_place() {
        return this.x;
    }

    public final long getStart_time() {
        return this.f;
    }

    public final int getSteps_sampling_rate() {
        return this.m;
    }

    @Nullable
    public final String getSwimmingStyle() {
        return this.M;
    }

    @Nullable
    public final String getTarget() {
        return this.i;
    }

    @Nullable
    public final String getTarget_baseunit() {
        return this.j;
    }

    @Nullable
    public final TimeSpentHeartRateZone getTimespent_per_heartratezone() {
        return this.B;
    }

    @Nullable
    public final Integer getTotalLaps() {
        return this.O;
    }

    @Nullable
    public final Integer getTotalStrokes() {
        return this.L;
    }

    public final float getTotal_calories() {
        return this.p;
    }

    public final int getTotal_distance() {
        return this.q;
    }

    public final int getTotal_steps() {
        return this.o;
    }

    public final void setActivityId(@Nullable Integer num) {
        this.b = num;
    }

    public final void setActivity_type(@Nullable String str) {
        this.h = str;
    }

    public final void setAppConnectivityCode(@Nullable String str) {
        this.C = str;
    }

    public final void setAvgPace(@Nullable Float f) {
        this.J = f;
    }

    public final void setAvgSpeed(@Nullable Float f) {
        this.F = f;
    }

    public final void setAvgStepFrequency(@Nullable Integer num) {
        this.D = num;
    }

    public final void setAvgStrideLength(@Nullable Integer num) {
        this.H = num;
    }

    public final void setAvgStrokeFreq(@Nullable Integer num) {
        this.Q = num;
    }

    public final void setAvgSwolf(@Nullable Integer num) {
        this.P = num;
    }

    public final void setAvg_hr(int i) {
        this.t = i;
    }

    public final void setCategoryId(@Nullable Integer num) {
        this.f2810a = num;
    }

    public final void setClient_ref_id(@Nullable String str) {
        this.d = str;
    }

    public final void setEnd_time(long j) {
        this.g = j;
    }

    public final void setFatigue_level(int i) {
        this.v = i;
    }

    public final void setFeedback(@Nullable String str) {
        this.z = str;
    }

    public final void setFromHAR(@Nullable Integer num) {
        this.R = num;
    }

    public final void setHrZoneRanges(@Nullable HeartRateZoneRanges heartRateZoneRanges) {
        this.A = heartRateZoneRanges;
    }

    public final void setHr_sampling_rate(int i) {
        this.n = i;
    }

    public final void setIndoor_outdoor(@Nullable String str) {
        this.k = str;
    }

    public final void setIssenttoserver(boolean z) {
        this.w = z;
    }

    public final void setMaxPace(@Nullable Float f) {
        this.K = f;
    }

    public final void setMaxSpeed(@Nullable Float f) {
        this.G = f;
    }

    public final void setMaxStepFrequency(@Nullable Integer num) {
        this.E = num;
    }

    public final void setMaxStrideLength(@Nullable Integer num) {
        this.I = num;
    }

    public final void setMax_hr(int i) {
        this.r = i;
    }

    public final void setMinPace(@Nullable Float f) {
        this.S = f;
    }

    public final void setMin_hr(int i) {
        this.s = i;
    }

    public final void setMoodaftersession(@Nullable String str) {
        this.y = str;
    }

    public final void setPace(float f) {
        this.u = f;
    }

    public final void setPoolLength(@Nullable Integer num) {
        this.N = num;
    }

    public final void setSerialNo(@Nullable String str) {
        this.c = str;
    }

    public final void setSession_date(@Nullable String str) {
        this.e = str;
    }

    public final void setSession_duration(int i) {
        this.l = i;
    }

    public final void setSession_id(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.session_id = str;
    }

    public final void setSession_place(@Nullable String str) {
        this.x = str;
    }

    public final void setStart_time(long j) {
        this.f = j;
    }

    public final void setSteps_sampling_rate(int i) {
        this.m = i;
    }

    public final void setSwimmingStyle(@Nullable String str) {
        this.M = str;
    }

    public final void setTarget(@Nullable String str) {
        this.i = str;
    }

    public final void setTarget_baseunit(@Nullable String str) {
        this.j = str;
    }

    public final void setTimespent_per_heartratezone(@Nullable TimeSpentHeartRateZone timeSpentHeartRateZone) {
        this.B = timeSpentHeartRateZone;
    }

    public final void setTotalLaps(@Nullable Integer num) {
        this.O = num;
    }

    public final void setTotalStrokes(@Nullable Integer num) {
        this.L = num;
    }

    public final void setTotal_calories(float f) {
        this.p = f;
    }

    public final void setTotal_distance(int i) {
        this.q = i;
    }

    public final void setTotal_steps(int i) {
        this.o = i;
    }
}
