package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class SetSocialDistanceScanSettingsRequest extends BleBaseRequest {
    public int f;
    public int g;
    public int h;
    public int i;
    public String j;
    public int k;
    public int l;
    public int m;
    public int n;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f3550a;
        public int b;
        public int c;
        public int d;
        public int e;
        public String f;
        public int g = -1;
        public int h;
        public int i;
        public int j;

        public static Builder Builder() {
            return new Builder();
        }

        public SetSocialDistanceScanSettingsRequest build() {
            SetSocialDistanceScanSettingsRequest setSocialDistanceScanSettingsRequest = new SetSocialDistanceScanSettingsRequest();
            setSocialDistanceScanSettingsRequest.f = this.b;
            setSocialDistanceScanSettingsRequest.g = this.c;
            setSocialDistanceScanSettingsRequest.h = this.d;
            setSocialDistanceScanSettingsRequest.i = this.e;
            setSocialDistanceScanSettingsRequest.j = this.f;
            setSocialDistanceScanSettingsRequest.k = this.g;
            setSocialDistanceScanSettingsRequest.l = this.h;
            setSocialDistanceScanSettingsRequest.m = this.i;
            setSocialDistanceScanSettingsRequest.n = this.j;
            return setSocialDistanceScanSettingsRequest;
        }

        public Builder setAddressFilter(String str) {
            this.f = str;
            return this;
        }

        public Builder setBandAlert(int i) {
            this.d = i;
            return this;
        }

        public void setId(Object obj) {
            this.f3550a = obj;
        }

        public Builder setRssiFilter(int i) {
            this.e = i;
            return this;
        }

        public Builder setScanInterval(int i) {
            this.h = i;
            return this;
        }

        public Builder setScanPeriod(int i) {
            this.b = i;
            return this;
        }

        public Builder setScanTimeout(int i) {
            this.j = i;
            return this;
        }

        public Builder setScanUnit(int i) {
            this.c = i;
            return this;
        }

        public Builder setScanWindow(int i) {
            this.i = i;
            return this;
        }

        public Builder setUuidFilter(int i) {
            this.g = i;
            return this;
        }
    }

    public String getAddressFilter() {
        return this.j;
    }

    public int getBandAlert() {
        return this.h;
    }

    public int getRssiFilter() {
        return this.i;
    }

    public int getScanInterval() {
        return this.l;
    }

    public int getScanPeriod() {
        return this.f;
    }

    public int getScanTimeout() {
        return this.n;
    }

    public int getScanUnit() {
        return this.g;
    }

    public int getScanWindow() {
        return this.m;
    }

    public int getUuidFilter() {
        return this.k;
    }
}
