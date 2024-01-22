package com.coveiot.android.bleabstract.response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes2.dex */
public class RunDayData extends DayData {

    /* renamed from: a  reason: collision with root package name */
    public String f3656a;
    public String mActivityType;
    public double mCalories;
    public String mDate;
    public double mDistance;
    public String mMacAddress;
    public int mSteps;
    public RunTimeLogBeanData timeLogBean;

    public String getId() {
        String str = getmMacAddress() + getmDate() + getmActivityType();
        this.f3656a = str;
        return str;
    }

    public RunTimeLogBeanData getTimeLogBean() {
        return this.timeLogBean;
    }

    @Override // com.coveiot.android.bleabstract.response.DayData
    public String getmActivityType() {
        return this.mActivityType;
    }

    public double getmCalories() {
        return this.mCalories;
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

    public double getmDistance() {
        return this.mDistance;
    }

    @Override // com.coveiot.android.bleabstract.response.DayData
    public String getmMacAddress() {
        return this.mMacAddress;
    }

    public int getmSteps() {
        return this.mSteps;
    }

    public void setId(String str) {
        this.f3656a = str;
    }

    public void setTimeLogBean(RunTimeLogBeanData runTimeLogBeanData) {
        this.timeLogBean = runTimeLogBeanData;
    }

    @Override // com.coveiot.android.bleabstract.response.DayData
    public void setmActivityType(String str) {
        this.mActivityType = str;
    }

    public void setmCalories(double d) {
        this.mCalories = d;
    }

    @Override // com.coveiot.android.bleabstract.response.DayData
    public void setmDate(String str) {
        this.mDate = str;
    }

    public void setmDistance(double d) {
        this.mDistance = d;
    }

    @Override // com.coveiot.android.bleabstract.response.DayData
    public void setmMacAddress(String str) {
        this.mMacAddress = str;
    }

    public void setmSteps(int i) {
        this.mSteps = i;
    }
}
