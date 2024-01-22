package com.coveiot.covedb.rr.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
@Entity(primaryKeys = {"serial_no", "date"}, tableName = "daily_rr_table")
/* loaded from: classes8.dex */
public class EntityDailyRrData {
    @NonNull
    @ColumnInfo(name = "date")

    /* renamed from: a  reason: collision with root package name */
    public String f6964a;
    public int b;
    public boolean is_sync_server;
    @NonNull
    public String serial_no;

    public int getAvgRrvalue() {
        return this.b;
    }

    @NonNull
    public String getDate() {
        return this.f6964a;
    }

    @NonNull
    public String getSerial_no() {
        return this.serial_no;
    }

    public boolean isIs_sync_server() {
        return this.is_sync_server;
    }

    public void setAvgRrvalue(int i) {
        this.b = i;
    }

    public void setDate(@NonNull String str) {
        this.f6964a = str;
    }

    public void setIs_sync_server(boolean z) {
        this.is_sync_server = z;
    }

    public void setSerial_no(@NonNull String str) {
        this.serial_no = str;
    }
}
