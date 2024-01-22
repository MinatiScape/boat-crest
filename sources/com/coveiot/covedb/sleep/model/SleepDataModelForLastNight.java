package com.coveiot.covedb.sleep.model;

import androidx.room.ColumnInfo;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class SleepDataModelForLastNight {

    /* renamed from: a  reason: collision with root package name */
    public String f6974a;
    @ColumnInfo(name = "start_time")
    public String b;
    @ColumnInfo(name = "end_time")
    public String c;
    public ArrayList<Integer> d = new ArrayList<>();
    @ColumnInfo(name = "ligth_sleep")
    public int e;
    @ColumnInfo(name = "deep_sleep")
    public int f;
    @ColumnInfo(name = "awake")
    public int g;
    public String h;

    public int getAwake() {
        return this.g;
    }

    public List<Integer> getCodevalue() {
        return this.d;
    }

    public String getDate() {
        return this.f6974a;
    }

    public int getDeepSleep() {
        return this.f;
    }

    public String getEndTime() {
        return this.c;
    }

    public String getId() {
        return this.f6974a + HexStringBuilder.DEFAULT_SEPARATOR + this.b;
    }

    public int getIntervalValue() {
        return this.f + this.e;
    }

    public int getLigthSleep() {
        return this.e;
    }

    public String getStartTime() {
        return this.b;
    }

    public String getToday_date() {
        return this.h;
    }

    public void setAwake(int i) {
        this.g = i;
    }

    public void setCodevalue(ArrayList<Integer> arrayList) {
        this.d = arrayList;
    }

    public void setDate(String str) {
        this.f6974a = str;
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

    public void setStartTime(String str) {
        this.b = str;
    }

    public void setToday_date(String str) {
        this.h = str;
    }

    public String toString() {
        return "HourlySleepData{date='" + this.f6974a + "', startTime='" + this.b + "', endTime='" + this.c + "', intervelValue=" + this.d.toString() + '}';
    }
}
