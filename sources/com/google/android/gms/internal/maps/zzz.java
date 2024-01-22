package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
/* loaded from: classes8.dex */
public abstract class zzz extends zzb implements zzaa {
    public static zzaa zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
        return queryLocalInterface instanceof zzaa ? (zzaa) queryLocalInterface : new zzy(iBinder);
    }
}
