package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.location.Geofence;
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.Locale;
@VisibleForTesting
@SafeParcelable.Class(creator = "ParcelableGeofenceCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes8.dex */
public final class zzdh extends AbstractSafeParcelable implements Geofence {
    public static final Parcelable.Creator<zzdh> CREATOR = new zzdi();
    @SafeParcelable.Field(getter = "getRequestId", id = 1)
    public final String h;
    @SafeParcelable.Field(getter = "getExpirationTime", id = 2)
    public final long i;
    @SafeParcelable.Field(getter = "getType", id = 3)
    public final short j;
    @SafeParcelable.Field(getter = "getLatitude", id = 4)
    public final double k;
    @SafeParcelable.Field(getter = "getLongitude", id = 5)
    public final double l;
    @SafeParcelable.Field(getter = "getRadius", id = 6)
    public final float m;
    @SafeParcelable.Field(getter = "getTransitionTypes", id = 7)
    public final int n;
    @SafeParcelable.Field(defaultValue = BleConst.GetDeviceTime, getter = "getNotificationResponsiveness", id = 8)
    public final int o;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getLoiteringDelay", id = 9)
    public final int p;

    @SafeParcelable.Constructor
    public zzdh(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 7) int i, @SafeParcelable.Param(id = 3) short s, @SafeParcelable.Param(id = 4) double d, @SafeParcelable.Param(id = 5) double d2, @SafeParcelable.Param(id = 6) float f, @SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 8) int i2, @SafeParcelable.Param(id = 9) int i3) {
        if (str == null || str.length() > 100) {
            throw new IllegalArgumentException("requestId is null or too long: ".concat(String.valueOf(str)));
        }
        if (f <= 0.0f) {
            throw new IllegalArgumentException("invalid radius: " + f);
        } else if (d > 90.0d || d < -90.0d) {
            throw new IllegalArgumentException("invalid latitude: " + d);
        } else if (d2 > 180.0d || d2 < -180.0d) {
            throw new IllegalArgumentException("invalid longitude: " + d2);
        } else {
            int i4 = i & 7;
            if (i4 != 0) {
                this.j = s;
                this.h = str;
                this.k = d;
                this.l = d2;
                this.m = f;
                this.i = j;
                this.n = i4;
                this.o = i2;
                this.p = i3;
                return;
            }
            throw new IllegalArgumentException("No supported transition specified: " + i);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzdh) {
            zzdh zzdhVar = (zzdh) obj;
            if (this.m == zzdhVar.m && this.k == zzdhVar.k && this.l == zzdhVar.l && this.j == zzdhVar.j) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.location.Geofence
    public final long getExpirationTime() {
        return this.i;
    }

    @Override // com.google.android.gms.location.Geofence
    public final double getLatitude() {
        return this.k;
    }

    @Override // com.google.android.gms.location.Geofence
    public final int getLoiteringDelay() {
        return this.p;
    }

    @Override // com.google.android.gms.location.Geofence
    public final double getLongitude() {
        return this.l;
    }

    @Override // com.google.android.gms.location.Geofence
    public final int getNotificationResponsiveness() {
        return this.o;
    }

    @Override // com.google.android.gms.location.Geofence
    public final float getRadius() {
        return this.m;
    }

    @Override // com.google.android.gms.location.Geofence
    public final String getRequestId() {
        return this.h;
    }

    @Override // com.google.android.gms.location.Geofence
    public final int getTransitionTypes() {
        return this.n;
    }

    public final int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.k);
        long doubleToLongBits2 = Double.doubleToLongBits(this.l);
        return ((((((((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + Float.floatToIntBits(this.m)) * 31) + this.j) * 31) + this.n;
    }

    public final String toString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[9];
        short s = this.j;
        objArr[0] = s != -1 ? s != 1 ? "UNKNOWN" : "CIRCLE" : "INVALID";
        objArr[1] = this.h.replaceAll("\\p{C}", "?");
        objArr[2] = Integer.valueOf(this.n);
        objArr[3] = Double.valueOf(this.k);
        objArr[4] = Double.valueOf(this.l);
        objArr[5] = Float.valueOf(this.m);
        objArr[6] = Integer.valueOf(this.o / 1000);
        objArr[7] = Integer.valueOf(this.p);
        objArr[8] = Long.valueOf(this.i);
        return String.format(locale, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", objArr);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.h, false);
        SafeParcelWriter.writeLong(parcel, 2, this.i);
        SafeParcelWriter.writeShort(parcel, 3, this.j);
        SafeParcelWriter.writeDouble(parcel, 4, this.k);
        SafeParcelWriter.writeDouble(parcel, 5, this.l);
        SafeParcelWriter.writeFloat(parcel, 6, this.m);
        SafeParcelWriter.writeInt(parcel, 7, this.n);
        SafeParcelWriter.writeInt(parcel, 8, this.o);
        SafeParcelWriter.writeInt(parcel, 9, this.p);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
