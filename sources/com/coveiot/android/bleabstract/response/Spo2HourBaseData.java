package com.coveiot.android.bleabstract.response;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
/* loaded from: classes2.dex */
public class Spo2HourBaseData {

    /* renamed from: a  reason: collision with root package name */
    public String f3662a;
    public long b;
    public String c;
    public String d;
    public String e;
    public String f;
    public ArrayList<Integer> mMinuteWiseData = new ArrayList<>();

    public String getDate() {
        try {
            this.f3662a = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new SimpleDateFormat("yyyy-MM-dd").parse(this.f3662a));
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (ParseException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return this.f3662a;
    }

    public String getEndHour() {
        return this.f;
    }

    public String getStartHour() {
        return this.e;
    }

    public long getTimeStamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.b = simpleDateFormat.parse(this.f3662a + HexStringBuilder.DEFAULT_SEPARATOR + getStartHour()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this.b;
    }

    public String getmActivityType() {
        return this.d;
    }

    public String getmMacAddress() {
        return this.c;
    }

    public ArrayList<Integer> getmMinuteWiseData() {
        return this.mMinuteWiseData;
    }

    public void setActivityType(String str) {
        this.d = str;
    }

    public void setDate(String str) {
        this.f3662a = str;
    }

    public void setEndHour(String str) {
        this.f = str;
    }

    public void setMacAddress(String str) {
        this.c = str;
    }

    public void setMinuteWiseData(ArrayList<Integer> arrayList) {
        this.mMinuteWiseData = arrayList;
    }

    public void setStartHour(String str) {
        this.e = str;
    }
}