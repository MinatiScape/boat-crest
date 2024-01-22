package com.coveiot.khtouchdb.walk;

import androidx.room.Entity;
import com.coveiot.khtouchdb.walk.model.KHTGStepDataItemBean;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"date", DeviceKey.MacAddress}, tableName = "entity_tg_steps")
/* loaded from: classes8.dex */
public final class EntityTGStepData {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f7200a;
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
    @Nullable
    public List<KHTGStepDataItemBean> l;
    public long m;
    public boolean n;

    public EntityTGStepData(@NotNull String date, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.f7200a = date;
        this.b = macAddress;
    }

    @NotNull
    public final String getDate() {
        return this.f7200a;
    }

    public final int getItemCount() {
        return this.e;
    }

    @Nullable
    public final List<KHTGStepDataItemBean> getItems() {
        return this.l;
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

    public final int getPerMinute() {
        return this.d;
    }

    public final int getStandCount() {
        return this.k;
    }

    public final long getTimeStamp() {
        return this.m;
    }

    public final int getTotalActiveTime() {
        return this.j;
    }

    public final int getTotalCal() {
        return this.h;
    }

    public final int getTotalDistance() {
        return this.i;
    }

    public final int getTotalSteps() {
        return this.g;
    }

    public final boolean isProcessed() {
        return this.n;
    }

    public final void setDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f7200a = str;
    }

    public final void setItemCount(int i) {
        this.e = i;
    }

    public final void setItems(@Nullable List<KHTGStepDataItemBean> list) {
        this.l = list;
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

    public final void setPerMinute(int i) {
        this.d = i;
    }

    public final void setProcessed(boolean z) {
        this.n = z;
    }

    public final void setStandCount(int i) {
        this.k = i;
    }

    public final void setTimeStamp(long j) {
        this.m = j;
    }

    public final void setTotalActiveTime(int i) {
        this.j = i;
    }

    public final void setTotalCal(int i) {
        this.h = i;
    }

    public final void setTotalDistance(int i) {
        this.i = i;
    }

    public final void setTotalSteps(int i) {
        this.g = i;
    }
}
