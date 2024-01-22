package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.common.util.VisibleForTesting;
@VisibleForTesting
/* loaded from: classes6.dex */
public final class zze implements ServiceConnection {
    public final int h;
    public final /* synthetic */ BaseGmsClient i;

    public zze(BaseGmsClient baseGmsClient, int i) {
        this.i = baseGmsClient;
        this.h = i;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Object obj;
        IGmsServiceBroker kVar;
        BaseGmsClient baseGmsClient = this.i;
        if (iBinder == null) {
            BaseGmsClient.j(baseGmsClient, 16);
            return;
        }
        obj = baseGmsClient.u;
        synchronized (obj) {
            BaseGmsClient baseGmsClient2 = this.i;
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            if (queryLocalInterface != null && (queryLocalInterface instanceof IGmsServiceBroker)) {
                kVar = (IGmsServiceBroker) queryLocalInterface;
            } else {
                kVar = new k(iBinder);
            }
            baseGmsClient2.v = kVar;
        }
        this.i.zzl(0, null, this.h);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        Object obj;
        obj = this.i.u;
        synchronized (obj) {
            this.i.v = null;
        }
        Handler handler = this.i.s;
        handler.sendMessage(handler.obtainMessage(6, this.h, 1));
    }
}
