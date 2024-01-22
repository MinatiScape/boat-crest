package com.coveiot.sdk.ble.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes9.dex */
public class DailyData {
    public String id;
    public String mActivityType;
    public String mDate;
    public String mMacAddress;

    public String getmActivityType() {
        return this.mActivityType;
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

    public String getmMacAddress() {
        return this.mMacAddress;
    }

    public void setmActivityType(String str) {
        this.mActivityType = str;
    }

    public void setmDate(String str) {
        this.mDate = str;
    }

    public void setmMacAddress(String str) {
        this.mMacAddress = str;
    }
}
