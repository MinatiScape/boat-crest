package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.jstyle.blesdk1860.constant.BleConst;
@KeepForSdk
@SafeParcelable.Class(creator = "GetServiceRequestCreator")
@SafeParcelable.Reserved({9})
/* loaded from: classes6.dex */
public class GetServiceRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<GetServiceRequest> CREATOR = new zzn();
    public static final Scope[] v = new Scope[0];
    public static final Feature[] w = new Feature[0];
    @SafeParcelable.VersionField(id = 1)
    public final int h;
    @SafeParcelable.Field(id = 2)
    public final int i;
    @SafeParcelable.Field(id = 3)
    public final int j;
    @SafeParcelable.Field(id = 4)
    public String k;
    @Nullable
    @SafeParcelable.Field(id = 5)
    public IBinder l;
    @SafeParcelable.Field(defaultValueUnchecked = "GetServiceRequest.EMPTY_SCOPES", id = 6)
    public Scope[] m;
    @SafeParcelable.Field(defaultValueUnchecked = "new android.os.Bundle()", id = 7)
    public Bundle n;
    @Nullable
    @SafeParcelable.Field(id = 8)
    public Account o;
    @SafeParcelable.Field(defaultValueUnchecked = "GetServiceRequest.EMPTY_FEATURES", id = 10)
    public Feature[] p;
    @SafeParcelable.Field(defaultValueUnchecked = "GetServiceRequest.EMPTY_FEATURES", id = 11)
    public Feature[] q;
    @SafeParcelable.Field(id = 12)
    public final boolean r;
    @SafeParcelable.Field(defaultValue = BleConst.GetDeviceTime, id = 13)
    public final int s;
    @SafeParcelable.Field(getter = "isRequestingTelemetryConfiguration", id = 14)
    public boolean t;
    @Nullable
    @SafeParcelable.Field(getter = "getAttributionTag", id = 15)
    public final String u;

    @SafeParcelable.Constructor
    public GetServiceRequest(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) int i3, @SafeParcelable.Param(id = 4) String str, @Nullable @SafeParcelable.Param(id = 5) IBinder iBinder, @SafeParcelable.Param(id = 6) Scope[] scopeArr, @SafeParcelable.Param(id = 7) Bundle bundle, @Nullable @SafeParcelable.Param(id = 8) Account account, @SafeParcelable.Param(id = 10) Feature[] featureArr, @SafeParcelable.Param(id = 11) Feature[] featureArr2, @SafeParcelable.Param(id = 12) boolean z, @SafeParcelable.Param(id = 13) int i4, @SafeParcelable.Param(id = 14) boolean z2, @Nullable @SafeParcelable.Param(id = 15) String str2) {
        scopeArr = scopeArr == null ? v : scopeArr;
        bundle = bundle == null ? new Bundle() : bundle;
        featureArr = featureArr == null ? w : featureArr;
        featureArr2 = featureArr2 == null ? w : featureArr2;
        this.h = i;
        this.i = i2;
        this.j = i3;
        if ("com.google.android.gms".equals(str)) {
            this.k = "com.google.android.gms";
        } else {
            this.k = str;
        }
        if (i < 2) {
            this.o = iBinder != null ? AccountAccessor.getAccountBinderSafe(IAccountAccessor.Stub.asInterface(iBinder)) : null;
        } else {
            this.l = iBinder;
            this.o = account;
        }
        this.m = scopeArr;
        this.n = bundle;
        this.p = featureArr;
        this.q = featureArr2;
        this.r = z;
        this.s = i4;
        this.t = z2;
        this.u = str2;
    }

    @NonNull
    @KeepForSdk
    public Bundle getExtraArgs() {
        return this.n;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        zzn.a(this, parcel, i);
    }

    @Nullable
    public final String zza() {
        return this.u;
    }
}
