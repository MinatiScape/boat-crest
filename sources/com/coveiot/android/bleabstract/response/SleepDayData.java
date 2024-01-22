package com.coveiot.android.bleabstract.response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes2.dex */
public class SleepDayData extends DayData {
    public int mAwakeTime;
    public int mBreathQuality;
    public int mDeepSleep;
    public int mLightSleep;
    public int mSleepScore;
    public int mTotalSleep;
    public int mUnSleep;
    public int maxHr;
    public int maxStress;
    public int minHr;
    public int minStress;
    public SleepTimeLogBeanData timeLogBean;

    public String getId() {
        String str = getmMacAddress() + getmDate() + getmActivityType();
        this.id = str;
        return str;
    }

    public SleepTimeLogBeanData getTimeLogBean() {
        return this.timeLogBean;
    }

    @Override // com.coveiot.android.bleabstract.response.DayData
    public String getmActivityType() {
        return this.mActivityType;
    }

    public int getmAwakeTime() {
        return this.mAwakeTime;
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

    public int getmDeepSleep() {
        return this.mDeepSleep;
    }

    public int getmLightSleep() {
        return this.mLightSleep;
    }

    @Override // com.coveiot.android.bleabstract.response.DayData
    public String getmMacAddress() {
        return this.mMacAddress;
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

    public void setTimeLogBean(SleepTimeLogBeanData sleepTimeLogBeanData) {
        this.timeLogBean = sleepTimeLogBeanData;
    }

    @Override // com.coveiot.android.bleabstract.response.DayData
    public void setmActivityType(String str) {
        this.mActivityType = str;
    }

    public void setmAwakeTime(int i) {
        this.mAwakeTime = i;
    }

    @Override // com.coveiot.android.bleabstract.response.DayData
    public void setmDate(String str) {
        this.mDate = str;
    }

    public void setmDeepSleep(int i) {
        this.mDeepSleep = i;
    }

    public void setmLightSleep(int i) {
        this.mLightSleep = i;
    }

    @Override // com.coveiot.android.bleabstract.response.DayData
    public void setmMacAddress(String str) {
        this.mMacAddress = str;
    }

    public void setmTotalSleep(int i) {
        this.mTotalSleep = i;
    }

    public void setmUnSleep(int i) {
        this.mUnSleep = i;
    }

    public String toString() {
        return "SleepDayData{id='" + this.id + "', mDate='" + this.mDate + "', mMacAddress='" + this.mMacAddress + "', mActivityType='" + this.mActivityType + "', mTotalSleep=" + this.mTotalSleep + ", mDeepSleep=" + this.mDeepSleep + ", mLightSleep=" + this.mLightSleep + ", mAwakeTime=" + this.mAwakeTime + ", mUnSleep=" + this.mUnSleep + ", sleepscore=" + this.mSleepScore + ", maxHr=" + this.maxHr + ", minHr=" + this.minHr + ", maxStress=" + this.maxStress + ", minStress=" + this.minStress + ", timeLogBean=" + this.timeLogBean + '}';
    }
}
