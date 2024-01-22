package com.coveiot.android.bleabstract.response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes2.dex */
public class StressDayData extends DayData {

    /* renamed from: a  reason: collision with root package name */
    public int f3671a;
    public int b;
    public double c;
    public double d;
    public double e;
    public StressTimeLogBeanData timeLogBean;

    public double getAvgStress() {
        return this.c;
    }

    public double getBaselineStress() {
        return this.d;
    }

    public String getId() {
        String str = getmMacAddress() + getmDate() + getmActivityType();
        this.id = str;
        return str;
    }

    public int getMaxStress() {
        return this.b;
    }

    public int getMinStress() {
        return this.f3671a;
    }

    public double getReadinessStress() {
        return this.e;
    }

    public StressTimeLogBeanData getTimeLogBean() {
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

    public void setAvgStress(double d) {
        this.c = d;
    }

    public void setBaselineStress(double d) {
        this.d = d;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setMaxStress(int i) {
        this.b = i;
    }

    public void setMinStress(int i) {
        this.f3671a = i;
    }

    public void setReadinessStress(double d) {
        this.e = d;
    }

    public void setTimeLogBean(StressTimeLogBeanData stressTimeLogBeanData) {
        this.timeLogBean = stressTimeLogBeanData;
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
