package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class SetScreenTimeOutRequest extends BleBaseRequest {
    public int f;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3547a;

        public Builder(int i) {
            this.f3547a = i;
        }

        public SetScreenTimeOutRequest build() {
            SetScreenTimeOutRequest setScreenTimeOutRequest = new SetScreenTimeOutRequest();
            setScreenTimeOutRequest.f = this.f3547a;
            return setScreenTimeOutRequest;
        }
    }

    public int getScreenTimeOut() {
        return this.f;
    }
}
