package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class DeviceInfoRequest extends BleBaseRequest {
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3487a;
        public boolean b;
        public boolean c;
        public boolean d;
        public boolean e;
        public boolean f;
        public boolean g;
        public boolean h;
        @Nullable
        public Object i;

        @NotNull
        public final DeviceInfoRequest build() {
            DeviceInfoRequest deviceInfoRequest = new DeviceInfoRequest();
            deviceInfoRequest.setDevicName(this.f3487a);
            deviceInfoRequest.setSerialNo(this.b);
            deviceInfoRequest.setMacAddress(this.c);
            deviceInfoRequest.setManufacturerName(this.d);
            deviceInfoRequest.setModelNo(this.e);
            deviceInfoRequest.setFwVersion(this.f);
            deviceInfoRequest.setHwVersion(this.g);
            deviceInfoRequest.setSoftwareRevision(this.h);
            return deviceInfoRequest;
        }

        @Nullable
        public final Object getId$bleabstract_release() {
            return this.i;
        }

        public final boolean isDeviceName$bleabstract_release() {
            return this.f3487a;
        }

        public final boolean isFwVersion$bleabstract_release() {
            return this.f;
        }

        public final boolean isHwVersion$bleabstract_release() {
            return this.g;
        }

        public final boolean isMacAddress$bleabstract_release() {
            return this.c;
        }

        public final boolean isManufacturer$bleabstract_release() {
            return this.d;
        }

        public final boolean isModelNo$bleabstract_release() {
            return this.e;
        }

        public final boolean isSerialNo$bleabstract_release() {
            return this.b;
        }

        public final boolean isSoftwareRevision$bleabstract_release() {
            return this.h;
        }

        public final void setDeviceName$bleabstract_release(boolean z) {
            this.f3487a = z;
        }

        public final void setFwVersion$bleabstract_release(boolean z) {
            this.f = z;
        }

        public final void setHwVersion$bleabstract_release(boolean z) {
            this.g = z;
        }

        public final void setId$bleabstract_release(@Nullable Object obj) {
            this.i = obj;
        }

        @NotNull
        public final Builder setIsDeviceName(boolean z) {
            this.f3487a = z;
            return this;
        }

        @NotNull
        public final Builder setIsFwVersion(boolean z) {
            this.f = z;
            return this;
        }

        @NotNull
        public final Builder setIsHwVersion(boolean z) {
            this.g = z;
            return this;
        }

        @NotNull
        public final Builder setIsMacAddress(boolean z) {
            this.c = z;
            return this;
        }

        @NotNull
        public final Builder setIsManufacturer(boolean z) {
            this.d = z;
            return this;
        }

        @NotNull
        public final Builder setIsModelNo(boolean z) {
            this.e = z;
            return this;
        }

        @NotNull
        public final Builder setIsSerialNo(boolean z) {
            this.b = z;
            return this;
        }

        @NotNull
        public final Builder setIsSoftwareVersion(boolean z) {
            this.h = z;
            return this;
        }

        public final void setMacAddress$bleabstract_release(boolean z) {
            this.c = z;
        }

        public final void setManufacturer$bleabstract_release(boolean z) {
            this.d = z;
        }

        public final void setModelNo$bleabstract_release(boolean z) {
            this.e = z;
        }

        public final void setSerialNo$bleabstract_release(boolean z) {
            this.b = z;
        }

        public final void setSoftwareRevision$bleabstract_release(boolean z) {
            this.h = z;
        }
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    @Nullable
    public BleCommand getBleCommand() {
        return BleCommand.DEVICE_INFO;
    }

    public final boolean isDevicName() {
        return this.f;
    }

    public final boolean isFwVersion() {
        return this.k;
    }

    public final boolean isHwVersion() {
        return this.l;
    }

    public final boolean isMacAddress() {
        return this.h;
    }

    public final boolean isManufacturerName() {
        return this.i;
    }

    public final boolean isModelNo() {
        return this.j;
    }

    public final boolean isSerialNo() {
        return this.g;
    }

    public final boolean isSoftwareRevision() {
        return this.m;
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public void setBleCommand(@Nullable BleCommand bleCommand) {
    }

    public final void setDevicName(boolean z) {
        this.f = z;
    }

    public final void setFwVersion(boolean z) {
        this.k = z;
    }

    public final void setHwVersion(boolean z) {
        this.l = z;
    }

    public final void setMacAddress(boolean z) {
        this.h = z;
    }

    public final void setManufacturerName(boolean z) {
        this.i = z;
    }

    public final void setModelNo(boolean z) {
        this.j = z;
    }

    public final void setSerialNo(boolean z) {
        this.g = z;
    }

    public final void setSoftwareRevision(boolean z) {
        this.m = z;
    }
}
