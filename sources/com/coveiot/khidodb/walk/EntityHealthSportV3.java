package com.coveiot.khidodb.walk;

import androidx.room.Entity;
import com.coveiot.khidodb.walk.model.KHHealthSportV3Item;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"year", "month", WeatherCriteria.UNIT_TYPE_DAY, "start_time", DeviceKey.MacAddress}, tableName = "health_sport_v3")
/* loaded from: classes8.dex */
public final class EntityHealthSportV3 {

    /* renamed from: a  reason: collision with root package name */
    public int f7111a;
    public int b;
    public int c;
    public int d;
    @NotNull
    public String e;
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
    @Nullable
    public List<Integer> p;
    @Nullable
    public List<Integer> q;
    @Nullable
    public List<KHHealthSportV3Item> r;
    public int s;
    public long t;
    public boolean u;

    public EntityHealthSportV3(int i, int i2, int i3, int i4, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.f7111a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = macAddress;
    }

    public final int getDay() {
        return this.f7111a;
    }

    public final int getHour() {
        return this.f;
    }

    public final int getItem_count() {
        return this.o;
    }

    @Nullable
    public final List<KHHealthSportV3Item> getItems() {
        return this.r;
    }

    @NotNull
    public final String getMacAddress() {
        return this.e;
    }

    public final int getMinute() {
        return this.g;
    }

    public final int getMonth() {
        return this.b;
    }

    public final int getPer_minute() {
        return this.i;
    }

    public final int getSecond() {
        return this.h;
    }

    public final int getStart_time() {
        return this.d;
    }

    public final long getTimestamp() {
        return this.t;
    }

    public final int getTotal_active_time() {
        return this.m;
    }

    public final int getTotal_activity_calories() {
        return this.n;
    }

    public final int getTotal_distances() {
        return this.l;
    }

    public final int getTotal_rest_activity_calories() {
        return this.k;
    }

    public final int getTotal_step() {
        return this.j;
    }

    @Nullable
    public final List<Integer> getType() {
        return this.q;
    }

    public final int getWalk_goal_time() {
        return this.s;
    }

    @Nullable
    public final List<Integer> getWear_flag_array() {
        return this.p;
    }

    public final int getYear() {
        return this.c;
    }

    public final boolean isProcessed() {
        return this.u;
    }

    public final void setDay(int i) {
        this.f7111a = i;
    }

    public final void setHour(int i) {
        this.f = i;
    }

    public final void setItem_count(int i) {
        this.o = i;
    }

    public final void setItems(@Nullable List<KHHealthSportV3Item> list) {
        this.r = list;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.e = str;
    }

    public final void setMinute(int i) {
        this.g = i;
    }

    public final void setMonth(int i) {
        this.b = i;
    }

    public final void setPer_minute(int i) {
        this.i = i;
    }

    public final void setProcessed(boolean z) {
        this.u = z;
    }

    public final void setSecond(int i) {
        this.h = i;
    }

    public final void setStart_time(int i) {
        this.d = i;
    }

    public final void setTimestamp(long j) {
        this.t = j;
    }

    public final void setTotal_active_time(int i) {
        this.m = i;
    }

    public final void setTotal_activity_calories(int i) {
        this.n = i;
    }

    public final void setTotal_distances(int i) {
        this.l = i;
    }

    public final void setTotal_rest_activity_calories(int i) {
        this.k = i;
    }

    public final void setTotal_step(int i) {
        this.j = i;
    }

    public final void setType(@Nullable List<Integer> list) {
        this.q = list;
    }

    public final void setWalk_goal_time(int i) {
        this.s = i;
    }

    public final void setWear_flag_array(@Nullable List<Integer> list) {
        this.p = list;
    }

    public final void setYear(int i) {
        this.c = i;
    }
}
