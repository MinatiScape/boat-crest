package com.coveiot.covedb.accelerometer.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
@Entity(primaryKeys = {"serial_number", "timestamp"}, tableName = "rawAccelerometerDataHistory")
/* loaded from: classes8.dex */
public class EntityRawAccelerometerData {
    @NonNull
    @ColumnInfo(name = "timestamp")

    /* renamed from: a  reason: collision with root package name */
    public long f6936a;
    @NonNull
    @ColumnInfo(name = "serial_number")
    public String b;
    @ColumnInfo(name = "recordNumber")
    public int c;
    @ColumnInfo(name = "accelerometerDataFilePath")
    public String d;
    @ColumnInfo(name = "samplingRate")
    public int e;
    @ColumnInfo(name = "interval")
    public int f;
    @ColumnInfo(name = "duration")
    public int g;
    @ColumnInfo(name = "is_sync_to_server")
    public int h;

    public String getAccelerometerDataFilePath() {
        return this.d;
    }

    public int getDuration() {
        return this.g;
    }

    public int getInterval() {
        return this.f;
    }

    public int getIs_sync_to_server() {
        return this.h;
    }

    public int getRecordNumber() {
        return this.c;
    }

    public int getSamplingRate() {
        return this.e;
    }

    public String getSerial_number() {
        return this.b;
    }

    public long getTimestamp() {
        return this.f6936a;
    }

    public void setAccelerometerDataFilePath(String str) {
        this.d = str;
    }

    public void setDuration(int i) {
        this.g = i;
    }

    public void setInterval(int i) {
        this.f = i;
    }

    public void setIs_sync_to_server(int i) {
        this.h = i;
    }

    public void setRecordNumber(int i) {
        this.c = i;
    }

    public void setSamplingRate(int i) {
        this.e = i;
    }

    public void setSerial_number(String str) {
        this.b = str;
    }

    public void setTimestamp(long j) {
        this.f6936a = j;
    }
}
