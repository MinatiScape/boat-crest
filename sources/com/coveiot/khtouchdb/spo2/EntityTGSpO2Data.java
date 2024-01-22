package com.coveiot.khtouchdb.spo2;

import androidx.room.Entity;
import com.coveiot.khtouchdb.spo2.model.KHTGSpO2DataItemBean;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"date", DeviceKey.MacAddress}, tableName = "entity_tg_spo2")
/* loaded from: classes8.dex */
public final class EntityTGSpO2Data {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f7192a;
    @NotNull
    public String b;
    public boolean c;
    @Nullable
    public List<KHTGSpO2DataItemBean> d;
    public long e;
    public boolean f;

    public EntityTGSpO2Data(@NotNull String date, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.f7192a = date;
        this.b = macAddress;
    }

    @NotNull
    public final String getDate() {
        return this.f7192a;
    }

    public final boolean getHaveMoreData() {
        return this.c;
    }

    @Nullable
    public final List<KHTGSpO2DataItemBean> getItems() {
        return this.d;
    }

    @NotNull
    public final String getMacAddress() {
        return this.b;
    }

    public final long getTimeStamp() {
        return this.e;
    }

    public final boolean isProcessed() {
        return this.f;
    }

    public final void setDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f7192a = str;
    }

    public final void setHaveMoreData(boolean z) {
        this.c = z;
    }

    public final void setItems(@Nullable List<KHTGSpO2DataItemBean> list) {
        this.d = list;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.b = str;
    }

    public final void setProcessed(boolean z) {
        this.f = z;
    }

    public final void setTimeStamp(long j) {
        this.e = j;
    }
}
