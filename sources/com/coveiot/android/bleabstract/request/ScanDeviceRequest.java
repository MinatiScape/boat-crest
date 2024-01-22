package com.coveiot.android.bleabstract.request;

import android.app.Activity;
import com.coveiot.android.bleabstract.models.BleCommand;
/* loaded from: classes2.dex */
public class ScanDeviceRequest extends BleBaseRequest {
    public String[] f;
    public long g;
    public boolean h;
    public Activity i;
    public int j;
    public boolean k;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public String[] f3516a;
        public long b;
        public Object c;
        public boolean d;
        public Activity e;
        public int f;
        public boolean g;

        public ScanDeviceRequest build() {
            ScanDeviceRequest scanDeviceRequest = new ScanDeviceRequest(this.f3516a, this.b);
            scanDeviceRequest.g = this.b;
            scanDeviceRequest.h = this.d;
            scanDeviceRequest.f = this.f3516a;
            scanDeviceRequest.k = this.g;
            scanDeviceRequest.i = this.e;
            scanDeviceRequest.j = this.f;
            return scanDeviceRequest;
        }

        public Builder setActivity(Activity activity) {
            this.e = activity;
            return this;
        }

        public Builder setId(Object obj) {
            this.c = obj;
            return this;
        }

        public Builder setRequestId(int i) {
            this.f = i;
            return this;
        }

        public Builder setScanDuration(long j) {
            this.b = j;
            return this;
        }

        public Builder setScanFilter(String[] strArr) {
            this.f3516a = strArr;
            return this;
        }

        public Builder setShouldProvideBatchResult(boolean z) {
            this.d = z;
            return this;
        }

        public Builder setSingleDevice(boolean z) {
            this.g = z;
            return this;
        }
    }

    public ScanDeviceRequest(String[] strArr, long j) {
        this.f = strArr;
        this.g = j;
    }

    public Activity getActivity() {
        return this.i;
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public BleCommand getBleCommand() {
        return BleCommand.SCAN;
    }

    public int getReqCode() {
        return this.j;
    }

    public long getScanDuration() {
        return this.g;
    }

    public String[] getScanFilter() {
        return this.f;
    }

    public boolean getScanSingleDevice() {
        return this.k;
    }

    public boolean isShouldProvideBatchResult() {
        return this.h;
    }
}
