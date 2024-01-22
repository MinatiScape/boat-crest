package com.coveiot.android.bleabstract.response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes2.dex */
public class RrDayData extends DayData {

    /* renamed from: a  reason: collision with root package name */
    public int f3653a;
    public RrTimeLogBeanData timeLogBean;

    public String getId() {
        String str = getmMacAddress() + getmDate() + getmActivityType();
        this.id = str;
        return str;
    }

    public int getRrInterval() {
        return this.f3653a;
    }

    public RrTimeLogBeanData getTimeLogBean() {
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

    public void setId(String str) {
        this.id = str;
    }

    public void setRrInterval(int i) {
        this.f3653a = i;
    }

    public void setTimeLogBean(RrTimeLogBeanData rrTimeLogBeanData) {
        this.timeLogBean = rrTimeLogBeanData;
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
