package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.zzu;
@SafeParcelable.Class(creator = "LocationReceiverCreator")
/* loaded from: classes8.dex */
public final class zzdb extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdb> CREATOR = new zzdc();
    @SafeParcelable.Field(getter = "getType", id = 1)
    public final int h;
    @Nullable
    @SafeParcelable.Field(getter = "getOldBinderReceiver", id = 2)
    public final IBinder i;
    @Nullable
    @SafeParcelable.Field(getter = "getBinderReceiver", id = 3)
    public final IBinder j;
    @Nullable
    @SafeParcelable.Field(getter = "getPendingIntentReceiver", id = 4)
    public final PendingIntent k;
    @Nullable
    @SafeParcelable.Field(getter = "getModuleId", id = 5)
    public final String l;
    @Nullable
    @SafeParcelable.Field(getter = "getListenerId", id = 6)
    public final String m;

    @SafeParcelable.Constructor
    public zzdb(@SafeParcelable.Param(id = 1) int i, @Nullable @SafeParcelable.Param(id = 2) IBinder iBinder, @Nullable @SafeParcelable.Param(id = 3) IBinder iBinder2, @Nullable @SafeParcelable.Param(id = 4) PendingIntent pendingIntent, @Nullable @SafeParcelable.Param(id = 5) String str, @Nullable @SafeParcelable.Param(id = 6) String str2) {
        this.h = i;
        this.i = iBinder;
        this.j = iBinder2;
        this.k = pendingIntent;
        this.l = Build.VERSION.SDK_INT >= 30 ? null : str;
        this.m = str2;
    }

    public static zzdb zza(PendingIntent pendingIntent, @Nullable String str, @Nullable String str2) {
        return new zzdb(3, null, null, pendingIntent, null, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.os.IBinder] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.google.android.gms.location.zzr, android.os.IBinder] */
    public static zzdb zzb(@Nullable IInterface iInterface, com.google.android.gms.location.zzr zzrVar, @Nullable String str, @Nullable String str2) {
        if (iInterface == null) {
            iInterface = null;
        }
        return new zzdb(2, iInterface, zzrVar, null, null, str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.os.IBinder] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.google.android.gms.location.zzu, android.os.IBinder] */
    public static zzdb zzc(@Nullable IInterface iInterface, zzu zzuVar, @Nullable String str, @Nullable String str2) {
        if (iInterface == null) {
            iInterface = null;
        }
        return new zzdb(1, iInterface, zzuVar, null, null, str2);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeIBinder(parcel, 2, this.i, false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.j, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.k, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.l, false);
        SafeParcelWriter.writeString(parcel, 6, this.m, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
