package com.coveiot.sdk.ble.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes9.dex */
public class Spo2DailyData extends DailyData {
    public double avgSpo2;
    private String id;
    public String mActivityType;
    public String mDate;
    public String mMacAddress;
    public int maxSpo2;
    public int minSpo2;
    public TimeLogBeanSpo2 timeLogBean;

    public double getAvgSpo2() {
        return this.avgSpo2;
    }

    public String getId() {
        String str = getmMacAddress() + getmDate() + getmActivityType();
        this.id = str;
        return str;
    }

    public int getMaxSpo2() {
        return this.maxSpo2;
    }

    public int getMinSpo2() {
        return this.minSpo2;
    }

    public TimeLogBeanSpo2 getTimeLogBean() {
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

    public void setAvgSpo2(double d) {
        this.avgSpo2 = d;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setMaxSpo2(int i) {
        this.maxSpo2 = i;
    }

    public void setMinSpo2(int i) {
        this.minSpo2 = i;
    }

    public void setTimeLogBean(TimeLogBeanSpo2 timeLogBeanSpo2) {
        this.timeLogBean = timeLogBeanSpo2;
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
