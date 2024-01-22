package com.coveiot.sdk.ble.api.response;

import java.io.Serializable;
/* loaded from: classes9.dex */
public class SendPersonalizedMessageRes implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public int f7560a;
    public int b;

    public SendPersonalizedMessageRes(int i) {
        this.f7560a = i;
    }

    public int getButtonAction() {
        return this.b;
    }

    public int getButtonId() {
        return this.f7560a;
    }

    public void setButtonAction(int i) {
        this.b = i;
    }

    public void setButtonId(int i) {
        this.f7560a = i;
    }

    public String toString() {
        return "SendPersonalizedMessageRes{buttonId= " + this.f7560a + ", buttonAction= " + this.b + '}';
    }

    public SendPersonalizedMessageRes(int i, int i2) {
        this.f7560a = i;
        this.b = i2;
    }
}
