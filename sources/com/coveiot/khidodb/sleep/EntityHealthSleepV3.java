package com.coveiot.khidodb.sleep;

import androidx.room.Entity;
import com.coveiot.khidodb.sleep.model.KHHealthSleepV3Item;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"get_up_year", "get_up_month", "get_up_day", "get_up_hour", "get_up_minte", DeviceKey.MacAddress}, tableName = "health_sleep_v3")
/* loaded from: classes8.dex */
public final class EntityHealthSleepV3 {
    public boolean A;

    /* renamed from: a  reason: collision with root package name */
    public int f7099a;
    public int b;
    public int c;
    public int d;
    public int e;
    @NotNull
    public String f;
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
    @Nullable
    public List<KHHealthSleepV3Item> y;
    public long z;

    public EntityHealthSleepV3(int i, int i2, int i3, int i4, int i5, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.f7099a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = macAddress;
    }

    public final int getAwrr_status() {
        return this.v;
    }

    public final int getBreath_quality() {
        return this.x;
    }

    public final int getData_type() {
        return this.g;
    }

    public final int getDeep_count() {
        return this.u;
    }

    public final int getDeep_mins() {
        return this.q;
    }

    public final int getFall_asleep_day() {
        return this.j;
    }

    public final int getFall_asleep_hour() {
        return this.k;
    }

    public final int getFall_asleep_minte() {
        return this.l;
    }

    public final int getFall_asleep_month() {
        return this.i;
    }

    public final int getFall_asleep_year() {
        return this.h;
    }

    public final int getGet_up_day() {
        return this.f7099a;
    }

    public final int getGet_up_hour() {
        return this.d;
    }

    public final int getGet_up_minte() {
        return this.e;
    }

    public final int getGet_up_month() {
        return this.b;
    }

    public final int getGet_up_year() {
        return this.c;
    }

    @Nullable
    public final List<KHHealthSleepV3Item> getItems() {
        return this.y;
    }

    public final int getLight_count() {
        return this.s;
    }

    public final int getLight_mins() {
        return this.o;
    }

    @NotNull
    public final String getMacAddress() {
        return this.f;
    }

    public final int getRem_count() {
        return this.t;
    }

    public final int getRem_mins() {
        return this.p;
    }

    public final int getSleep_score() {
        return this.w;
    }

    public final long getTimestamp() {
        return this.z;
    }

    public final int getTotal_sleep_time_mins() {
        return this.m;
    }

    public final int getWake_count() {
        return this.r;
    }

    public final int getWake_mins() {
        return this.n;
    }

    public final boolean isProcessed() {
        return this.A;
    }

    public final void setAwrr_status(int i) {
        this.v = i;
    }

    public final void setBreath_quality(int i) {
        this.x = i;
    }

    public final void setData_type(int i) {
        this.g = i;
    }

    public final void setDeep_count(int i) {
        this.u = i;
    }

    public final void setDeep_mins(int i) {
        this.q = i;
    }

    public final void setFall_asleep_day(int i) {
        this.j = i;
    }

    public final void setFall_asleep_hour(int i) {
        this.k = i;
    }

    public final void setFall_asleep_minte(int i) {
        this.l = i;
    }

    public final void setFall_asleep_month(int i) {
        this.i = i;
    }

    public final void setFall_asleep_year(int i) {
        this.h = i;
    }

    public final void setGet_up_day(int i) {
        this.f7099a = i;
    }

    public final void setGet_up_hour(int i) {
        this.d = i;
    }

    public final void setGet_up_minte(int i) {
        this.e = i;
    }

    public final void setGet_up_month(int i) {
        this.b = i;
    }

    public final void setGet_up_year(int i) {
        this.c = i;
    }

    public final void setItems(@Nullable List<KHHealthSleepV3Item> list) {
        this.y = list;
    }

    public final void setLight_count(int i) {
        this.s = i;
    }

    public final void setLight_mins(int i) {
        this.o = i;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f = str;
    }

    public final void setProcessed(boolean z) {
        this.A = z;
    }

    public final void setRem_count(int i) {
        this.t = i;
    }

    public final void setRem_mins(int i) {
        this.p = i;
    }

    public final void setSleep_score(int i) {
        this.w = i;
    }

    public final void setTimestamp(long j) {
        this.z = j;
    }

    public final void setTotal_sleep_time_mins(int i) {
        this.m = i;
    }

    public final void setWake_count(int i) {
        this.r = i;
    }

    public final void setWake_mins(int i) {
        this.n = i;
    }
}
