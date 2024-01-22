package com.coveiot.khjstyledb.deviceinfo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.jstyle.blesdk1860.constant.DeviceKey;
@Entity(tableName = "device_info")
/* loaded from: classes8.dex */
public class KHJstyleEntityDeviceInfo {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = DeviceKey.MacAddress)

    /* renamed from: a  reason: collision with root package name */
    public String f7120a;
    @ColumnInfo(name = "walk_last_sync_timestamp")
    public long b;

    public long getLastSyncWalkTimeStamp() {
        return this.b;
    }

    @NonNull
    public String getMacAddress() {
        return this.f7120a;
    }

    public void setLastSyncWalkTimeStamp(long j) {
        this.b = j;
    }

    public void setMacAddress(@NonNull String str) {
        this.f7120a = str;
    }
}
