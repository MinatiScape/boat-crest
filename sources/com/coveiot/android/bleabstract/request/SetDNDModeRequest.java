package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
/* loaded from: classes2.dex */
public class SetDNDModeRequest extends BleBaseRequest {
    public boolean f;
    public boolean g;
    public boolean h;
    public int i;
    public int j;
    public int k;
    public int l;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3524a;
        public boolean b;
        public boolean c;
        public int d;
        public int e;
        public int f;
        public int g;

        public Builder(boolean z, int i, int i2, int i3, int i4) {
            this.f3524a = z;
            this.d = i;
            this.e = i2;
            this.f = i3;
            this.g = i4;
        }

        public SetDNDModeRequest build() {
            SetDNDModeRequest setDNDModeRequest = new SetDNDModeRequest(this.f3524a, this.d, this.e, this.f, this.g);
            setDNDModeRequest.f = this.f3524a;
            setDNDModeRequest.h = this.c;
            setDNDModeRequest.g = this.b;
            setDNDModeRequest.i = this.d;
            setDNDModeRequest.j = this.e;
            setDNDModeRequest.k = this.f;
            setDNDModeRequest.l = this.g;
            return setDNDModeRequest;
        }

        public Builder setDNDEnabled(boolean z) {
            this.f3524a = z;
            return this;
        }

        public Builder setEndHour(int i) {
            this.f = i;
            return this;
        }

        public Builder setEndMin(int i) {
            this.g = i;
            return this;
        }

        public Builder setLiftWristEnabled(boolean z) {
            this.c = z;
            return this;
        }

        public Builder setNotificationEnabled(boolean z) {
            this.b = z;
            return this;
        }

        public Builder setStartHour(int i) {
            this.d = i;
            return this;
        }

        public Builder setStartMin(int i) {
            this.e = i;
            return this;
        }
    }

    public SetDNDModeRequest(boolean z, int i, int i2, int i3, int i4) {
        this.f = z;
        this.i = i;
        this.j = i2;
        this.k = i3;
        this.l = i4;
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public BleCommand getBleCommand() {
        return BleCommand.SET_DND_MODE;
    }

    public int getEndHour() {
        return this.k;
    }

    public int getEndMin() {
        return this.l;
    }

    public int getStartHour() {
        return this.i;
    }

    public int getStartMin() {
        return this.j;
    }

    public boolean isDNDEnabled() {
        return this.f;
    }

    public boolean isLiftWristEnabled() {
        return this.h;
    }

    public boolean isNotificationEnabled() {
        return this.g;
    }
}
