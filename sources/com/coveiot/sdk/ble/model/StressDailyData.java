package com.coveiot.sdk.ble.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes9.dex */
public class StressDailyData extends DailyData {
    public double avgHrv;
    public double avgStress;
    public double baselineHrv;
    public double baselineStress;
    public double maxHrv;
    public double maxStress;
    public double minHrv;
    public double minStress;
    public double readinessHrv;
    public double readinessStress;
    public TimeLogBeanStress timeLogBean;

    public double getAvgHrv() {
        return this.avgHrv;
    }

    public double getAvgStress() {
        return this.avgStress;
    }

    public double getBaselineHrv() {
        return this.baselineHrv;
    }

    public double getBaselineStress() {
        return this.baselineStress;
    }

    public String getId() {
        String str = getmMacAddress() + getmDate() + getmActivityType();
        this.id = str;
        return str;
    }

    public double getMaxHrv() {
        return this.maxHrv;
    }

    public double getMaxStress() {
        return this.maxStress;
    }

    public double getMinHrv() {
        return this.minHrv;
    }

    public double getMinStress() {
        return this.minStress;
    }

    public double getReadinessHrv() {
        return this.readinessHrv;
    }

    public double getReadinessStress() {
        return this.readinessStress;
    }

    public TimeLogBeanStress getTimeLogBean() {
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

    public void setAvgHrv(double d) {
        this.avgHrv = d;
    }

    public void setAvgStress(double d) {
        this.avgStress = d;
    }

    public void setBaselineHrv(double d) {
        this.baselineHrv = d;
    }

    public void setBaselineStress(double d) {
        this.baselineStress = d;
    }

    public void setMaxHrv(double d) {
        this.maxHrv = d;
    }

    public void setMaxStress(double d) {
        this.maxStress = d;
    }

    public void setMinHrv(double d) {
        this.minHrv = d;
    }

    public void setMinStress(double d) {
        this.minStress = d;
    }

    public void setReadinessHrv(double d) {
        this.readinessHrv = d;
    }

    public void setReadinessStress(double d) {
        this.readinessStress = d;
    }

    public void setTimeLogBean(TimeLogBeanStress timeLogBeanStress) {
        this.timeLogBean = timeLogBeanStress;
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
