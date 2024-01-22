package com.coveiot.android.bleabstract.response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes2.dex */
public class HeartRateDayData extends DayData {

    /* renamed from: a  reason: collision with root package name */
    public int f3634a;
    public int b;
    public int c;
    public int d;
    public HeartRateTimeLogBeanData timeLogBean;

    public int getAvgHeartRate() {
        return this.d;
    }

    public int getHheartRate() {
        return this.f3634a;
    }

    public String getId() {
        String str = getmMacAddress() + getmDate() + getmActivityType();
        this.id = str;
        return str;
    }

    public int getMaxHeartRate() {
        return this.c;
    }

    public int getMinHeartRate() {
        return this.b;
    }

    public HeartRateTimeLogBeanData getTimeLogBean() {
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

    public void setAvgHeartRate(int i) {
        this.d = i;
    }

    public void setHheartRate(int i) {
        this.f3634a = i;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setMaxHeartRate(int i) {
        this.c = i;
    }

    public void setMinHeartRate(int i) {
        this.b = i;
    }

    public void setTimeLogBean(HeartRateTimeLogBeanData heartRateTimeLogBeanData) {
        this.timeLogBean = heartRateTimeLogBeanData;
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
