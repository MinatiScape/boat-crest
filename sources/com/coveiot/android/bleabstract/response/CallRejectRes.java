package com.coveiot.android.bleabstract.response;

import java.io.Serializable;
/* loaded from: classes2.dex */
public class CallRejectRes implements Serializable {
    public boolean shouldReject;

    public CallRejectRes(boolean z) {
        this.shouldReject = z;
    }

    public String toString() {
        return "CallRejectRes{shouldReject=" + this.shouldReject + '}';
    }
}
