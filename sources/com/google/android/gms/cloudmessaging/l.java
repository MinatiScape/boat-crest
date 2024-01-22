package com.google.android.gms.cloudmessaging;

import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
/* loaded from: classes6.dex */
public final class l {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Messenger f8225a;
    @Nullable
    public final zza b;

    public l(IBinder iBinder) throws RemoteException {
        String interfaceDescriptor = iBinder.getInterfaceDescriptor();
        if ("android.os.IMessenger".equals(interfaceDescriptor)) {
            this.f8225a = new Messenger(iBinder);
            this.b = null;
        } else if ("com.google.android.gms.iid.IMessengerCompat".equals(interfaceDescriptor)) {
            this.b = new zza(iBinder);
            this.f8225a = null;
        } else {
            String valueOf = String.valueOf(interfaceDescriptor);
            Log.w("MessengerIpcClient", valueOf.length() != 0 ? "Invalid interface descriptor: ".concat(valueOf) : new String("Invalid interface descriptor: "));
            throw new RemoteException();
        }
    }

    public final void a(Message message) throws RemoteException {
        Messenger messenger = this.f8225a;
        if (messenger != null) {
            messenger.send(message);
            return;
        }
        zza zzaVar = this.b;
        if (zzaVar != null) {
            zzaVar.zza(message);
            return;
        }
        throw new IllegalStateException("Both messengers are null");
    }
}
