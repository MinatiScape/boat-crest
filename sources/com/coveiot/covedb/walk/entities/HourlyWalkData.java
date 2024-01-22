package com.coveiot.covedb.walk.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
@Entity(primaryKeys = {"id", "serial_no"}, tableName = "hourlywalkdata")
/* loaded from: classes8.dex */
public class HourlyWalkData {
    @ColumnInfo(name = "start_time")

    /* renamed from: a  reason: collision with root package name */
    public String f6994a;
    @ColumnInfo(name = "end_time")
    public String b;
    @ColumnInfo(name = "interval_value")
    public int c;
    public ArrayList<Integer> d;
    @ColumnInfo(name = "distance_code_value")
    public ArrayList<Integer> e;
    @ColumnInfo(name = "calorie_code_value")
    public List<Float> f;
    @ColumnInfo(name = "calories")
    public int g;
    @ColumnInfo(name = "distance")
    public int h;
    @ColumnInfo(name = "active_time")
    public Integer i;
    @SerializedName("otherData")
    @Ignore
    private OtherDataBean j;
    @ColumnInfo(name = "date")
    public String mDate;
    @NonNull
    @ColumnInfo(name = "serial_no")
    public String mac_address;

    /* loaded from: classes8.dex */
    public static class OtherDataBean {
        @SerializedName("codedValues")

        /* renamed from: a  reason: collision with root package name */
        private List<Integer> f6995a;

        public OtherDataBean(List<Integer> list) {
            this.f6995a = list;
        }

        public List<Integer> getCodedValues() {
            return this.f6995a;
        }

        public void setCodedValues(List<Integer> list) {
            this.f6995a = list;
        }
    }

    public HourlyWalkData() {
        this.d = new ArrayList<>();
        this.i = null;
    }

    public Integer getActiveTime() {
        return this.i;
    }

    public List<Float> getCalorieCodeValue() {
        return this.f;
    }

    public int getCalories() {
        return this.g;
    }

    public ArrayList<Integer> getCodevalue() {
        return this.d;
    }

    public int getDistance() {
        return this.h;
    }

    public ArrayList<Integer> getDistanceCodeValue() {
        return this.e;
    }

    public String getEndTime() {
        return this.b;
    }

    public String getId() {
        return this.mDate + HexStringBuilder.DEFAULT_SEPARATOR + this.f6994a;
    }

    public int getIntervelValue() {
        return this.c;
    }

    public String getMacAddress() {
        return this.mac_address;
    }

    public OtherDataBean getOtherData() {
        OtherDataBean otherDataBean = new OtherDataBean(this.d);
        this.j = otherDataBean;
        return otherDataBean;
    }

    public String getStartTime() {
        return this.f6994a;
    }

    public String getmDate() {
        return this.mDate;
    }

    public void setActiveTime(Integer num) {
        this.i = num;
    }

    public void setCalorieCodeValue(List<Float> list) {
        this.f = list;
    }

    public void setCalories(int i) {
        this.g = i;
    }

    public void setCodevalue(ArrayList<Integer> arrayList) {
        this.d = arrayList;
    }

    public void setDistance(int i) {
        this.h = i;
    }

    public void setDistanceCodeValue(ArrayList<Integer> arrayList) {
        this.e = arrayList;
    }

    public void setEndTime(String str) {
        this.b = str;
    }

    public void setId(String str) {
    }

    public void setIntervelValue(int i) {
        this.c = i;
    }

    public void setMacAddress(String str) {
        this.mac_address = str;
    }

    public void setOtherData(OtherDataBean otherDataBean) {
        this.j = otherDataBean;
    }

    public void setStartTime(String str) {
        this.f6994a = str;
    }

    public void setmDate(String str) {
        this.mDate = str;
    }

    public String toString() {
        return "HourlyWalkData{mDate='" + this.mDate + "', startTime='" + this.f6994a + "', endTime='" + this.b + "', intervelValue=" + this.c + ", codevalue=" + this.d + '}';
    }

    public HourlyWalkData(String str, String str2, String str3, ArrayList<Integer> arrayList, int i) {
        this.d = new ArrayList<>();
        this.i = null;
        this.mDate = str;
        this.f6994a = str2;
        this.b = str3;
        this.c = i;
        this.d = arrayList;
    }
}
