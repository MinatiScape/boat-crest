package com.coveiot.android.bleabstract.response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes2.dex */
public class TemperatureDayData extends DayData {

    /* renamed from: a  reason: collision with root package name */
    public double f3675a;
    public double b;
    public double c;
    public double d;
    public TemperatureTimeLogBeanData timeLogBean;

    public double getAvgTemperature() {
        return this.d;
    }

    public String getId() {
        String str = getmMacAddress() + getmDate() + getmActivityType();
        this.id = str;
        return str;
    }

    public double getMaxTemperature() {
        return this.c;
    }

    public double getMinTemperature() {
        return this.b;
    }

    public TemperatureTimeLogBeanData getTimeLogBean() {
        return this.timeLogBean;
    }

    public double gethTemperature() {
        return this.f3675a;
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

    public void setAvgTemperature(double d) {
        this.d = d;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setMaxTemperature(double d) {
        this.c = d;
    }

    public void setMinTemperature(double d) {
        this.b = d;
    }

    public void setTimeLogBean(TemperatureTimeLogBeanData temperatureTimeLogBeanData) {
        this.timeLogBean = temperatureTimeLogBeanData;
    }

    public void sethTemperature(int i) {
        this.f3675a = i;
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
