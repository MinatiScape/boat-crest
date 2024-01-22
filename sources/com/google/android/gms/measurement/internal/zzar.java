package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Iterator;
@SafeParcelable.Class(creator = "EventParamsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes10.dex */
public final class zzar extends AbstractSafeParcelable implements Iterable<String> {
    public static final Parcelable.Creator<zzar> CREATOR = new zzas();
    @SafeParcelable.Field(getter = "z", id = 2)
    public final Bundle h;

    @SafeParcelable.Constructor
    public zzar(@SafeParcelable.Param(id = 2) Bundle bundle) {
        this.h = bundle;
    }

    public final Double b(String str) {
        return Double.valueOf(this.h.getDouble("value"));
    }

    public final Long c(String str) {
        return Long.valueOf(this.h.getLong("value"));
    }

    public final Object d(String str) {
        return this.h.get(str);
    }

    public final String e(String str) {
        return this.h.getString(str);
    }

    @Override // java.lang.Iterable
    public final Iterator<String> iterator() {
        return new h(this);
    }

    public final String toString() {
        return this.h.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, zzc(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int zza() {
        return this.h.size();
    }

    public final Bundle zzc() {
        return new Bundle(this.h);
    }
}
