package com.coveiot.khidodb.spo2;

import androidx.room.Entity;
import com.coveiot.khidodb.spo2.model.KHHealthSpo2Item;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.Date;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {WeatherCriteria.UNIT_TYPE_DAY, "month", "year", "startTime", DeviceKey.MacAddress}, tableName = "entity_health_spo2")
/* loaded from: classes8.dex */
public final class EntityHealthSpo2 {

    /* renamed from: a  reason: collision with root package name */
    public int f7103a;
    public int b;
    public int c;
    public int d;
    @NotNull
    public String e;
    @Nullable
    public Date f;
    public long g;
    public boolean h;
    @Nullable
    public List<KHHealthSpo2Item> i;

    public EntityHealthSpo2(int i, int i2, int i3, int i4, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.f7103a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = macAddress;
    }

    @Nullable
    public final Date getDate() {
        return this.f;
    }

    public final int getDay() {
        return this.f7103a;
    }

    @Nullable
    public final List<KHHealthSpo2Item> getItems() {
        return this.i;
    }

    @NotNull
    public final String getMacAddress() {
        return this.e;
    }

    public final int getMonth() {
        return this.b;
    }

    public final int getStartTime() {
        return this.d;
    }

    public final long getTimestamp() {
        return this.g;
    }

    public final int getYear() {
        return this.c;
    }

    public final boolean isProcessed() {
        return this.h;
    }

    public final void setDate(@Nullable Date date) {
        this.f = date;
    }

    public final void setDay(int i) {
        this.f7103a = i;
    }

    public final void setItems(@Nullable List<KHHealthSpo2Item> list) {
        this.i = list;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.e = str;
    }

    public final void setMonth(int i) {
        this.b = i;
    }

    public final void setProcessed(boolean z) {
        this.h = z;
    }

    public final void setStartTime(int i) {
        this.d = i;
    }

    public final void setTimestamp(long j) {
        this.g = j;
    }

    public final void setYear(int i) {
        this.c = i;
    }
}
