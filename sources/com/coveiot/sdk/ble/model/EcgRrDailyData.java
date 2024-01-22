package com.coveiot.sdk.ble.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes9.dex */
public class EcgRrDailyData extends DailyData {
    public int ecgData;
    public int rrInterval;
    public TimeLogBeanEcgRr timeLogBean;

    public int getEcgData() {
        return this.ecgData;
    }

    public String getId() {
        String str = getmMacAddress() + getmDate() + getmActivityType();
        this.id = str;
        return str;
    }

    public int getRrInterval() {
        return this.rrInterval;
    }

    public TimeLogBeanEcgRr getTimeLogBean() {
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

    public void setEcgData(int i) {
        this.ecgData = i;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setRrInterval(int i) {
        this.rrInterval = i;
    }

    public void setTimeLogBean(TimeLogBeanEcgRr timeLogBeanEcgRr) {
        this.timeLogBean = timeLogBeanEcgRr;
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
