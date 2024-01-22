package com.coveiot.khidodb.heartrate;

import androidx.room.Entity;
import com.coveiot.khidodb.heartrate.model.KHHealthHeartRateHighLowItem;
import com.coveiot.khidodb.heartrate.model.KHHealthHeartRateSecondItem;
import com.coveiot.khidodb.heartrate.model.KHHealthHeartRateSecond_Interval;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.Date;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"year", "month", WeatherCriteria.UNIT_TYPE_DAY, "startTime", DeviceKey.MacAddress}, tableName = "health_heartrate_v3")
/* loaded from: classes8.dex */
public final class EntityHealthHeartRateSecond {

    /* renamed from: a  reason: collision with root package name */
    public int f7089a;
    public int b;
    public int c;
    public int d;
    @NotNull
    public String e;
    @Nullable
    public List<KHHealthHeartRateSecond_Interval> f;
    @Nullable
    public List<KHHealthHeartRateSecondItem> g;
    @Nullable
    public List<KHHealthHeartRateHighLowItem> h;
    public int i;
    @Nullable
    public Long j;
    public long k;
    @Nullable
    public Date l;
    public int m;
    public int n;
    public int o;
    @Nullable
    public List<Integer> p;
    public int q;
    public long r;
    public boolean s;

    public EntityHealthHeartRateSecond(int i, int i2, int i3, int i4, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.f7089a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = macAddress;
    }

    public final long getDId() {
        return this.k;
    }

    @Nullable
    public final Long getDataId() {
        return this.j;
    }

    @Nullable
    public final Date getDate() {
        return this.l;
    }

    public final int getDay() {
        return this.f7089a;
    }

    public final int getFive_min_avg_data() {
        return this.o;
    }

    @Nullable
    public final List<Integer> getFive_min_data() {
        return this.p;
    }

    public final int getFive_min_max_data() {
        return this.m;
    }

    public final int getFive_min_min_data() {
        return this.n;
    }

    @Nullable
    public final List<KHHealthHeartRateSecond_Interval> getHrInterval() {
        return this.f;
    }

    @Nullable
    public final List<KHHealthHeartRateHighLowItem> getHr_data() {
        return this.h;
    }

    public final int getHr_data_count() {
        return this.q;
    }

    @Nullable
    public final List<KHHealthHeartRateSecondItem> getItems() {
        return this.g;
    }

    @NotNull
    public final String getMacAddress() {
        return this.e;
    }

    public final int getMonth() {
        return this.b;
    }

    public final int getSilentHR() {
        return this.i;
    }

    public final int getStartTime() {
        return this.d;
    }

    public final long getTimestamp() {
        return this.r;
    }

    public final int getYear() {
        return this.c;
    }

    public final boolean isProcessed() {
        return this.s;
    }

    public final void setDId(long j) {
        this.k = j;
    }

    public final void setDataId(@Nullable Long l) {
        this.j = l;
    }

    public final void setDate(@Nullable Date date) {
        this.l = date;
    }

    public final void setDay(int i) {
        this.f7089a = i;
    }

    public final void setFive_min_avg_data(int i) {
        this.o = i;
    }

    public final void setFive_min_data(@Nullable List<Integer> list) {
        this.p = list;
    }

    public final void setFive_min_max_data(int i) {
        this.m = i;
    }

    public final void setFive_min_min_data(int i) {
        this.n = i;
    }

    public final void setHrInterval(@Nullable List<KHHealthHeartRateSecond_Interval> list) {
        this.f = list;
    }

    public final void setHr_data(@Nullable List<KHHealthHeartRateHighLowItem> list) {
        this.h = list;
    }

    public final void setHr_data_count(int i) {
        this.q = i;
    }

    public final void setItems(@Nullable List<KHHealthHeartRateSecondItem> list) {
        this.g = list;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.e = str;
    }

    public final void setMonth(int i) {
        this.b = i;
    }

    public final void setProcessed(boolean z) {
        this.s = z;
    }

    public final void setSilentHR(int i) {
        this.i = i;
    }

    public final void setStartTime(int i) {
        this.d = i;
    }

    public final void setTimestamp(long j) {
        this.r = j;
    }

    public final void setYear(int i) {
        this.c = i;
    }
}
