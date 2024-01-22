package com.coveiot.khidodb.activities;

import androidx.room.Entity;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"year", "month", WeatherCriteria.UNIT_TYPE_DAY, WeatherCriteria.UNIT_TYPE_HOUR, "minute", "second", DeviceKey.MacAddress}, tableName = "entity_swim_v3")
/* loaded from: classes8.dex */
public final class EntityHealthSwimV3 {

    /* renamed from: a  reason: collision with root package name */
    public int f7083a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    @NotNull
    public String g;
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
    @Nullable
    public List<KHHealthSwimV3Detail> r;
    public int s;
    public int t;
    public long u;
    public boolean v;

    public EntityHealthSwimV3(int i, int i2, int i3, int i4, int i5, int i6, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.f7083a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = i6;
        this.g = macAddress;
    }

    public final void EntityHealthSwimV3() {
    }

    public final int getAverageSWOLF() {
        return this.m;
    }

    public final int getAvg_frequency() {
        return this.t;
    }

    public final int getAvg_speed() {
        return this.s;
    }

    public final int getCalories() {
        return this.j;
    }

    public final int getConfirmDistance() {
        return this.q;
    }

    public final int getDay() {
        return this.f7083a;
    }

    public final int getDistance() {
        return this.k;
    }

    public final int getDurations() {
        return this.i;
    }

    public final int getHour() {
        return this.d;
    }

    @Nullable
    public final List<KHHealthSwimV3Detail> getItems() {
        return this.r;
    }

    @NotNull
    public final String getMacAddress() {
        return this.g;
    }

    public final int getMinute() {
        return this.e;
    }

    public final int getMonth() {
        return this.b;
    }

    public final int getPoolDistance() {
        return this.p;
    }

    public final int getSecond() {
        return this.f;
    }

    public final int getSwimmingPosture() {
        return this.o;
    }

    public final long getTimestamp() {
        return this.u;
    }

    public final int getTotalStrokesNumber() {
        return this.n;
    }

    public final int getTrips() {
        return this.l;
    }

    public final int getType() {
        return this.h;
    }

    public final int getYear() {
        return this.c;
    }

    public final boolean isProcessed() {
        return this.v;
    }

    public final void setAverageSWOLF(int i) {
        this.m = i;
    }

    public final void setAvg_frequency(int i) {
        this.t = i;
    }

    public final void setAvg_speed(int i) {
        this.s = i;
    }

    public final void setCalories(int i) {
        this.j = i;
    }

    public final void setConfirmDistance(int i) {
        this.q = i;
    }

    public final void setDay(int i) {
        this.f7083a = i;
    }

    public final void setDistance(int i) {
        this.k = i;
    }

    public final void setDurations(int i) {
        this.i = i;
    }

    public final void setHour(int i) {
        this.d = i;
    }

    public final void setItems(@Nullable List<KHHealthSwimV3Detail> list) {
        this.r = list;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.g = str;
    }

    public final void setMinute(int i) {
        this.e = i;
    }

    public final void setMonth(int i) {
        this.b = i;
    }

    public final void setPoolDistance(int i) {
        this.p = i;
    }

    public final void setProcessed(boolean z) {
        this.v = z;
    }

    public final void setSecond(int i) {
        this.f = i;
    }

    public final void setSwimmingPosture(int i) {
        this.o = i;
    }

    public final void setTimestamp(long j) {
        this.u = j;
    }

    public final void setTotalStrokesNumber(int i) {
        this.n = i;
    }

    public final void setTrips(int i) {
        this.l = i;
    }

    public final void setType(int i) {
        this.h = i;
    }

    public final void setYear(int i) {
        this.c = i;
    }
}
