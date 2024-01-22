package com.realsil.sdk.core;

import androidx.annotation.NonNull;
import com.szabh.smable3.entity.BleDeviceInfo;
/* loaded from: classes12.dex */
public final class RtkConfigure {

    /* renamed from: a  reason: collision with root package name */
    public boolean f13541a;
    public boolean b;
    public String c;
    public int d;

    /* loaded from: classes12.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public RtkConfigure f13542a = new RtkConfigure();

        public RtkConfigure build() {
            return this.f13542a;
        }

        public Builder debugEnabled(boolean z) {
            this.f13542a.setDebugEnabled(z);
            return this;
        }

        public Builder globalLogLevel(int i) {
            this.f13542a.setGlobalLogLevel(i);
            return this;
        }

        public Builder logTag(@NonNull String str) {
            this.f13542a.setLogTag(str);
            return this;
        }

        public Builder printLog(boolean z) {
            this.f13542a.setPrintLog(z);
            return this;
        }
    }

    public int getGlobalLogLevel() {
        return this.d;
    }

    public String getLogTag() {
        return this.c;
    }

    public boolean isDebugEnabled() {
        return this.f13541a;
    }

    public boolean isPrintLog() {
        return this.b;
    }

    public void setDebugEnabled(boolean z) {
        this.f13541a = z;
    }

    public void setGlobalLogLevel(int i) {
        this.d = i;
    }

    public void setLogTag(String str) {
        this.c = str;
    }

    public void setPrintLog(boolean z) {
        this.b = z;
    }

    public String toString() {
        return "RtkConfigure{" + String.format("\n\tdebugEnabled=%b, printLog=%b, logTag=%s, globalLogLevel=0x%02X", Boolean.valueOf(this.f13541a), Boolean.valueOf(this.b), this.c, Integer.valueOf(this.d)) + "\n}";
    }

    public RtkConfigure() {
        this.f13541a = true;
        this.b = true;
        this.c = BleDeviceInfo.PLATFORM_REALTEK;
        this.d = 1;
    }
}
