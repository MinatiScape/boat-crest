package com.coveiot.sdk.ble.api.response;

import java.io.Serializable;
/* loaded from: classes9.dex */
public class SendSOSRes implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public int f7562a;
    public Long b;
    public String c;
    public String d;

    public SendSOSRes(int i, Long l, String str, String str2) {
        this.f7562a = i;
        this.b = l;
        this.c = str;
        this.d = str2;
    }

    public String getContactName() {
        return this.c;
    }

    public String getContactNumber() {
        return this.d;
    }

    public int getEvent() {
        return this.f7562a;
    }

    public Long getUnixTimeStamp() {
        return this.b;
    }

    public void setContactName(String str) {
        this.c = str;
    }

    public void setContactNumber(String str) {
        this.d = str;
    }

    public void setEvent(int i) {
        this.f7562a = i;
    }

    public void setUnixTimeStamp(Long l) {
        this.b = l;
    }

    public String toString() {
        return "SendSOSRes{event=" + this.f7562a + ", unixTimeStamp='" + this.b + "', contactName='" + this.c + "', contactNumber='" + this.d + "'}";
    }
}
