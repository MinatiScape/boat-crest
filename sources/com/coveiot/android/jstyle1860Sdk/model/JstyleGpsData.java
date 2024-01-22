package com.coveiot.android.jstyle1860Sdk.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.material.color.c;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0018\u0010\u0019J\b\u0010\u0003\u001a\u00020\u0002H\u0016R\"\u0010\u000b\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\u0013\u001a\u00020\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0017\u001a\u00020\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u000e\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012¨\u0006\u001a"}, d2 = {"Lcom/coveiot/android/jstyle1860Sdk/model/JstyleGpsData;", "", "", "toString", "", "a", "J", "getTimeStamp", "()J", "setTimeStamp", "(J)V", "timeStamp", "", "b", "D", "getLatitudeValue", "()D", "setLatitudeValue", "(D)V", "latitudeValue", c.f10260a, "getLongitudeValue", "setLongitudeValue", "longitudeValue", "<init>", "()V", "jstyle1860Sdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class JstyleGpsData {

    /* renamed from: a  reason: collision with root package name */
    public long f4607a;
    public double b;
    public double c;

    public final double getLatitudeValue() {
        return this.b;
    }

    public final double getLongitudeValue() {
        return this.c;
    }

    public final long getTimeStamp() {
        return this.f4607a;
    }

    public final void setLatitudeValue(double d) {
        this.b = d;
    }

    public final void setLongitudeValue(double d) {
        this.c = d;
    }

    public final void setTimeStamp(long j) {
        this.f4607a = j;
    }

    @NotNull
    public String toString() {
        return "JstyleGpsData(timeStamp=" + this.f4607a + ", latitudeValue=" + this.b + ", longitudeValue=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }
}
