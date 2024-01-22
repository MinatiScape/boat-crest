package com.coveiot.android.bleabstract.request;

import android.app.Activity;
/* loaded from: classes2.dex */
public class DeviceDeassociationRequest {

    /* renamed from: a  reason: collision with root package name */
    public String f3485a;
    public Activity b;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f3486a;
        public Activity b;

        public DeviceDeassociationRequest build() {
            DeviceDeassociationRequest deviceDeassociationRequest = new DeviceDeassociationRequest(this.f3486a, this.b);
            deviceDeassociationRequest.b = this.b;
            deviceDeassociationRequest.f3485a = this.f3486a;
            return deviceDeassociationRequest;
        }

        public Builder setActivity(Activity activity) {
            this.b = activity;
            return this;
        }

        public Builder setScanFilter(String str) {
            this.f3486a = str;
            return this;
        }
    }

    public DeviceDeassociationRequest(String str, Activity activity) {
        this.f3485a = str;
        this.b = activity;
    }

    public Activity getActivity() {
        return this.b;
    }

    public String getMacAddress() {
        return this.f3485a;
    }
}
