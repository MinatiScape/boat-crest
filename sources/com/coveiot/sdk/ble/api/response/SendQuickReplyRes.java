package com.coveiot.sdk.ble.api.response;

import androidx.annotation.NonNull;
import java.io.Serializable;
/* loaded from: classes9.dex */
public class SendQuickReplyRes implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public int f7561a;
    public String b;

    public SendQuickReplyRes(int i) {
        this.f7561a = i;
    }

    public int getIndex() {
        return this.f7561a;
    }

    public String getMessage() {
        return this.b;
    }

    public void setIndex(int i) {
        this.f7561a = i;
    }

    public void setMessage(String str) {
        this.b = str;
    }

    @NonNull
    public String toString() {
        return "SendQuickReplyRes{index=" + this.f7561a + "message=" + this.b + '}';
    }

    public SendQuickReplyRes(int i, String str) {
        this.f7561a = i;
        this.b = str;
    }
}
