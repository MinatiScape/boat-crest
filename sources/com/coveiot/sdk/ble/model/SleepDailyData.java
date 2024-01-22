package com.coveiot.sdk.ble.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes9.dex */
public class SleepDailyData {
    private String id;
    public String mActivityType;
    public int mAwakeTime;
    public String mDate;
    public int mDeepSleep;
    public int mLightSleep;
    public String mMacAddress;
    public int mRemSleep;
    public int mTotalSleep;
    public int mUnSleep;
    public TimeLogBeanSleep timeLogBean;

    public String getId() {
        String str = getmMacAddress() + getmDate() + getmActivityType();
        this.id = str;
        return str;
    }

    public TimeLogBeanSleep getTimeLogBean() {
        return this.timeLogBean;
    }

    public String getmActivityType() {
        return this.mActivityType;
    }

    public int getmAwakeTime() {
        return this.mAwakeTime;
    }

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

    public int getmDeepSleep() {
        return this.mDeepSleep;
    }

    public int getmLightSleep() {
        return this.mLightSleep;
    }

    public String getmMacAddress() {
        return this.mMacAddress;
    }

    public int getmRemSleep() {
        return this.mRemSleep;
    }

    public int getmTotalSleep() {
        return this.mTotalSleep;
    }

    public int getmUnSleep() {
        return this.mUnSleep;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setTimeLogBean(TimeLogBeanSleep timeLogBeanSleep) {
        this.timeLogBean = timeLogBeanSleep;
    }

    public void setmActivityType(String str) {
        this.mActivityType = str;
    }

    public void setmAwakeTime(int i) {
        this.mAwakeTime = i;
    }

    public void setmDate(String str) {
        this.mDate = str;
    }

    public void setmDeepSleep(int i) {
        this.mDeepSleep = i;
    }

    public void setmLightSleep(int i) {
        this.mLightSleep = i;
    }

    public void setmMacAddress(String str) {
        this.mMacAddress = str;
    }

    public void setmRemSleep(int i) {
        this.mRemSleep = i;
    }

    public void setmTotalSleep(int i) {
        this.mTotalSleep = i;
    }

    public void setmUnSleep(int i) {
        this.mUnSleep = i;
    }
}
