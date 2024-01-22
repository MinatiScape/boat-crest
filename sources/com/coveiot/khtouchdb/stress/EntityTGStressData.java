package com.coveiot.khtouchdb.stress;

import androidx.room.Entity;
import com.coveiot.khtouchdb.stress.model.KHTGStressDataItemBean;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"date", "startTime", DeviceKey.MacAddress}, tableName = "entity_tg_stress")
/* loaded from: classes8.dex */
public final class EntityTGStressData {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f7196a;
    public int b;
    @NotNull
    public String c;
    public boolean d;
    public int e;
    @Nullable
    public List<KHTGStressDataItemBean> f;
    public long g;
    public boolean h;

    public EntityTGStressData(@NotNull String date, int i, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.f7196a = date;
        this.b = i;
        this.c = macAddress;
    }

    @NotNull
    public final String getDate() {
        return this.f7196a;
    }

    public final boolean getHaveMoreData() {
        return this.d;
    }

    @Nullable
    public final List<KHTGStressDataItemBean> getItems() {
        return this.f;
    }

    @NotNull
    public final String getMacAddress() {
        return this.c;
    }

    public final int getOffset() {
        return this.e;
    }

    public final int getStartTime() {
        return this.b;
    }

    public final long getTimeStamp() {
        return this.g;
    }

    public final boolean isProcessed() {
        return this.h;
    }

    public final void setDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f7196a = str;
    }

    public final void setHaveMoreData(boolean z) {
        this.d = z;
    }

    public final void setItems(@Nullable List<KHTGStressDataItemBean> list) {
        this.f = list;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.c = str;
    }

    public final void setOffset(int i) {
        this.e = i;
    }

    public final void setProcessed(boolean z) {
        this.h = z;
    }

    public final void setStartTime(int i) {
        this.b = i;
    }

    public final void setTimeStamp(long j) {
        this.g = j;
    }
}
