package com.coveiot.khtouchdb.heartrate;

import androidx.room.Entity;
import com.coveiot.khtouchdb.heartrate.model.KHTGHRDataItemBean;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"date", DeviceKey.MacAddress}, tableName = "entity_tg_heart_rate")
/* loaded from: classes8.dex */
public final class EntityTGHeartRateData {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f7184a;
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
    @Nullable
    public List<KHTGHRDataItemBean> q;
    public long r;
    public boolean s;

    public EntityTGHeartRateData(@NotNull String date, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.f7184a = date;
        this.b = macAddress;
    }

    public final int getAerobicMinutes() {
        return this.j;
    }

    public final int getAerobicThreshold() {
        return this.i;
    }

    public final int getAnaerobicMinutes() {
        return this.p;
    }

    public final int getAnaerobicThreshold() {
        return this.o;
    }

    public final int getBurnFatMinutes() {
        return this.h;
    }

    public final int getBurnFatThreshold() {
        return this.g;
    }

    @NotNull
    public final String getDate() {
        return this.f7184a;
    }

    public final int getItemCount() {
        return this.e;
    }

    @Nullable
    public final List<KHTGHRDataItemBean> getItems() {
        return this.q;
    }

    public final int getLimitMinutes() {
        return this.l;
    }

    public final int getLimitThreshold() {
        return this.k;
    }

    @NotNull
    public final String getMacAddress() {
        return this.b;
    }

    public final int getMinuteOffset() {
        return this.c;
    }

    public final int getPacketCount() {
        return this.f;
    }

    public final int getSilentHr() {
        return this.d;
    }

    public final long getTimeStamp() {
        return this.r;
    }

    public final int getWarmUpMinutes() {
        return this.n;
    }

    public final int getWarmUpThreshold() {
        return this.m;
    }

    public final boolean isProcessed() {
        return this.s;
    }

    public final void setAerobicMinutes(int i) {
        this.j = i;
    }

    public final void setAerobicThreshold(int i) {
        this.i = i;
    }

    public final void setAnaerobicMinutes(int i) {
        this.p = i;
    }

    public final void setAnaerobicThreshold(int i) {
        this.o = i;
    }

    public final void setBurnFatMinutes(int i) {
        this.h = i;
    }

    public final void setBurnFatThreshold(int i) {
        this.g = i;
    }

    public final void setDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f7184a = str;
    }

    public final void setItemCount(int i) {
        this.e = i;
    }

    public final void setItems(@Nullable List<KHTGHRDataItemBean> list) {
        this.q = list;
    }

    public final void setLimitMinutes(int i) {
        this.l = i;
    }

    public final void setLimitThreshold(int i) {
        this.k = i;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.b = str;
    }

    public final void setMinuteOffset(int i) {
        this.c = i;
    }

    public final void setPacketCount(int i) {
        this.f = i;
    }

    public final void setProcessed(boolean z) {
        this.s = z;
    }

    public final void setSilentHr(int i) {
        this.d = i;
    }

    public final void setTimeStamp(long j) {
        this.r = j;
    }

    public final void setWarmUpMinutes(int i) {
        this.n = i;
    }

    public final void setWarmUpThreshold(int i) {
        this.m = i;
    }
}
