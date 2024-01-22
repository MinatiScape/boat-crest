package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
/* loaded from: classes8.dex */
public abstract class zzt extends zzb implements zzu {
    public static zzu zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
        return queryLocalInterface instanceof zzu ? (zzu) queryLocalInterface : new zzs(iBinder);
    }
}
