package com.coveiot.covedb.stress.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
@Entity(primaryKeys = {"id", "serial_no"}, tableName = "hourly_stress")
/* loaded from: classes8.dex */
public class HourlyStress {
    @ColumnInfo(name = "start_time")

    /* renamed from: a  reason: collision with root package name */
    public String f6982a;
    @ColumnInfo(name = "end_time")
    public String b;
    @ColumnInfo(name = "codevalue")
    public List<Integer> c;
    @SerializedName("otherData")
    @Ignore
    private OtherDataBean d;
    @ColumnInfo(name = "date")
    public String mDate;
    @NonNull
    @ColumnInfo(name = "serial_no")
    public String mac_address;
    @ColumnInfo(name = "stress_avg")
    public double stress_avg;
    @ColumnInfo(name = "stress_high")
    public int stress_high;
    @ColumnInfo(name = "stress_low")
    public int stress_low;

    /* loaded from: classes8.dex */
    public static class OtherDataBean {
        @SerializedName("codedValues")

        /* renamed from: a  reason: collision with root package name */
        private List<Integer> f6983a;

        public OtherDataBean(List<Integer> list) {
            this.f6983a = list;
        }

        public List<Integer> getCodedValues() {
            return this.f6983a;
        }

        public void setCodedValues(List<Integer> list) {
            this.f6983a = list;
        }
    }

    public HourlyStress() {
        this.c = new ArrayList();
    }

    public List<Integer> getCodevalue() {
        return this.c;
    }

    public String getEndTime() {
        return this.b;
    }

    public String getId() {
        return this.mDate + HexStringBuilder.DEFAULT_SEPARATOR + this.f6982a;
    }

    public String getMacAddress() {
        return this.mac_address;
    }

    public OtherDataBean getOtherData() {
        OtherDataBean otherDataBean = new OtherDataBean(this.c);
        this.d = otherDataBean;
        return otherDataBean;
    }

    public String getStartTime() {
        return this.f6982a;
    }

    public double getStress_avg() {
        return this.stress_avg;
    }

    public int getStress_high() {
        return this.stress_high;
    }

    public int getStress_low() {
        return this.stress_low;
    }

    public String getmDate() {
        return this.mDate;
    }

    public void setCodevalue(List<Integer> list) {
        this.c = list;
    }

    public void setEndTime(String str) {
        this.b = str;
    }

    public void setId(String str) {
    }

    public void setMacAddress(String str) {
        this.mac_address = str;
    }

    public void setOtherData(OtherDataBean otherDataBean) {
        this.d = otherDataBean;
    }

    public void setStartTime(String str) {
        this.f6982a = str;
    }

    public void setStress_avg(double d) {
        this.stress_avg = d;
    }

    public void setStress_high(int i) {
        this.stress_high = i;
    }

    public void setStress_low(int i) {
        this.stress_low = i;
    }

    public void setmDate(String str) {
        this.mDate = str;
    }

    public String toString() {
        return "HourlyTemperature{mDate='" + this.mDate + "', startTime='" + this.f6982a + "', endTime='" + this.b + "', codevalue=" + this.c + '}';
    }

    public HourlyStress(String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        this.c = arrayList;
        this.mDate = str;
        this.f6982a = str2;
        this.b = str3;
        this.c = arrayList;
    }
}
