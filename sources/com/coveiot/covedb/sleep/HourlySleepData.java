package com.coveiot.covedb.sleep;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.List;
@Entity(primaryKeys = {"id", "serial_no"}, tableName = "hourlysleepdata")
/* loaded from: classes8.dex */
public class HourlySleepData {

    /* renamed from: a  reason: collision with root package name */
    public String f6972a;
    @ColumnInfo(name = "start_time")
    public String b;
    @ColumnInfo(name = "end_time")
    public String c;
    public ArrayList<Integer> d;
    @ColumnInfo(name = "ligth_sleep")
    public int e;
    @ColumnInfo(name = "deep_sleep")
    public int f;
    @ColumnInfo(name = "awake")
    public int g;
    @NonNull
    @ColumnInfo(name = "serial_no")
    public String mac_address;

    public HourlySleepData() {
        this.d = new ArrayList<>();
    }

    public int getAwake() {
        return this.g;
    }

    public List<Integer> getCodevalue() {
        return this.d;
    }

    public String getDate() {
        return this.f6972a;
    }

    public int getDeepSleep() {
        return this.f;
    }

    public String getEndTime() {
        return this.c;
    }

    public String getId() {
        return this.f6972a + HexStringBuilder.DEFAULT_SEPARATOR + this.b;
    }

    public int getIntervalValue() {
        return this.f + this.e;
    }

    public int getLigthSleep() {
        return this.e;
    }

    public String getMacAddress() {
        return this.mac_address;
    }

    public String getStartTime() {
        return this.b;
    }

    public void setAwake(int i) {
        this.g = i;
    }

    public void setCodevalue(ArrayList<Integer> arrayList) {
        this.d = arrayList;
    }

    public void setDate(String str) {
        this.f6972a = str;
    }

    public void setDeepSleep(int i) {
        this.f = i;
    }

    public void setEndTime(String str) {
        this.c = str;
    }

    public void setId(String str) {
    }

    public void setIntervalValue(int i) {
    }

    public void setLigthSleep(int i) {
        this.e = i;
    }

    public void setMacAddress(String str) {
        this.mac_address = str;
    }

    public void setStartTime(String str) {
        this.b = str;
    }

    public String toString() {
        return "HourlySleepData{date='" + this.f6972a + "', startTime='" + this.b + "', endTime='" + this.c + "', intervelValue=" + this.d.toString() + '}';
    }

    public HourlySleepData(String str, String str2, String str3, ArrayList<Integer> arrayList) {
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        this.d = arrayList2;
        this.f6972a = str;
        this.b = str2;
        this.c = str3;
        arrayList2.addAll(arrayList);
    }
}
