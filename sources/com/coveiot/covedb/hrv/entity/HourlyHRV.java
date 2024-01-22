package com.coveiot.covedb.hrv.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
@Entity(primaryKeys = {"id", "serial_no"}, tableName = "hourly_hrv")
/* loaded from: classes8.dex */
public class HourlyHRV {
    @ColumnInfo(name = "start_time")

    /* renamed from: a  reason: collision with root package name */
    public String f6954a;
    @ColumnInfo(name = "end_time")
    public String b;
    @ColumnInfo(name = "codevalue")
    public List<Double> c;
    @SerializedName("otherData")
    @Ignore
    private OtherDataBean d;
    @ColumnInfo(name = "hrv_avg")
    public double hrv_avg;
    @ColumnInfo(name = "hrv_high")
    public double hrv_high;
    @ColumnInfo(name = "hrv_low")
    public double hrv_low;
    @ColumnInfo(name = "date")
    public String mDate;
    @NonNull
    @ColumnInfo(name = "serial_no")
    public String mac_address;

    /* loaded from: classes8.dex */
    public static class OtherDataBean {
        @SerializedName("codedValues")

        /* renamed from: a  reason: collision with root package name */
        private List<Double> f6955a;

        public OtherDataBean(List<Double> list) {
            this.f6955a = list;
        }

        public List<Double> getCodedValues() {
            return this.f6955a;
        }

        public void setCodedValues(List<Double> list) {
            this.f6955a = list;
        }
    }

    public HourlyHRV() {
        this.c = new ArrayList();
    }

    public List<Double> getCodevalue() {
        return this.c;
    }

    public String getEndTime() {
        return this.b;
    }

    public double getHrv_avg() {
        return this.hrv_avg;
    }

    public double getHrv_high() {
        return this.hrv_high;
    }

    public double getHrv_low() {
        return this.hrv_low;
    }

    public String getId() {
        return this.mDate + HexStringBuilder.DEFAULT_SEPARATOR + this.f6954a;
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
        return this.f6954a;
    }

    public String getmDate() {
        return this.mDate;
    }

    public void setCodevalue(List<Double> list) {
        this.c = list;
    }

    public void setEndTime(String str) {
        this.b = str;
    }

    public void setHrv_avg(double d) {
        this.hrv_avg = d;
    }

    public void setHrv_high(double d) {
        this.hrv_high = d;
    }

    public void setHrv_low(double d) {
        this.hrv_low = d;
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
        this.f6954a = str;
    }

    public void setmDate(String str) {
        this.mDate = str;
    }

    public String toString() {
        return "HourlyHRV{mDate='" + this.mDate + "', startTime='" + this.f6954a + "', endTime='" + this.b + "', codevalue=" + this.c + '}';
    }

    public HourlyHRV(String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        this.c = arrayList;
        this.mDate = str;
        this.f6954a = str2;
        this.b = str3;
        this.c = arrayList;
    }
}
