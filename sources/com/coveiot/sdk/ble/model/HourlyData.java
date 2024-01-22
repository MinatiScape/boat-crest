package com.coveiot.sdk.ble.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
/* loaded from: classes9.dex */
public class HourlyData {
    private String mActivityType;
    private String mDate;
    private String mEndHour;
    private String mMacAddress;
    public ArrayList<Integer> mMinuteWiseData = new ArrayList<>();
    private String mStartHour;
    private long timeStamp;

    public String getDate() {
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

    public String getEndHour() {
        return this.mEndHour;
    }

    public String getStartHour() {
        return this.mStartHour;
    }

    public long getTimeStamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        try {
            this.timeStamp = simpleDateFormat.parse(this.mDate + HexStringBuilder.DEFAULT_SEPARATOR + getStartHour()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this.timeStamp;
    }

    public String getmActivityType() {
        return this.mActivityType;
    }

    public String getmMacAddress() {
        return this.mMacAddress;
    }

    public ArrayList<Integer> getmMinuteWiseData() {
        return this.mMinuteWiseData;
    }

    public void setActivityType(String str) {
        this.mActivityType = str;
    }

    public void setDate(String str) {
        this.mDate = str;
    }

    public void setEndHour(String str) {
        this.mEndHour = str;
    }

    public void setMacAddress(String str) {
        this.mMacAddress = str;
    }

    public void setMinuteWiseData(ArrayList<Integer> arrayList) {
        this.mMinuteWiseData = arrayList;
    }

    public void setStartHour(String str) {
        this.mStartHour = str;
    }

    public String toString() {
        return "HourlyData{mDate='" + this.mDate + "', timeStamp=" + this.timeStamp + ", mMacAddress='" + this.mMacAddress + "', mActivityType='" + this.mActivityType + "', mStartHour='" + this.mStartHour + "', mEndHour='" + this.mEndHour + "', mMinuteWiseData=" + this.mMinuteWiseData + '}';
    }
}
