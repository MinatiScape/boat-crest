package com.coveiot.android.bleabstract.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.maps.model.LatLng;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class ActivityGPSSample {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public LatLng f3391a = new LatLng(0.0d, 0.0d);
    public long b;

    public final long getGpsTimeStamp() {
        return this.b;
    }

    @NotNull
    public final LatLng getLocation() {
        return this.f3391a;
    }

    public final void setGpsTimeStamp(long j) {
        this.b = j;
    }

    public final void setLocation(@NotNull LatLng latLng) {
        Intrinsics.checkNotNullParameter(latLng, "<set-?>");
        this.f3391a = latLng;
    }

    @NotNull
    public String toString() {
        return "ActivityGPSSample(location=" + this.f3391a + ", gpsTimeStamp=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
