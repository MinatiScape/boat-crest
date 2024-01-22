package com.coveiot.khtouchdb.sleep;

import androidx.room.Entity;
import com.coveiot.khtouchdb.sleep.model.KHTGSleepDataItemBean;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"date", DeviceKey.MacAddress, "endHour", "endMinute"}, tableName = "entity_tg_sleep")
/* loaded from: classes8.dex */
public final class EntityTGSleepData {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f7188a;
    public int b;
    public int c;
    @NotNull
    public String d;
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
    public List<KHTGSleepDataItemBean> q;
    public long r;
    public boolean s;

    public EntityTGSleepData(@NotNull String date, int i, int i2, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.f7188a = date;
        this.b = i;
        this.c = i2;
        this.d = macAddress;
    }

    @NotNull
    public final String getDate() {
        return this.f7188a;
    }

    public final int getDeepCount() {
        return this.j;
    }

    public final int getDeepMinute() {
        return this.n;
    }

    public final int getEndHour() {
        return this.b;
    }

    public final int getEndMinute() {
        return this.c;
    }

    public final int getEyeMoveCount() {
        return this.l;
    }

    public final int getEyeMoveMinute() {
        return this.o;
    }

    public final int getItemCount() {
        return this.g;
    }

    @Nullable
    public final List<KHTGSleepDataItemBean> getItems() {
        return this.q;
    }

    public final int getLightCount() {
        return this.i;
    }

    public final int getLightMinute() {
        return this.m;
    }

    @NotNull
    public final String getMacAddress() {
        return this.d;
    }

    public final int getPacketCount() {
        return this.h;
    }

    public final int getSleepMinute() {
        return this.f;
    }

    public final int getSleepScore() {
        return this.p;
    }

    public final long getTimeStamp() {
        return this.r;
    }

    public final int getTotalMinute() {
        return this.e;
    }

    public final int getWakeCount() {
        return this.k;
    }

    public final boolean isProcessed() {
        return this.s;
    }

    public final void setDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f7188a = str;
    }

    public final void setDeepCount(int i) {
        this.j = i;
    }

    public final void setDeepMinute(int i) {
        this.n = i;
    }

    public final void setEndHour(int i) {
        this.b = i;
    }

    public final void setEndMinute(int i) {
        this.c = i;
    }

    public final void setEyeMoveCount(int i) {
        this.l = i;
    }

    public final void setEyeMoveMinute(int i) {
        this.o = i;
    }

    public final void setItemCount(int i) {
        this.g = i;
    }

    public final void setItems(@Nullable List<KHTGSleepDataItemBean> list) {
        this.q = list;
    }

    public final void setLightCount(int i) {
        this.i = i;
    }

    public final void setLightMinute(int i) {
        this.m = i;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.d = str;
    }

    public final void setPacketCount(int i) {
        this.h = i;
    }

    public final void setProcessed(boolean z) {
        this.s = z;
    }

    public final void setSleepMinute(int i) {
        this.f = i;
    }

    public final void setSleepScore(int i) {
        this.p = i;
    }

    public final void setTimeStamp(long j) {
        this.r = j;
    }

    public final void setTotalMinute(int i) {
        this.e = i;
    }

    public final void setWakeCount(int i) {
        this.k = i;
    }
}
