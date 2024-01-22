package com.coveiot.covedb.temperature.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
@Entity(primaryKeys = {"id", "serial_no"}, tableName = "hourly_temperature")
/* loaded from: classes8.dex */
public class HourlyTemperature {
    @ColumnInfo(name = "start_time")

    /* renamed from: a  reason: collision with root package name */
    public String f6988a;
    @ColumnInfo(name = "end_time")
    public String b;
    @ColumnInfo(name = "codevalue")
    public List<Double> c;
    @SerializedName("otherData")
    @Ignore
    private OtherDataBean d;
    @ColumnInfo(name = "date")
    public String mDate;
    @NonNull
    @ColumnInfo(name = "serial_no")
    public String mac_address;
    @ColumnInfo(name = "temperature_avg")
    public double temperature_avg;
    @ColumnInfo(name = "temperature_high")
    public double temperature_high;
    @ColumnInfo(name = "temperature_low")
    public double temperature_low;

    /* loaded from: classes8.dex */
    public static class OtherDataBean {
        @SerializedName("codedValues")

        /* renamed from: a  reason: collision with root package name */
        private List<Double> f6989a;

        public OtherDataBean(List<Double> list) {
            this.f6989a = list;
        }

        public List<Double> getCodedValues() {
            return this.f6989a;
        }

        public void setCodedValues(List<Double> list) {
            this.f6989a = list;
        }
    }

    public HourlyTemperature() {
        this.c = new ArrayList();
    }

    public List<Double> getCodevalue() {
        return this.c;
    }

    public String getEndTime() {
        return this.b;
    }

    public String getId() {
        return this.mDate + HexStringBuilder.DEFAULT_SEPARATOR + this.f6988a;
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
        return this.f6988a;
    }

    public double getTemperature_avg() {
        return this.temperature_avg;
    }

    public double getTemperature_high() {
        return this.temperature_high;
    }

    public double getTemperature_low() {
        return this.temperature_low;
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

    public void setId(String str) {
    }

    public void setMacAddress(String str) {
        this.mac_address = str;
    }

    public void setOtherData(OtherDataBean otherDataBean) {
        this.d = otherDataBean;
    }

    public void setStartTime(String str) {
        this.f6988a = str;
    }

    public void setTemperature_avg(double d) {
        this.temperature_avg = d;
    }

    public void setTemperature_high(double d) {
        this.temperature_high = d;
    }

    public void setTemperature_low(double d) {
        this.temperature_low = d;
    }

    public void setmDate(String str) {
        this.mDate = str;
    }

    public String toString() {
        return "HourlyTemperature{mDate='" + this.mDate + "', startTime='" + this.f6988a + "', endTime='" + this.b + "', codevalue=" + this.c + '}';
    }

    public HourlyTemperature(String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        this.c = arrayList;
        this.mDate = str;
        this.f6988a = str2;
        this.b = str3;
        this.c = arrayList;
    }
}
