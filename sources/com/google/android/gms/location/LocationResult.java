package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Collections;
import java.util.List;
@SafeParcelable.Class(creator = "LocationResultCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes10.dex */
public final class LocationResult extends AbstractSafeParcelable implements ReflectedParcelable {
    @SafeParcelable.Field(defaultValueUnchecked = "LocationResult.DEFAULT_LOCATIONS", getter = "getLocations", id = 1)
    public final List h;
    public static final List i = Collections.emptyList();
    @NonNull
    public static final Parcelable.Creator<LocationResult> CREATOR = new zzy();

    @SafeParcelable.Constructor
    public LocationResult(@SafeParcelable.Param(id = 1) List list) {
        this.h = list;
    }

    @NonNull
    public static LocationResult create(@NonNull List<Location> list) {
        if (list == null) {
            list = i;
        }
        return new LocationResult(list);
    }

    @Nullable
    public static LocationResult extractResult(@NonNull Intent intent) {
        if (hasResult(intent)) {
            LocationResult locationResult = (LocationResult) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.android.gms.location.EXTRA_LOCATION_RESULT_BYTES", CREATOR);
            return locationResult == null ? (LocationResult) intent.getParcelableExtra("com.google.android.gms.location.EXTRA_LOCATION_RESULT") : locationResult;
        }
        return null;
    }

    public static boolean hasResult(@NonNull Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_RESULT") || intent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_RESULT_BYTES");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(@androidx.annotation.Nullable java.lang.Object r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.google.android.gms.location.LocationResult
            r1 = 0
            if (r0 == 0) goto L8c
            com.google.android.gms.location.LocationResult r9 = (com.google.android.gms.location.LocationResult) r9
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 31
            if (r0 < r2) goto L16
            java.util.List r0 = r8.h
            java.util.List r9 = r9.h
            boolean r9 = r0.equals(r9)
            return r9
        L16:
            java.util.List r0 = r8.h
            int r0 = r0.size()
            java.util.List r2 = r9.h
            int r2 = r2.size()
            if (r0 == r2) goto L25
            return r1
        L25:
            java.util.List r0 = r8.h
            java.util.Iterator r0 = r0.iterator()
            java.util.List r9 = r9.h
            java.util.Iterator r9 = r9.iterator()
        L31:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L8a
            java.lang.Object r2 = r0.next()
            android.location.Location r2 = (android.location.Location) r2
            java.lang.Object r3 = r9.next()
            android.location.Location r3 = (android.location.Location) r3
            double r4 = r2.getLatitude()
            double r6 = r3.getLatitude()
            int r4 = java.lang.Double.compare(r4, r6)
            if (r4 == 0) goto L52
            return r1
        L52:
            double r4 = r2.getLongitude()
            double r6 = r3.getLongitude()
            int r4 = java.lang.Double.compare(r4, r6)
            if (r4 == 0) goto L61
            return r1
        L61:
            long r4 = r2.getTime()
            long r6 = r3.getTime()
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L6e
            return r1
        L6e:
            long r4 = r2.getElapsedRealtimeNanos()
            long r6 = r3.getElapsedRealtimeNanos()
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L7b
            return r1
        L7b:
            java.lang.String r2 = r2.getProvider()
            java.lang.String r3 = r3.getProvider()
            boolean r2 = com.google.android.gms.common.internal.Objects.equal(r2, r3)
            if (r2 != 0) goto L31
            return r1
        L8a:
            r9 = 1
            return r9
        L8c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.LocationResult.equals(java.lang.Object):boolean");
    }

    @Nullable
    public Location getLastLocation() {
        int size = this.h.size();
        if (size == 0) {
            return null;
        }
        return (Location) this.h.get(size - 1);
    }

    @NonNull
    public List<Location> getLocations() {
        return this.h;
    }

    public int hashCode() {
        return Objects.hashCode(this.h);
    }

    @NonNull
    public String toString() {
        return "LocationResult".concat(String.valueOf(this.h));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getLocations(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
