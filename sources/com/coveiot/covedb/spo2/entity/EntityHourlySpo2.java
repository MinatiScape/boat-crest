package com.coveiot.covedb.spo2.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.List;
@Entity(primaryKeys = {"id", "serial_no"}, tableName = "hourly_spo2")
/* loaded from: classes8.dex */
public class EntityHourlySpo2 {
    @ColumnInfo(name = "start_time")

    /* renamed from: a  reason: collision with root package name */
    public String f6978a;
    @ColumnInfo(name = "end_time")
    public String b;
    @ColumnInfo(name = "codevalue")
    public List<Integer> codevalue = new ArrayList();
    @ColumnInfo(name = "date")
    public String mDate;
    @NonNull
    @ColumnInfo(name = "serial_no")
    public String mac_address;
    @ColumnInfo(name = "spo2_avg")
    public double spo2_avg;
    @ColumnInfo(name = "spo2_high")
    public int spo2_high;
    @ColumnInfo(name = "spo2_low")
    public int spo2_low;

    public List<Integer> getCodevalue() {
        return this.codevalue;
    }

    public String getEndTime() {
        return this.b;
    }

    @NonNull
    public String getId() {
        return this.mDate + HexStringBuilder.DEFAULT_SEPARATOR + this.f6978a;
    }

    @NonNull
    public String getMac_address() {
        return this.mac_address;
    }

    public double getSpo2_avg() {
        return this.spo2_avg;
    }

    public int getSpo2_high() {
        return this.spo2_high;
    }

    public int getSpo2_low() {
        return this.spo2_low;
    }

    public String getStartTime() {
        return this.f6978a;
    }

    public String getmDate() {
        return this.mDate;
    }

    public void setCodevalue(List<Integer> list) {
        this.codevalue = list;
    }

    public void setEndTime(String str) {
        this.b = str;
    }

    public void setId(@NonNull String str) {
    }

    public void setMac_address(@NonNull String str) {
        this.mac_address = str;
    }

    public void setSpo2_avg(double d) {
        this.spo2_avg = d;
    }

    public void setSpo2_high(int i) {
        this.spo2_high = i;
    }

    public void setSpo2_low(int i) {
        this.spo2_low = i;
    }

    public void setStartTime(String str) {
        this.f6978a = str;
    }

    public void setmDate(String str) {
        this.mDate = str;
    }
}
