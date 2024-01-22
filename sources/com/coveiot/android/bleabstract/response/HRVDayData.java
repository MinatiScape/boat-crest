package com.coveiot.android.bleabstract.response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes2.dex */
public class HRVDayData extends DayData {

    /* renamed from: a  reason: collision with root package name */
    public double f3630a;
    public double b;
    public double c;
    public double d;
    public double e;
    public HRVTimeLogBeanData f;

    public double getAvgHrv() {
        return this.c;
    }

    public double getBaselineHrv() {
        return this.d;
    }

    public HRVTimeLogBeanData getHrvTimeLogBeanData() {
        return this.f;
    }

    public String getId() {
        String str = getmMacAddress() + getmDate() + getmActivityType();
        this.id = str;
        return str;
    }

    public double getMaxHrv() {
        return this.b;
    }

    public double getMinHrv() {
        return this.f3630a;
    }

    public double getReadinessHrv() {
        return this.e;
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

    public void setAvgHrv(double d) {
        this.c = d;
    }

    public void setBaselineHrv(double d) {
        this.d = d;
    }

    public void setHrvTimeLogBeanData(HRVTimeLogBeanData hRVTimeLogBeanData) {
        this.f = hRVTimeLogBeanData;
    }

    public void setMaxHrv(double d) {
        this.b = d;
    }

    public void setMinHrv(double d) {
        this.f3630a = d;
    }

    public void setReadinessHrv(double d) {
        this.e = d;
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
