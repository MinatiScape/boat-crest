package com.coveiot.sdk.ble.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes9.dex */
public class HrBpDailyData extends DailyData {
    public int diastolicBp;
    public int heartRate;
    private String id;
    public int rrValue;
    public int systolicBp;
    public TimeLogBeansHrBp timeLogBean;

    public int getDiastolicBp() {
        return this.diastolicBp;
    }

    public int getHeartRate() {
        return this.heartRate;
    }

    public String getId() {
        String str = getmMacAddress() + getmDate() + getmActivityType();
        this.id = str;
        return str;
    }

    public int getRrValue() {
        return this.rrValue;
    }

    public int getSystolicBp() {
        return this.systolicBp;
    }

    public TimeLogBeansHrBp getTimeLogBean() {
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

    public void setDiastolicBp(int i) {
        this.diastolicBp = i;
    }

    public void setHeartRate(int i) {
        this.heartRate = i;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setRrValue(int i) {
        this.rrValue = i;
    }

    public void setSystolicBp(int i) {
        this.systolicBp = i;
    }

    public void setTimeLogBean(TimeLogBeansHrBp timeLogBeansHrBp) {
        this.timeLogBean = timeLogBeansHrBp;
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
