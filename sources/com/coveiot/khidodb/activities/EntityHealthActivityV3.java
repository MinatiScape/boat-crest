package com.coveiot.khidodb.activities;

import androidx.room.Entity;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"year", "month", WeatherCriteria.UNIT_TYPE_DAY, WeatherCriteria.UNIT_TYPE_HOUR, "minute", "second", DeviceKey.MacAddress}, tableName = "entity_activity_v3")
/* loaded from: classes8.dex */
public final class EntityHealthActivityV3 {
    @Nullable
    public int[] A;
    @Nullable
    public List<KHHealthActivityV3StepsItem> B;
    public int C;
    @Nullable
    public List<KHHealthActivityV3ItemKMSpeed> D;
    @Nullable
    public List<Integer> E;
    @Nullable
    public List<Integer> F;
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
    public int R;
    public int S;
    public int T;
    public int U;
    public int V;
    public int W;
    public int X;
    public int Y;
    @Nullable
    public List<Integer> Z;

    /* renamed from: a  reason: collision with root package name */
    public int f7082a;
    @Nullable
    public List<Integer> a0;
    public int b;
    @Nullable
    public List<Integer> b0;
    public int c;
    @Nullable
    public List<Integer> c0;
    public int d;
    public int d0;
    public int e;
    public int e0;
    public int f;
    public int f0;
    @NotNull
    public String g;
    public int g0;
    public int h;
    public int h0;
    public int i;
    public int i0;
    public int j;
    public int j0;
    public int k;
    public long k0;
    public int l;
    public boolean l0;
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

    public EntityHealthActivityV3(int i, int i2, int i3, int i4, int i5, int i6, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.f7082a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = i6;
        this.g = macAddress;
    }

    public final void HealthActivityV3() {
    }

    public final int getAerobic_exercise_time() {
        return this.v;
    }

    public final int getAerobic_mins() {
        return this.u;
    }

    public final int getAnaerobicMins() {
        return this.w;
    }

    public final int getAnaerobic_exercise_time() {
        return this.x;
    }

    public final int getAvg_hr_value() {
        return this.n;
    }

    public final int getAvg_pace_speed() {
        return this.P;
    }

    public final int getAvg_speed() {
        return this.H;
    }

    public final int getAvg_step_frequency() {
        return this.J;
    }

    public final int getAvg_step_stride() {
        return this.L;
    }

    public final int getBurn_fat_mins() {
        return this.s;
    }

    public final int getCalories() {
        return this.l;
    }

    public final int getConnect_app() {
        return this.O;
    }

    public final int getDay() {
        return this.f7082a;
    }

    public final int getDistance() {
        return this.m;
    }

    public final int getDurations() {
        return this.k;
    }

    public final int getEnd_day() {
        return this.h0;
    }

    public final int getEnd_hour() {
        return this.i0;
    }

    public final int getEnd_minute() {
        return this.j0;
    }

    public final int getEnd_month() {
        return this.g0;
    }

    public final int getExtreme_exercise_time() {
        return this.z;
    }

    public final int getFast_km_speed() {
        return this.C;
    }

    public final int getFast_pace_speed() {
        return this.Q;
    }

    public final int getFat_burning_time() {
        return this.t;
    }

    @Nullable
    public final List<Integer> getFrequency_items() {
        return this.E;
    }

    public final int getHour() {
        return this.d;
    }

    public final int getHr_data_interval_minute() {
        return this.h;
    }

    @Nullable
    public final int[] getHr_data_vlaue() {
        return this.A;
    }

    @Nullable
    public final List<KHHealthActivityV3StepsItem> getItems() {
        return this.B;
    }

    @Nullable
    public final List<KHHealthActivityV3ItemKMSpeed> getItems_km_speed() {
        return this.D;
    }

    @Nullable
    public final List<Integer> getItems_mi_speed() {
        return this.F;
    }

    public final int getKm_speed() {
        return this.G;
    }

    public final int getLimit_mins() {
        return this.y;
    }

    @NotNull
    public final String getMacAddress() {
        return this.g;
    }

    public final int getMax_hr_value() {
        return this.o;
    }

    public final int getMax_speed() {
        return this.I;
    }

    public final int getMax_step_frequency() {
        return this.K;
    }

    public final int getMax_step_stride() {
        return this.M;
    }

    public final int getMin_hr_value() {
        return this.p;
    }

    public final int getMinute() {
        return this.e;
    }

    public final int getMonth() {
        return this.b;
    }

    @Nullable
    public final List<Integer> getPace_speed_items() {
        return this.Z;
    }

    public final int getPaddle_frequency_count() {
        return this.e0;
    }

    @Nullable
    public final List<Integer> getPaddle_frequency_items() {
        return this.b0;
    }

    public final int getPaddle_number_count() {
        return this.d0;
    }

    @Nullable
    public final List<Integer> getPaddle_number_items() {
        return this.a0;
    }

    public final int getRecovery_time_day() {
        return this.V;
    }

    public final int getRecovery_time_hour() {
        return this.W;
    }

    public final int getRecovery_time_min() {
        return this.X;
    }

    public final int getRecovery_time_mon() {
        return this.U;
    }

    public final int getRecovery_time_s() {
        return this.Y;
    }

    public final int getRecovery_time_year() {
        return this.T;
    }

    public final int getSecond() {
        return this.f;
    }

    public final int getSport_start_type() {
        return this.N;
    }

    public final int getStep() {
        return this.j;
    }

    public final long getTimestamp() {
        return this.k0;
    }

    public final int getTraining_effect() {
        return this.R;
    }

    public final int getTread_frequency_count() {
        return this.f0;
    }

    @Nullable
    public final List<Integer> getTread_frequency_items() {
        return this.c0;
    }

    public final int getType() {
        return this.i;
    }

    public final int getVO2max() {
        return this.S;
    }

    public final int getWarmUpMins() {
        return this.q;
    }

    public final int getWarm_up_time() {
        return this.r;
    }

    public final int getYear() {
        return this.c;
    }

    public final boolean isProcessed() {
        return this.l0;
    }

    public final void setAerobic_exercise_time(int i) {
        this.v = i;
    }

    public final void setAerobic_mins(int i) {
        this.u = i;
    }

    public final void setAnaerobicMins(int i) {
        this.w = i;
    }

    public final void setAnaerobic_exercise_time(int i) {
        this.x = i;
    }

    public final void setAvg_hr_value(int i) {
        this.n = i;
    }

    public final void setAvg_pace_speed(int i) {
        this.P = i;
    }

    public final void setAvg_speed(int i) {
        this.H = i;
    }

    public final void setAvg_step_frequency(int i) {
        this.J = i;
    }

    public final void setAvg_step_stride(int i) {
        this.L = i;
    }

    public final void setBurn_fat_mins(int i) {
        this.s = i;
    }

    public final void setCalories(int i) {
        this.l = i;
    }

    public final void setConnect_app(int i) {
        this.O = i;
    }

    public final void setDay(int i) {
        this.f7082a = i;
    }

    public final void setDistance(int i) {
        this.m = i;
    }

    public final void setDurations(int i) {
        this.k = i;
    }

    public final void setEnd_day(int i) {
        this.h0 = i;
    }

    public final void setEnd_hour(int i) {
        this.i0 = i;
    }

    public final void setEnd_minute(int i) {
        this.j0 = i;
    }

    public final void setEnd_month(int i) {
        this.g0 = i;
    }

    public final void setExtreme_exercise_time(int i) {
        this.z = i;
    }

    public final void setFast_km_speed(int i) {
        this.C = i;
    }

    public final void setFast_pace_speed(int i) {
        this.Q = i;
    }

    public final void setFat_burning_time(int i) {
        this.t = i;
    }

    public final void setFrequency_items(@Nullable List<Integer> list) {
        this.E = list;
    }

    public final void setHour(int i) {
        this.d = i;
    }

    public final void setHr_data_interval_minute(int i) {
        this.h = i;
    }

    public final void setHr_data_vlaue(@Nullable int[] iArr) {
        this.A = iArr;
    }

    public final void setItems(@Nullable List<KHHealthActivityV3StepsItem> list) {
        this.B = list;
    }

    public final void setItems_km_speed(@Nullable List<KHHealthActivityV3ItemKMSpeed> list) {
        this.D = list;
    }

    public final void setItems_mi_speed(@Nullable List<Integer> list) {
        this.F = list;
    }

    public final void setKm_speed(int i) {
        this.G = i;
    }

    public final void setLimit_mins(int i) {
        this.y = i;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.g = str;
    }

    public final void setMax_hr_value(int i) {
        this.o = i;
    }

    public final void setMax_speed(int i) {
        this.I = i;
    }

    public final void setMax_step_frequency(int i) {
        this.K = i;
    }

    public final void setMax_step_stride(int i) {
        this.M = i;
    }

    public final void setMin_hr_value(int i) {
        this.p = i;
    }

    public final void setMinute(int i) {
        this.e = i;
    }

    public final void setMonth(int i) {
        this.b = i;
    }

    public final void setPace_speed_items(@Nullable List<Integer> list) {
        this.Z = list;
    }

    public final void setPaddle_frequency_count(int i) {
        this.e0 = i;
    }

    public final void setPaddle_frequency_items(@Nullable List<Integer> list) {
        this.b0 = list;
    }

    public final void setPaddle_number_count(int i) {
        this.d0 = i;
    }

    public final void setPaddle_number_items(@Nullable List<Integer> list) {
        this.a0 = list;
    }

    public final void setProcessed(boolean z) {
        this.l0 = z;
    }

    public final void setRecovery_time_day(int i) {
        this.V = i;
    }

    public final void setRecovery_time_hour(int i) {
        this.W = i;
    }

    public final void setRecovery_time_min(int i) {
        this.X = i;
    }

    public final void setRecovery_time_mon(int i) {
        this.U = i;
    }

    public final void setRecovery_time_s(int i) {
        this.Y = i;
    }

    public final void setRecovery_time_year(int i) {
        this.T = i;
    }

    public final void setSecond(int i) {
        this.f = i;
    }

    public final void setSport_start_type(int i) {
        this.N = i;
    }

    public final void setStep(int i) {
        this.j = i;
    }

    public final void setTimestamp(long j) {
        this.k0 = j;
    }

    public final void setTraining_effect(int i) {
        this.R = i;
    }

    public final void setTread_frequency_count(int i) {
        this.f0 = i;
    }

    public final void setTread_frequency_items(@Nullable List<Integer> list) {
        this.c0 = list;
    }

    public final void setType(int i) {
        this.i = i;
    }

    public final void setVO2max(int i) {
        this.S = i;
    }

    public final void setWarmUpMins(int i) {
        this.q = i;
    }

    public final void setWarm_up_time(int i) {
        this.r = i;
    }

    public final void setYear(int i) {
        this.c = i;
    }

    @NotNull
    public String toString() {
        return "HealthActivityV3{year=" + this.c + ", month=" + this.b + ", day=" + this.f7082a + ", hour=" + this.d + ", minute=" + this.e + ", second=" + this.f + ", hr_data_interval_minute=" + this.h + ", type=" + this.i + ", step=" + this.j + ", durations=" + this.k + ", calories=" + this.l + ", distance=" + this.m + ", avg_hr_value=" + this.n + ", max_hr_value=" + this.o + ", warmUpMins=" + this.q + ", warm_up_time=" + this.r + ", burn_fat_mins=" + this.s + ", fat_burning_time=" + this.t + ", aerobic_mins=" + this.u + ", aerobic_exercise_time=" + this.v + ", anaerobicMins=" + this.w + ", anaerobic_exercise_time=" + this.x + ", limit_mins=" + this.y + ", extreme_exercise_time=" + this.z + ", hr_data_vlaue=" + Arrays.toString(this.A) + ", items=" + this.B + ", fast_km_speed=" + this.C + ", items_km_speed=" + this.D + ", frequency_items=" + this.E + ", items_mi_speed=" + this.F + ", km_speed=" + this.G + ", avg_speed=" + this.H + ", max_speed=" + this.I + ", avg_step_frequency=" + this.J + ", max_step_frequency=" + this.K + ", avg_step_stride=" + this.L + ", max_step_stride=" + this.M + ", sport_start_type=" + this.N + ", connect_app=" + this.O + ", avg_pace_speed=" + this.P + ", fast_pace_speed=" + this.Q + ", training_effect=" + this.R + ", vO2max=" + this.S + ", recovery_time_year=" + this.T + ", recovery_time_mon=" + this.U + ", recovery_time_day=" + this.V + ", recovery_time_hour=" + this.W + ", recovery_time_min=" + this.X + ", recovery_time_s=" + this.Y + ", min_hr_value=" + this.p + ", pace_speed_items=" + this.Z + ", paddle_number_items=" + this.a0 + ", paddle_frequency_items=" + this.b0 + ", tread_frequency_items=" + this.c0 + ", paddle_number_count=" + this.d0 + ", paddle_frequency_count=" + this.e0 + ", tread_frequency_count=" + this.f0 + ", end_month=" + this.g0 + ", end_day=" + this.h0 + ", end_hour=" + this.i0 + ", end_minute=" + this.j0 + '}';
    }
}
