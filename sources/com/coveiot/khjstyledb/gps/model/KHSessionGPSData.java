package com.coveiot.khjstyledb.gps.model;

import com.google.android.material.color.c;
import kotlin.Metadata;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0016\u0010\u0017R\"\u0010\t\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\r\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0004\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR\"\u0010\u0015\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0018"}, d2 = {"Lcom/coveiot/khjstyledb/gps/model/KHSessionGPSData;", "", "", "a", "D", "getLatitudeValue", "()D", "setLatitudeValue", "(D)V", "latitudeValue", "b", "getLongitudeValue", "setLongitudeValue", "longitudeValue", "", c.f10260a, "J", "getGpsTimeStamp", "()J", "setGpsTimeStamp", "(J)V", "gpsTimeStamp", "<init>", "()V", "khjstyledb_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes8.dex */
public final class KHSessionGPSData {

    /* renamed from: a  reason: collision with root package name */
    public double f7124a;
    public double b;
    public long c;

    public final long getGpsTimeStamp() {
        return this.c;
    }

    public final double getLatitudeValue() {
        return this.f7124a;
    }

    public final double getLongitudeValue() {
        return this.b;
    }

    public final void setGpsTimeStamp(long j) {
        this.c = j;
    }

    public final void setLatitudeValue(double d) {
        this.f7124a = d;
    }

    public final void setLongitudeValue(double d) {
        this.b = d;
    }
}
