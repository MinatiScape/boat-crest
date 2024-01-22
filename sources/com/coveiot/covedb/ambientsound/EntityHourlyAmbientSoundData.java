package com.coveiot.covedb.ambientsound;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import java.util.ArrayList;
@Entity(primaryKeys = {"serial_no", "start_time", "date"}, tableName = "hourly_ambient_sound_table")
/* loaded from: classes8.dex */
public class EntityHourlyAmbientSoundData {
    @NonNull
    @ColumnInfo(name = "date")

    /* renamed from: a  reason: collision with root package name */
    public String f6939a;
    @NonNull
    @ColumnInfo(name = "start_time")
    public String b;
    @ColumnInfo(name = "end_time")
    public String c;
    @ColumnInfo(name = "min_ambient_sound")
    public int d;
    @ColumnInfo(name = "max_ambient_sound")
    public int e;
    @ColumnInfo(name = "avg_ambient_sound")
    public double f;
    @ColumnInfo(name = "coded_values")
    public ArrayList<Integer> g;
    @NonNull
    @ColumnInfo(name = "serial_no")
    public String serial_no;

    public double getAvgAmbientSound() {
        return this.f;
    }

    public ArrayList<Integer> getCodedValues() {
        return this.g;
    }

    public String getDate() {
        return this.f6939a;
    }

    public String getEndTime() {
        return this.c;
    }

    public int getMaxAmbientSound() {
        return this.e;
    }

    public int getMinAmbientSound() {
        return this.d;
    }

    @NonNull
    public String getSerialNo() {
        return this.serial_no;
    }

    @NonNull
    public String getStartTime() {
        return this.b;
    }

    public void setAvgAmbientSound(double d) {
        this.f = d;
    }

    public void setCodedValues(ArrayList<Integer> arrayList) {
        this.g = arrayList;
    }

    public void setDate(String str) {
        this.f6939a = str;
    }

    public void setEndTime(String str) {
        this.c = str;
    }

    public void setMaxAmbientSound(int i) {
        this.e = i;
    }

    public void setMinAmbientSound(int i) {
        this.d = i;
    }

    public void setSerialNo(@NonNull String str) {
        this.serial_no = str;
    }

    public void setStartTime(@NonNull String str) {
        this.b = str;
    }
}
