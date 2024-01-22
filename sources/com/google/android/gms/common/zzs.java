package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.Nullable;
@SafeParcelable.Class(creator = "GoogleCertificatesQueryCreator")
/* loaded from: classes6.dex */
public final class zzs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzs> CREATOR = new zzt();
    @SafeParcelable.Field(getter = "getCallingPackage", id = 1)
    public final String h;
    @Nullable
    @SafeParcelable.Field(getter = "getCallingCertificateBinder", id = 2, type = "android.os.IBinder")
    public final i i;
    @SafeParcelable.Field(getter = "getAllowTestKeys", id = 3)
    public final boolean j;
    @SafeParcelable.Field(defaultValue = "false", getter = "getIgnoreTestKeysOverride", id = 4)
    public final boolean k;

    @SafeParcelable.Constructor
    public zzs(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) @Nullable IBinder iBinder, @SafeParcelable.Param(id = 3) boolean z, @SafeParcelable.Param(id = 4) boolean z2) {
        this.h = str;
        j jVar = null;
        if (iBinder != null) {
            try {
                IObjectWrapper zzd = zzz.zzg(iBinder).zzd();
                byte[] bArr = zzd == null ? null : (byte[]) ObjectWrapper.unwrap(zzd);
                if (bArr != null) {
                    jVar = new j(bArr);
                } else {
                    Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
                }
            } catch (RemoteException e) {
                Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", e);
            }
        }
        this.i = jVar;
        this.j = z;
        this.k = z2;
    }

    public zzs(String str, @Nullable i iVar, boolean z, boolean z2) {
        this.h = str;
        this.i = iVar;
        this.j = z;
        this.k = z2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.h, false);
        i iVar = this.i;
        if (iVar == null) {
            Log.w("GoogleCertificatesQuery", "certificate binder is null");
            iVar = null;
        }
        SafeParcelWriter.writeIBinder(parcel, 2, iVar, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.j);
        SafeParcelWriter.writeBoolean(parcel, 4, this.k);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
