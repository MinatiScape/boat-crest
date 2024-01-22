package com.coveiot.sdk.ble.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes9.dex */
public class StepsDailyData extends DailyData {
    private String id;
    public String mActivityType;
    public double mCalories;
    public String mDate;
    public double mDistance;
    public String mMacAddress;
    public int mSteps;
    public TimeLogBeanSteps timeLogBean;

    public String getId() {
        String str = getmMacAddress() + getmDate() + getmActivityType();
        this.id = str;
        return str;
    }

    public TimeLogBeanSteps getTimeLogBean() {
        return this.timeLogBean;
    }

    @Override // com.coveiot.sdk.ble.model.DailyData
    public String getmActivityType() {
        return this.mActivityType;
    }

    public double getmCalories() {
        return this.mCalories;
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

    public double getmDistance() {
        return this.mDistance;
    }

    @Override // com.coveiot.sdk.ble.model.DailyData
    public String getmMacAddress() {
        return this.mMacAddress;
    }

    public int getmSteps() {
        return this.mSteps;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setTimeLogBean(TimeLogBeanSteps timeLogBeanSteps) {
        this.timeLogBean = timeLogBeanSteps;
    }

    @Override // com.coveiot.sdk.ble.model.DailyData
    public void setmActivityType(String str) {
        this.mActivityType = str;
    }

    public void setmCalories(double d) {
        this.mCalories = d;
    }

    @Override // com.coveiot.sdk.ble.model.DailyData
    public void setmDate(String str) {
        this.mDate = str;
    }

    public void setmDistance(double d) {
        this.mDistance = d;
    }

    @Override // com.coveiot.sdk.ble.model.DailyData
    public void setmMacAddress(String str) {
        this.mMacAddress = str;
    }

    public void setmSteps(int i) {
        this.mSteps = i;
    }
}
