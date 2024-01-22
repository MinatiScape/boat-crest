package com.coveiot.khidodb.noise;

import androidx.room.Entity;
import com.coveiot.khidodb.noise.model.KHHealthNoiseItem;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"year", "month", WeatherCriteria.UNIT_TYPE_DAY, "startTime", DeviceKey.MacAddress}, tableName = "health_noise_v3")
/* loaded from: classes8.dex */
public final class EntityHealthNoise {

    /* renamed from: a  reason: collision with root package name */
    public int f7095a;
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
    @Nullable
    public List<KHHealthNoiseItem> m;
    public long n;
    public boolean o;

    public EntityHealthNoise(int i, int i2, int i3, int i4, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.f7095a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = macAddress;
    }

    public final int getAvgNoise() {
        return this.i;
    }

    public final int getDay() {
        return this.f7095a;
    }

    public final int getHour() {
        return this.f;
    }

    public final int getInterval() {
        return this.l;
    }

    @Nullable
    public final List<KHHealthNoiseItem> getItems() {
        return this.m;
    }

    @NotNull
    public final String getMacAddress() {
        return this.e;
    }

    public final int getMaxNoise() {
        return this.j;
    }

    public final int getMinNoise() {
        return this.k;
    }

    public final int getMinute() {
        return this.g;
    }

    public final int getMonth() {
        return this.b;
    }

    public final int getSecond() {
        return this.h;
    }

    public final int getStartTime() {
        return this.d;
    }

    public final long getTimestamp() {
        return this.n;
    }

    public final int getYear() {
        return this.c;
    }

    public final boolean isProcessed() {
        return this.o;
    }

    public final void setAvgNoise(int i) {
        this.i = i;
    }

    public final void setDay(int i) {
        this.f7095a = i;
    }

    public final void setHour(int i) {
        this.f = i;
    }

    public final void setInterval(int i) {
        this.l = i;
    }

    public final void setItems(@Nullable List<KHHealthNoiseItem> list) {
        this.m = list;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.e = str;
    }

    public final void setMaxNoise(int i) {
        this.j = i;
    }

    public final void setMinNoise(int i) {
        this.k = i;
    }

    public final void setMinute(int i) {
        this.g = i;
    }

    public final void setMonth(int i) {
        this.b = i;
    }

    public final void setProcessed(boolean z) {
        this.o = z;
    }

    public final void setSecond(int i) {
        this.h = i;
    }

    public final void setStartTime(int i) {
        this.d = i;
    }

    public final void setTimestamp(long j) {
        this.n = j;
    }

    public final void setYear(int i) {
        this.c = i;
    }
}
