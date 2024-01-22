package com.coveiot.covepreferences.data;

import com.blankj.utilcode.constant.PermissionConstants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class OTPResendTimerConfigBean implements Serializable {
    @SerializedName("PHONE_CALL")
    private int phone_call;
    @SerializedName(PermissionConstants.SMS)
    private int sms;
    @SerializedName("WHATSAPP")
    private int whatsapp;

    public OTPResendTimerConfigBean(int i, int i2, int i3) {
        this.sms = 0;
        this.phone_call = 0;
        this.whatsapp = 0;
        this.sms = i;
        this.phone_call = i2;
        this.whatsapp = i3;
    }

    public int getPhone_call() {
        return this.phone_call;
    }

    public int getSms() {
        return this.sms;
    }

    public int getWhatsapp() {
        return this.whatsapp;
    }

    public void setPhone_call(int i) {
        this.phone_call = i;
    }

    public void setSms(int i) {
        this.sms = i;
    }

    public void setWhatsapp(int i) {
        this.whatsapp = i;
    }
}
