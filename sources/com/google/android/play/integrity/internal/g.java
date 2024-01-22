package com.google.android.play.integrity.internal;

import android.os.IBinder;
import android.os.IInterface;
/* loaded from: classes10.dex */
public abstract class g extends b implements h {
    public static h b(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.play.core.integrity.protocol.IIntegrityService");
        return queryLocalInterface instanceof h ? (h) queryLocalInterface : new f(iBinder);
    }
}
