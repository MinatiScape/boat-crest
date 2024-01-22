package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.BinderThread;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
/* loaded from: classes6.dex */
public final class zzf extends j {
    public final /* synthetic */ BaseGmsClient e;
    @Nullable
    public final IBinder zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @BinderThread
    public zzf(BaseGmsClient baseGmsClient, @Nullable int i, @Nullable IBinder iBinder, Bundle bundle) {
        super(baseGmsClient, i, bundle);
        this.e = baseGmsClient;
        this.zze = iBinder;
    }

    @Override // com.google.android.gms.common.internal.j
    public final void zzb(ConnectionResult connectionResult) {
        if (this.e.B != null) {
            this.e.B.onConnectionFailed(connectionResult);
        }
        this.e.onConnectionFailed(connectionResult);
    }

    @Override // com.google.android.gms.common.internal.j
    public final boolean zzd() {
        BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks;
        BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks2;
        try {
            IBinder iBinder = this.zze;
            Preconditions.checkNotNull(iBinder);
            String interfaceDescriptor = iBinder.getInterfaceDescriptor();
            if (!this.e.getServiceDescriptor().equals(interfaceDescriptor)) {
                String serviceDescriptor = this.e.getServiceDescriptor();
                Log.w("GmsClient", "service descriptor mismatch: " + serviceDescriptor + " vs. " + interfaceDescriptor);
                return false;
            }
            IInterface createServiceInterface = this.e.createServiceInterface(this.zze);
            if (createServiceInterface == null || !(BaseGmsClient.l(this.e, 2, 4, createServiceInterface) || BaseGmsClient.l(this.e, 3, 4, createServiceInterface))) {
                return false;
            }
            this.e.F = null;
            Bundle connectionHint = this.e.getConnectionHint();
            BaseGmsClient baseGmsClient = this.e;
            baseConnectionCallbacks = baseGmsClient.A;
            if (baseConnectionCallbacks != null) {
                baseConnectionCallbacks2 = baseGmsClient.A;
                baseConnectionCallbacks2.onConnected(connectionHint);
                return true;
            }
            return true;
        } catch (RemoteException unused) {
            Log.w("GmsClient", "service probably died");
            return false;
        }
    }
}
