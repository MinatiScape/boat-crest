package com.coveiot.covedb.ambientsound;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
@Entity(primaryKeys = {"serial_no", "date"}, tableName = "daily_ambient_sound_table")
/* loaded from: classes8.dex */
public class EntityDailyAmbientSoundData {
    @NonNull
    @ColumnInfo(name = "date")

    /* renamed from: a  reason: collision with root package name */
    public String f6938a;
    @ColumnInfo(name = "min_ambient_sound")
    public int b;
    @ColumnInfo(name = "max_ambient_sound")
    public int c;
    @ColumnInfo(name = "avg_ambient_sound")
    public double d;
    @ColumnInfo(name = "total_time")
    public int e;
    public boolean is_sync_server;
    @NonNull
    public String serial_no;

    public double getAvgAmbientSound() {
        return this.d;
    }

    public String getDate() {
        return this.f6938a;
    }

    public int getMaxAmbientSound() {
        return this.c;
    }

    public int getMinAmbientSound() {
        return this.b;
    }

    @NonNull
    public String getSerialNo() {
        return this.serial_no;
    }

    public int getTotalTime() {
        return this.e;
    }

    public boolean isIsSyncServer() {
        return this.is_sync_server;
    }

    public void setAvgAmbientSound(double d) {
        this.d = d;
    }

    public void setDate(String str) {
        this.f6938a = str;
    }

    public void setIsSyncServer(boolean z) {
        this.is_sync_server = z;
    }

    public void setMaxAmbientSound(int i) {
        this.c = i;
    }

    public void setMinAmbientSound(int i) {
        this.b = i;
    }

    public void setSerialNo(@NonNull String str) {
        this.serial_no = str;
    }

    public void setTotalTime(int i) {
        this.e = i;
    }
}
