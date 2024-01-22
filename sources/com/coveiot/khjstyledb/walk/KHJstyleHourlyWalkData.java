package com.coveiot.khjstyledb.walk;

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
public class KHJstyleHourlyWalkData {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public String f7129a;
    @ColumnInfo(name = "start_time")
    public String b;
    @ColumnInfo(name = "end_time")
    public String c;
    @ColumnInfo(name = "interval_value")
    public int d;
    public ArrayList<Integer> e;
    @ColumnInfo(name = "calories")
    public int f;
    @ColumnInfo(name = "distance")
    public int g;
    @SerializedName("otherData")
    @Ignore
    private OtherDataBean h;
    @ColumnInfo(name = "date")
    public String mDate;
    @NonNull
    @ColumnInfo(name = "serial_no")
    public String mac_address;

    /* loaded from: classes8.dex */
    public static class OtherDataBean {
        @SerializedName("codedValues")

        /* renamed from: a  reason: collision with root package name */
        private List<Integer> f7130a;

        public OtherDataBean(List<Integer> list) {
            this.f7130a = list;
        }

        public List<Integer> getCodedValues() {
            return this.f7130a;
        }

        public void setCodedValues(List<Integer> list) {
            this.f7130a = list;
        }
    }

    public KHJstyleHourlyWalkData() {
        this.e = new ArrayList<>();
    }

    public int getCalories() {
        return this.f;
    }

    public ArrayList<Integer> getCodevalue() {
        return this.e;
    }

    public int getDistance() {
        return this.g;
    }

    public String getEndTime() {
        return this.c;
    }

    public String getId() {
        String str = this.mDate + HexStringBuilder.DEFAULT_SEPARATOR + this.b;
        this.f7129a = str;
        return str;
    }

    public int getIntervelValue() {
        return this.d;
    }

    public String getMacAddress() {
        return this.mac_address;
    }

    public OtherDataBean getOtherData() {
        OtherDataBean otherDataBean = new OtherDataBean(this.e);
        this.h = otherDataBean;
        return otherDataBean;
    }

    public String getStartTime() {
        return this.b;
    }

    public String getmDate() {
        return this.mDate;
    }

    public void setCalories(int i) {
        this.f = i;
    }

    public void setCodevalue(ArrayList<Integer> arrayList) {
        this.e = arrayList;
    }

    public void setDistance(int i) {
        this.g = i;
    }

    public void setEndTime(String str) {
        this.c = str;
    }

    public void setId(String str) {
        this.f7129a = str;
    }

    public void setIntervelValue(int i) {
        this.d = i;
    }

    public void setMacAddress(String str) {
        this.mac_address = str;
    }

    public void setOtherData(OtherDataBean otherDataBean) {
        this.h = otherDataBean;
    }

    public void setStartTime(String str) {
        this.b = str;
    }

    public void setmDate(String str) {
        this.mDate = str;
    }

    public String toString() {
        return "KHJstyleHourlyWalkData{mDate='" + this.mDate + "', startTime='" + this.b + "', endTime='" + this.c + "', intervelValue=" + this.d + ", codevalue=" + this.e + '}';
    }

    public KHJstyleHourlyWalkData(String str, String str2, String str3, ArrayList<Integer> arrayList, int i) {
        this.e = new ArrayList<>();
        this.mDate = str;
        this.b = str2;
        this.c = str3;
        this.d = i;
        this.e = arrayList;
    }
}
