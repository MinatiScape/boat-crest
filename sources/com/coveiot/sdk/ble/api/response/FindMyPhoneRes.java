package com.coveiot.sdk.ble.api.response;

import com.coveiot.sdk.ble.api.model.FindMyPhoneState;
import java.io.Serializable;
/* loaded from: classes9.dex */
public class FindMyPhoneRes implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public FindMyPhoneState f7554a;

    public FindMyPhoneRes(FindMyPhoneState findMyPhoneState) {
        this.f7554a = findMyPhoneState;
    }

    public String toString() {
        return "FindMyPhoneRes{state=" + this.f7554a.name() + '}';
    }
}
