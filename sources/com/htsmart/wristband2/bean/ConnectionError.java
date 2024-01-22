package com.htsmart.wristband2.bean;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.polidea.rxandroidble2.exceptions.BleScanException;
/* loaded from: classes11.dex */
public class ConnectionError {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public Throwable f11953a;
    public boolean b;
    public int c;
    @Nullable
    public BleScanException d;

    @Deprecated
    public ConnectionError(@NonNull Throwable th, boolean z) {
        this.f11953a = th;
        this.b = z;
    }

    public ConnectionError(@NonNull Throwable th, boolean z, int i, @Nullable BleScanException bleScanException) {
        this.f11953a = th;
        this.b = z;
        this.c = i;
        this.d = bleScanException;
    }

    @Nullable
    public BleScanException getBleScanException() {
        return this.d;
    }

    public int getRetryTimes() {
        return this.c;
    }

    @NonNull
    public Throwable getThrowable() {
        return this.f11953a;
    }

    public boolean isRetry() {
        return this.b;
    }

    public void setBleScanException(@Nullable BleScanException bleScanException) {
        this.d = bleScanException;
    }

    public void setRetry(boolean z) {
        this.b = z;
    }

    public void setRetryTimes(int i) {
        this.c = i;
    }

    public void setThrowable(@NonNull Throwable th) {
        this.f11953a = th;
    }
}
