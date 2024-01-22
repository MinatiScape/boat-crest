package com.coveiot.covedb.rr.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import java.util.ArrayList;
@Entity(primaryKeys = {"serial_no", "start_time", "date"}, tableName = "hourly_rr_table")
/* loaded from: classes8.dex */
public class EntityHourlyRrData {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public String f6965a;
    public int avgRrValue;
    public String b;
    @NonNull
    @ColumnInfo(name = "date")
    public String c;
    @ColumnInfo(name = "coded_values")
    public ArrayList<Integer> d;
    @NonNull
    public String serial_no;

    public int getAvgRrValue() {
        return this.avgRrValue;
    }

    public ArrayList<Integer> getCodedValues() {
        return this.d;
    }

    @NonNull
    public String getDate() {
        return this.c;
    }

    public String getEnd_time() {
        return this.b;
    }

    @NonNull
    public String getSerial_no() {
        return this.serial_no;
    }

    @NonNull
    public String getStart_time() {
        return this.f6965a;
    }

    public void setAvgRrValue(int i) {
        this.avgRrValue = i;
    }

    public void setCodedValues(ArrayList<Integer> arrayList) {
        this.d = arrayList;
    }

    public void setDate(@NonNull String str) {
        this.c = str;
    }

    public void setEnd_time(String str) {
        this.b = str;
    }

    public void setSerial_no(@NonNull String str) {
        this.serial_no = str;
    }

    public void setStart_time(@NonNull String str) {
        this.f6965a = str;
    }
}
