package com.coveiot.covedb.ppg.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
@Entity(primaryKeys = {"serial_number", "timestamp"}, tableName = "rawPPGDataHistory")
/* loaded from: classes8.dex */
public class EntityRawPPGData {
    @NonNull
    @ColumnInfo(name = "timestamp")

    /* renamed from: a  reason: collision with root package name */
    public long f6961a;
    @NonNull
    @ColumnInfo(name = "serial_number")
    public String b;
    @ColumnInfo(name = "recordNumber")
    public int c;
    @ColumnInfo(name = "ppgDataFilePath")
    public String d;
    @ColumnInfo(name = "samplingRate")
    public int e;
    @ColumnInfo(name = "ppgType")
    public int f;
    @ColumnInfo(name = "interval")
    public int g;
    @ColumnInfo(name = "duration")
    public int h;
    @ColumnInfo(name = "movementLevel")
    public int i;
    @ColumnInfo(name = "is_sync_to_server")
    public int j;

    public int getDuration() {
        return this.h;
    }

    public int getInterval() {
        return this.g;
    }

    public int getIs_sync_to_server() {
        return this.j;
    }

    public int getMovementLevel() {
        return this.i;
    }

    public String getPpgDataFilePath() {
        return this.d;
    }

    public int getPpgType() {
        return this.f;
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
        return this.f6961a;
    }

    public void setDuration(int i) {
        this.h = i;
    }

    public void setInterval(int i) {
        this.g = i;
    }

    public void setIs_sync_to_server(int i) {
        this.j = i;
    }

    public void setMovementLevel(int i) {
        this.i = i;
    }

    public void setPpgDataFilePath(String str) {
        this.d = str;
    }

    public void setPpgType(int i) {
        this.f = i;
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
        this.f6961a = j;
    }
}
