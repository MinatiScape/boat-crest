package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
@VisibleForTesting
/* loaded from: classes10.dex */
public final class zzji implements ServiceConnection, BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    public volatile boolean h;
    public volatile zzee i;
    public final /* synthetic */ zzjj j;

    public zzji(zzjj zzjjVar) {
        this.j = zzjjVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    @MainThread
    public final void onConnected(Bundle bundle) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnected");
        synchronized (this) {
            try {
                Preconditions.checkNotNull(this.i);
                this.j.zzs.zzaz().zzp(new c3(this, this.i.getService()));
            } catch (DeadObjectException | IllegalStateException unused) {
                this.i = null;
                this.h = false;
            }
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    @MainThread
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionFailed");
        zzei zzl = this.j.zzs.zzl();
        if (zzl != null) {
            zzl.zzk().zzb("Service connection failed", connectionResult);
        }
        synchronized (this) {
            this.h = false;
            this.i = null;
        }
        this.j.zzs.zzaz().zzp(new e3(this));
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    @MainThread
    public final void onConnectionSuspended(int i) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionSuspended");
        this.j.zzs.zzay().zzc().zza("Service connection suspended");
        this.j.zzs.zzaz().zzp(new d3(this));
    }

    @Override // android.content.ServiceConnection
    @MainThread
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        zzji zzjiVar;
        zzdz zzdxVar;
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceConnected");
        synchronized (this) {
            if (iBinder == null) {
                this.h = false;
                this.j.zzs.zzay().zzd().zza("Service connected with null binder");
                return;
            }
            zzdz zzdzVar = null;
            try {
                String interfaceDescriptor = iBinder.getInterfaceDescriptor();
                if ("com.google.android.gms.measurement.internal.IMeasurementService".equals(interfaceDescriptor)) {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                    if (queryLocalInterface instanceof zzdz) {
                        zzdxVar = (zzdz) queryLocalInterface;
                    } else {
                        zzdxVar = new zzdx(iBinder);
                    }
                    zzdzVar = zzdxVar;
                    this.j.zzs.zzay().zzj().zza("Bound to IMeasurementService interface");
                } else {
                    this.j.zzs.zzay().zzd().zzb("Got binder with a wrong descriptor", interfaceDescriptor);
                }
            } catch (RemoteException unused) {
                this.j.zzs.zzay().zzd().zza("Service connect failed to get IMeasurementService");
            }
            if (zzdzVar == null) {
                this.h = false;
                try {
                    ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
                    Context zzau = this.j.zzs.zzau();
                    zzjiVar = this.j.b;
                    connectionTracker.unbindService(zzau, zzjiVar);
                } catch (IllegalArgumentException unused2) {
                }
            } else {
                this.j.zzs.zzaz().zzp(new a3(this, zzdzVar));
            }
        }
    }

    @Override // android.content.ServiceConnection
    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceDisconnected");
        this.j.zzs.zzay().zzc().zza("Service disconnected");
        this.j.zzs.zzaz().zzp(new b3(this, componentName));
    }

    @WorkerThread
    public final void zzb(Intent intent) {
        zzji zzjiVar;
        this.j.zzg();
        Context zzau = this.j.zzs.zzau();
        ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
        synchronized (this) {
            if (this.h) {
                this.j.zzs.zzay().zzj().zza("Connection attempt already in progress");
                return;
            }
            this.j.zzs.zzay().zzj().zza("Using local app measurement service");
            this.h = true;
            zzjiVar = this.j.b;
            connectionTracker.bindService(zzau, intent, zzjiVar, 129);
        }
    }

    @WorkerThread
    public final void zzc() {
        this.j.zzg();
        Context zzau = this.j.zzs.zzau();
        synchronized (this) {
            if (this.h) {
                this.j.zzs.zzay().zzj().zza("Connection attempt already in progress");
            } else if (this.i != null && (this.i.isConnecting() || this.i.isConnected())) {
                this.j.zzs.zzay().zzj().zza("Already awaiting connection attempt");
            } else {
                this.i = new zzee(zzau, Looper.getMainLooper(), this, this);
                this.j.zzs.zzay().zzj().zza("Connecting to remote service");
                this.h = true;
                Preconditions.checkNotNull(this.i);
                this.i.checkAvailabilityAndConnect();
            }
        }
    }

    @WorkerThread
    public final void zzd() {
        if (this.i != null && (this.i.isConnected() || this.i.isConnecting())) {
            this.i.disconnect();
        }
        this.i = null;
    }
}
