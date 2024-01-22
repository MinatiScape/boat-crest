package com.coveiot.sdk.ble.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes9.dex */
public class TemperatureDailyData extends DailyData {
    public double avgTemperature;
    private String id;
    public String mActivityType;
    public String mDate;
    public String mMacAddress;
    public double maxTemperature;
    public double minTemperature;
    public TimeLogBeanTemperature timeLogBean;

    public double getAvgTemperature() {
        return this.avgTemperature;
    }

    public String getId() {
        String str = getmMacAddress() + getmDate() + getmActivityType();
        this.id = str;
        return str;
    }

    public double getMaxTemperature() {
        return this.maxTemperature;
    }

    public double getMinTemperature() {
        return this.minTemperature;
    }

    public TimeLogBeanTemperature getTimeLogBean() {
        return this.timeLogBean;
    }

    @Override // com.coveiot.sdk.ble.model.DailyData
    public String getmActivityType() {
        return this.mActivityType;
    }

    @Override // com.coveiot.sdk.ble.model.DailyData
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

    @Override // com.coveiot.sdk.ble.model.DailyData
    public String getmMacAddress() {
        return this.mMacAddress;
    }

    public void setAvgTemperature(double d) {
        this.avgTemperature = d;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setMaxTemperature(double d) {
        this.maxTemperature = d;
    }

    public void setMinTemperature(double d) {
        this.minTemperature = d;
    }

    public void setTimeLogBean(TimeLogBeanTemperature timeLogBeanTemperature) {
        this.timeLogBean = timeLogBeanTemperature;
    }

    @Override // com.coveiot.sdk.ble.model.DailyData
    public void setmActivityType(String str) {
        this.mActivityType = str;
    }

    @Override // com.coveiot.sdk.ble.model.DailyData
    public void setmDate(String str) {
        this.mDate = str;
    }

    @Override // com.coveiot.sdk.ble.model.DailyData
    public void setmMacAddress(String str) {
        this.mMacAddress = str;
    }
}
