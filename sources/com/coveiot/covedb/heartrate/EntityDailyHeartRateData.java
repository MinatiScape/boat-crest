package com.coveiot.covedb.heartrate;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
@Entity(primaryKeys = {"serial_no", "date"}, tableName = "daily_heart_rate_table")
/* loaded from: classes8.dex */
public class EntityDailyHeartRateData {
    @NonNull
    @ColumnInfo(name = "date")

    /* renamed from: a  reason: collision with root package name */
    public String f6948a;
    public int b;
    public int c;
    public int d;
    public int e;
    public float f;
    public float g;
    public String h;
    public boolean is_sync_server;
    @NonNull
    public String serial_no;

    public int getAvgHeartRate() {
        return this.d;
    }

    public float getColumn_1() {
        return this.f;
    }

    public float getColumn_2() {
        return this.g;
    }

    public String getColumn_3() {
        return this.h;
    }

    public String getDate() {
        return this.f6948a;
    }

    public int getMaxHeartRate() {
        return this.c;
    }

    public int getMinHeartRate() {
        return this.b;
    }

    public int getRestHeartRate() {
        return this.e;
    }

    @NonNull
    public String getSerialNo() {
        return this.serial_no;
    }

    public boolean isIsSyncServer() {
        return this.is_sync_server;
    }

    public void setAvgHeartRate(int i) {
        this.d = i;
    }

    public void setColumn_1(float f) {
        this.f = f;
    }

    public void setColumn_2(float f) {
        this.g = f;
    }

    public void setColumn_3(String str) {
        this.h = str;
    }

    public void setDate(String str) {
        this.f6948a = str;
    }

    public void setIsSyncServer(boolean z) {
        this.is_sync_server = z;
    }

    public void setMaxHeartRate(int i) {
        this.c = i;
    }

    public void setMinHeartRate(int i) {
        this.b = i;
    }

    public void setRestHeartRate(int i) {
        this.e = i;
    }

    public void setSerialNo(@NonNull String str) {
        this.serial_no = str;
    }
}
