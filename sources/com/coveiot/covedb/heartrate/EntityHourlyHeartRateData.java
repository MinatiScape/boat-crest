package com.coveiot.covedb.heartrate;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import java.util.ArrayList;
@Entity(primaryKeys = {"serial_no", "start_time", "date"}, tableName = "hourly_heart_rate_table")
/* loaded from: classes8.dex */
public class EntityHourlyHeartRateData {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public String f6949a;
    public String b;
    @NonNull
    @ColumnInfo(name = "date")
    public String c;
    public int d;
    public int e;
    public float f;
    public int g;
    public long h;
    @ColumnInfo(name = "coded_values")
    public ArrayList<Integer> i;
    @NonNull
    public String serial_no;

    public float getAvgHeartRate() {
        return this.f;
    }

    public ArrayList<Integer> getCodedValues() {
        return this.i;
    }

    public String getDate() {
        return this.c;
    }

    public String getEndTime() {
        return this.b;
    }

    public int getMaxHeartRate() {
        return this.e;
    }

    public int getMinHeartRate() {
        return this.d;
    }

    @NonNull
    public String getSerialNo() {
        return this.serial_no;
    }

    @NonNull
    public String getStartTime() {
        return this.f6949a;
    }

    public void setAvgHeartRate(float f) {
        this.f = f;
    }

    public void setCodedValues(ArrayList<Integer> arrayList) {
        this.i = arrayList;
    }

    public void setDate(String str) {
        this.c = str;
    }

    public void setEndTime(String str) {
        this.b = str;
    }

    public void setMaxHeartRate(int i) {
        this.e = i;
    }

    public void setMinHeartRate(int i) {
        this.d = i;
    }

    public void setSerialNo(@NonNull String str) {
        this.serial_no = str;
    }

    public void setStartTime(@NonNull String str) {
        this.f6949a = str;
    }
}
