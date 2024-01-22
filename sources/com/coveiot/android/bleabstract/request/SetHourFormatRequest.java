package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class SetHourFormatRequest extends BleBaseRequest {
    public boolean f;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3533a;

        public Builder(boolean z) {
            this.f3533a = z;
        }

        public SetHourFormatRequest build() {
            SetHourFormatRequest setHourFormatRequest = new SetHourFormatRequest();
            setHourFormatRequest.f = this.f3533a;
            return setHourFormatRequest;
        }
    }

    public boolean is12HourFormat() {
        return this.f;
    }
}
