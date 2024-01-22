package com.coveiot.android.bleabstract.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class ActivityHeartRateSample {

    /* renamed from: a  reason: collision with root package name */
    public int f3392a;
    public long b;

    public final long getHrTimeStamp() {
        return this.b;
    }

    public final int getHrValue() {
        return this.f3392a;
    }

    public final void setHrTimeStamp(long j) {
        this.b = j;
    }

    public final void setHrValue(int i) {
        this.f3392a = i;
    }

    @NotNull
    public String toString() {
        return "ActivityHeartRateSample(hrValue=" + this.f3392a + ", hrTimeStamp=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
