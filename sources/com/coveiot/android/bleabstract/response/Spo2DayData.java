package com.coveiot.android.bleabstract.response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes2.dex */
public class Spo2DayData extends DayData {

    /* renamed from: a  reason: collision with root package name */
    public int f3661a;
    public int b;
    public int c;
    public double d;
    public Spo2TimeLogBeanData timeLogBean;

    public double getAvgSpo2() {
        return this.d;
    }

    public String getId() {
        String str = getmMacAddress() + getmDate() + getmActivityType();
        this.id = str;
        return str;
    }

    public int getMaxSPo2() {
        return this.c;
    }

    public int getMinSpo2() {
        return this.b;
    }

    public double getSpo2() {
        return this.f3661a;
    }

    public Spo2TimeLogBeanData getTimeLogBean() {
        return this.timeLogBean;
    }

    @Override // com.coveiot.android.bleabstract.response.DayData
    public String getmActivityType() {
        return this.mActivityType;
    }

    @Override // com.coveiot.android.bleabstract.response.DayData
    public String getmDate() {
        try {
            this.mDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new SimpleDateFormat("yyyy-MM-dd").parse(this.mDate));
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (ParseException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return this.mDate;
    }

    @Override // com.coveiot.android.bleabstract.response.DayData
    public String getmMacAddress() {
        return this.mMacAddress;
    }

    public void setAvgSpo2(double d) {
        this.d = d;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setMaxSPo2(int i) {
        this.c = i;
    }

    public void setMinSpo2(int i) {
        this.b = i;
    }

    public void setSpo2(int i) {
        this.f3661a = i;
    }

    public void setTimeLogBean(Spo2TimeLogBeanData spo2TimeLogBeanData) {
        this.timeLogBean = spo2TimeLogBeanData;
    }

    @Override // com.coveiot.android.bleabstract.response.DayData
    public void setmActivityType(String str) {
        this.mActivityType = str;
    }

    @Override // com.coveiot.android.bleabstract.response.DayData
    public void setmDate(String str) {
        this.mDate = str;
    }

    @Override // com.coveiot.android.bleabstract.response.DayData
    public void setmMacAddress(String str) {
        this.mMacAddress = str;
    }
}
