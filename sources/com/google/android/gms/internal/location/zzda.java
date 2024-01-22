package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LastLocationRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;
/* loaded from: classes8.dex */
public final class zzda extends GmsClient {
    public static final /* synthetic */ int zze = 0;
    public final SimpleArrayMap M;
    public final SimpleArrayMap N;
    public final SimpleArrayMap O;

    public zzda(Context context, Looper looper, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 23, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.M = new SimpleArrayMap();
        this.N = new SimpleArrayMap();
        this.O = new SimpleArrayMap();
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        return queryLocalInterface instanceof zzo ? (zzo) queryLocalInterface : new zzn(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Feature[] getApiFeatures() {
        return com.google.android.gms.location.zzm.zzl;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 11717000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final void onConnectionSuspended(int i) {
        super.onConnectionSuspended(i);
        synchronized (this.M) {
            this.M.clear();
        }
        synchronized (this.N) {
            this.N.clear();
        }
        synchronized (this.O) {
            this.O.clear();
        }
    }

    public final boolean p(Feature feature) {
        Feature feature2;
        Feature[] availableFeatures = getAvailableFeatures();
        if (availableFeatures == null) {
            return false;
        }
        int length = availableFeatures.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                feature2 = null;
                break;
            }
            feature2 = availableFeatures[i];
            if (feature.getName().equals(feature2.getName())) {
                break;
            }
            i++;
        }
        return feature2 != null && feature2.getVersion() >= feature.getVersion();
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final boolean usesClientTelemetry() {
        return true;
    }

    public final void zzA(boolean z, TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (p(com.google.android.gms.location.zzm.zzg)) {
            ((zzo) getService()).zzx(z, new a0(this, null, taskCompletionSource));
            return;
        }
        ((zzo) getService()).zzw(z);
        taskCompletionSource.setResult(null);
    }

    public final void zzB(ListenerHolder.ListenerKey listenerKey, boolean z, TaskCompletionSource taskCompletionSource) throws RemoteException {
        synchronized (this.N) {
            k0 k0Var = (k0) this.N.remove(listenerKey);
            if (k0Var == null) {
                taskCompletionSource.setResult(Boolean.FALSE);
                return;
            }
            k0Var.zzh();
            if (z) {
                if (p(com.google.android.gms.location.zzm.zzj)) {
                    ((zzo) getService()).zzy(zzdb.zzb(null, k0Var, null, null), new a0(this, Boolean.TRUE, taskCompletionSource));
                } else {
                    ((zzo) getService()).zzz(new zzdf(2, null, null, k0Var, null, new c0(Boolean.TRUE, taskCompletionSource), null));
                }
            } else {
                taskCompletionSource.setResult(Boolean.TRUE);
            }
        }
    }

    public final void zzC(ListenerHolder.ListenerKey listenerKey, boolean z, TaskCompletionSource taskCompletionSource) throws RemoteException {
        synchronized (this.M) {
            n0 n0Var = (n0) this.M.remove(listenerKey);
            if (n0Var == null) {
                taskCompletionSource.setResult(Boolean.FALSE);
                return;
            }
            n0Var.zzg();
            if (z) {
                if (p(com.google.android.gms.location.zzm.zzj)) {
                    ((zzo) getService()).zzy(zzdb.zzc(null, n0Var, null, null), new a0(this, Boolean.TRUE, taskCompletionSource));
                } else {
                    ((zzo) getService()).zzz(new zzdf(2, null, n0Var, null, null, new c0(Boolean.TRUE, taskCompletionSource), null));
                }
            } else {
                taskCompletionSource.setResult(Boolean.TRUE);
            }
        }
    }

    public final void zzD(PendingIntent pendingIntent, TaskCompletionSource taskCompletionSource, Object obj) throws RemoteException {
        if (p(com.google.android.gms.location.zzm.zzj)) {
            ((zzo) getService()).zzy(zzdb.zza(pendingIntent, null, null), new a0(this, null, taskCompletionSource));
        } else {
            ((zzo) getService()).zzz(new zzdf(2, null, null, null, pendingIntent, new c0(null, taskCompletionSource), null));
        }
    }

    public final LocationAvailability zzp() throws RemoteException {
        return ((zzo) getService()).zzf(getContext().getPackageName());
    }

    public final void zzq(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, TaskCompletionSource taskCompletionSource) throws RemoteException {
        Preconditions.checkNotNull(geofencingRequest, "geofencingRequest can't be null.");
        Preconditions.checkNotNull(pendingIntent, "PendingIntent must be specified.");
        ((zzo) getService()).zzg(geofencingRequest, pendingIntent, new x(taskCompletionSource));
    }

    public final void zzr(TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzo) getService()).zzi(new c0(null, taskCompletionSource));
    }

    public final void zzs(CurrentLocationRequest currentLocationRequest, CancellationToken cancellationToken, final TaskCompletionSource taskCompletionSource) throws RemoteException {
        getContext();
        if (p(com.google.android.gms.location.zzm.zze)) {
            final ICancelToken zze2 = ((zzo) getService()).zze(currentLocationRequest, new b0(this, taskCompletionSource));
            if (cancellationToken != null) {
                cancellationToken.onCanceledRequested(new OnTokenCanceledListener() { // from class: com.google.android.gms.internal.location.zzcf
                    @Override // com.google.android.gms.tasks.OnTokenCanceledListener
                    public final void onCanceled() {
                        ICancelToken iCancelToken = ICancelToken.this;
                        int i = zzda.zze;
                        try {
                            iCancelToken.cancel();
                        } catch (RemoteException unused) {
                        }
                    }
                });
                return;
            }
            return;
        }
        ListenerHolder createListenerHolder = ListenerHolders.createListenerHolder(new y(this, taskCompletionSource), zzdx.zza(), "GetCurrentLocation");
        final ListenerHolder.ListenerKey listenerKey = createListenerHolder.getListenerKey();
        listenerKey.getClass();
        z zVar = new z(this, createListenerHolder, taskCompletionSource);
        TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
        LocationRequest.Builder builder = new LocationRequest.Builder(currentLocationRequest.getPriority(), 0L);
        builder.setMinUpdateIntervalMillis(0L);
        builder.setDurationMillis(currentLocationRequest.getDurationMillis());
        builder.setGranularity(currentLocationRequest.getGranularity());
        builder.setMaxUpdateAgeMillis(currentLocationRequest.getMaxUpdateAgeMillis());
        builder.zza(currentLocationRequest.zze());
        builder.zzc(currentLocationRequest.zza());
        builder.setWaitForAccurateLocation(true);
        builder.zzb(currentLocationRequest.zzd());
        builder.zzd(currentLocationRequest.zzb());
        zzu(zVar, builder.build(), taskCompletionSource2);
        taskCompletionSource2.getTask().addOnCompleteListener(new OnCompleteListener() { // from class: com.google.android.gms.internal.location.zzcg
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                TaskCompletionSource taskCompletionSource3 = TaskCompletionSource.this;
                int i = zzda.zze;
                if (task.isSuccessful()) {
                    return;
                }
                Exception exception = task.getException();
                exception.getClass();
                taskCompletionSource3.trySetException(exception);
            }
        });
        if (cancellationToken != null) {
            cancellationToken.onCanceledRequested(new OnTokenCanceledListener() { // from class: com.google.android.gms.internal.location.zzch
                @Override // com.google.android.gms.tasks.OnTokenCanceledListener
                public final void onCanceled() {
                    try {
                        zzda.this.zzB(listenerKey, true, new TaskCompletionSource());
                    } catch (RemoteException unused) {
                    }
                }
            });
        }
    }

    public final void zzt(LastLocationRequest lastLocationRequest, TaskCompletionSource taskCompletionSource) throws RemoteException {
        getContext();
        if (p(com.google.android.gms.location.zzm.zzf)) {
            ((zzo) getService()).zzj(lastLocationRequest, new b0(this, taskCompletionSource));
        } else {
            taskCompletionSource.setResult(((zzo) getService()).zzd());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0044 A[Catch: all -> 0x0082, TryCatch #0 {, blocks: (B:4:0x001a, B:8:0x0028, B:10:0x003b, B:12:0x0044, B:14:0x0080, B:13:0x0057, B:9:0x002e), top: B:19:0x001a }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0057 A[Catch: all -> 0x0082, TryCatch #0 {, blocks: (B:4:0x001a, B:8:0x0028, B:10:0x003b, B:12:0x0044, B:14:0x0080, B:13:0x0057, B:9:0x002e), top: B:19:0x001a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzu(com.google.android.gms.internal.location.zzcs r18, com.google.android.gms.location.LocationRequest r19, com.google.android.gms.tasks.TaskCompletionSource r20) throws android.os.RemoteException {
        /*
            r17 = this;
            r1 = r17
            r0 = r19
            r2 = r20
            com.google.android.gms.common.api.internal.ListenerHolder r3 = r18.zza()
            com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey r4 = r3.getListenerKey()
            r4.getClass()
            com.google.android.gms.common.Feature r5 = com.google.android.gms.location.zzm.zzj
            boolean r5 = r1.p(r5)
            androidx.collection.SimpleArrayMap r6 = r1.N
            monitor-enter(r6)
            androidx.collection.SimpleArrayMap r7 = r1.N     // Catch: java.lang.Throwable -> L82
            java.lang.Object r7 = r7.get(r4)     // Catch: java.lang.Throwable -> L82
            com.google.android.gms.internal.location.k0 r7 = (com.google.android.gms.internal.location.k0) r7     // Catch: java.lang.Throwable -> L82
            r8 = 0
            if (r7 == 0) goto L2e
            if (r5 == 0) goto L28
            goto L2e
        L28:
            r7.b(r3)     // Catch: java.lang.Throwable -> L82
            r13 = r7
            r7 = r8
            goto L3b
        L2e:
            com.google.android.gms.internal.location.k0 r3 = new com.google.android.gms.internal.location.k0     // Catch: java.lang.Throwable -> L82
            r9 = r18
            r3.<init>(r9)     // Catch: java.lang.Throwable -> L82
            androidx.collection.SimpleArrayMap r9 = r1.N     // Catch: java.lang.Throwable -> L82
            r9.put(r4, r3)     // Catch: java.lang.Throwable -> L82
            r13 = r3
        L3b:
            r17.getContext()     // Catch: java.lang.Throwable -> L82
            java.lang.String r3 = r4.toIdString()     // Catch: java.lang.Throwable -> L82
            if (r5 == 0) goto L57
            android.os.IInterface r4 = r17.getService()     // Catch: java.lang.Throwable -> L82
            com.google.android.gms.internal.location.zzo r4 = (com.google.android.gms.internal.location.zzo) r4     // Catch: java.lang.Throwable -> L82
            com.google.android.gms.internal.location.zzdb r3 = com.google.android.gms.internal.location.zzdb.zzb(r7, r13, r8, r3)     // Catch: java.lang.Throwable -> L82
            com.google.android.gms.internal.location.a0 r5 = new com.google.android.gms.internal.location.a0     // Catch: java.lang.Throwable -> L82
            r5.<init>(r1, r8, r2)     // Catch: java.lang.Throwable -> L82
            r4.zzk(r3, r0, r5)     // Catch: java.lang.Throwable -> L82
            goto L80
        L57:
            android.os.IInterface r4 = r17.getService()     // Catch: java.lang.Throwable -> L82
            com.google.android.gms.internal.location.zzo r4 = (com.google.android.gms.internal.location.zzo) r4     // Catch: java.lang.Throwable -> L82
            com.google.android.gms.location.LocationRequest$Builder r5 = new com.google.android.gms.location.LocationRequest$Builder     // Catch: java.lang.Throwable -> L82
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L82
            r5.zzb(r8)     // Catch: java.lang.Throwable -> L82
            com.google.android.gms.location.LocationRequest r0 = r5.build()     // Catch: java.lang.Throwable -> L82
            com.google.android.gms.internal.location.zzdd r11 = com.google.android.gms.internal.location.zzdd.zza(r8, r0)     // Catch: java.lang.Throwable -> L82
            com.google.android.gms.internal.location.e0 r15 = new com.google.android.gms.internal.location.e0     // Catch: java.lang.Throwable -> L82
            r15.<init>(r2, r13)     // Catch: java.lang.Throwable -> L82
            com.google.android.gms.internal.location.zzdf r0 = new com.google.android.gms.internal.location.zzdf     // Catch: java.lang.Throwable -> L82
            r10 = 1
            r12 = 0
            r14 = 0
            r9 = r0
            r16 = r3
            r9.<init>(r10, r11, r12, r13, r14, r15, r16)     // Catch: java.lang.Throwable -> L82
            r4.zzz(r0)     // Catch: java.lang.Throwable -> L82
        L80:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L82
            return
        L82:
            r0 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L82
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzda.zzu(com.google.android.gms.internal.location.zzcs, com.google.android.gms.location.LocationRequest, com.google.android.gms.tasks.TaskCompletionSource):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0044 A[Catch: all -> 0x0082, TryCatch #0 {, blocks: (B:4:0x001a, B:8:0x0028, B:10:0x003b, B:12:0x0044, B:14:0x0080, B:13:0x0057, B:9:0x002e), top: B:19:0x001a }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0057 A[Catch: all -> 0x0082, TryCatch #0 {, blocks: (B:4:0x001a, B:8:0x0028, B:10:0x003b, B:12:0x0044, B:14:0x0080, B:13:0x0057, B:9:0x002e), top: B:19:0x001a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzv(com.google.android.gms.internal.location.zzcs r18, com.google.android.gms.location.LocationRequest r19, com.google.android.gms.tasks.TaskCompletionSource r20) throws android.os.RemoteException {
        /*
            r17 = this;
            r1 = r17
            r0 = r19
            r2 = r20
            com.google.android.gms.common.api.internal.ListenerHolder r3 = r18.zza()
            com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey r4 = r3.getListenerKey()
            r4.getClass()
            com.google.android.gms.common.Feature r5 = com.google.android.gms.location.zzm.zzj
            boolean r5 = r1.p(r5)
            androidx.collection.SimpleArrayMap r6 = r1.M
            monitor-enter(r6)
            androidx.collection.SimpleArrayMap r7 = r1.M     // Catch: java.lang.Throwable -> L82
            java.lang.Object r7 = r7.get(r4)     // Catch: java.lang.Throwable -> L82
            com.google.android.gms.internal.location.n0 r7 = (com.google.android.gms.internal.location.n0) r7     // Catch: java.lang.Throwable -> L82
            r8 = 0
            if (r7 == 0) goto L2e
            if (r5 == 0) goto L28
            goto L2e
        L28:
            r7.b(r3)     // Catch: java.lang.Throwable -> L82
            r12 = r7
            r7 = r8
            goto L3b
        L2e:
            com.google.android.gms.internal.location.n0 r3 = new com.google.android.gms.internal.location.n0     // Catch: java.lang.Throwable -> L82
            r9 = r18
            r3.<init>(r9)     // Catch: java.lang.Throwable -> L82
            androidx.collection.SimpleArrayMap r9 = r1.M     // Catch: java.lang.Throwable -> L82
            r9.put(r4, r3)     // Catch: java.lang.Throwable -> L82
            r12 = r3
        L3b:
            r17.getContext()     // Catch: java.lang.Throwable -> L82
            java.lang.String r3 = r4.toIdString()     // Catch: java.lang.Throwable -> L82
            if (r5 == 0) goto L57
            android.os.IInterface r4 = r17.getService()     // Catch: java.lang.Throwable -> L82
            com.google.android.gms.internal.location.zzo r4 = (com.google.android.gms.internal.location.zzo) r4     // Catch: java.lang.Throwable -> L82
            com.google.android.gms.internal.location.zzdb r3 = com.google.android.gms.internal.location.zzdb.zzc(r7, r12, r8, r3)     // Catch: java.lang.Throwable -> L82
            com.google.android.gms.internal.location.a0 r5 = new com.google.android.gms.internal.location.a0     // Catch: java.lang.Throwable -> L82
            r5.<init>(r1, r8, r2)     // Catch: java.lang.Throwable -> L82
            r4.zzk(r3, r0, r5)     // Catch: java.lang.Throwable -> L82
            goto L80
        L57:
            android.os.IInterface r4 = r17.getService()     // Catch: java.lang.Throwable -> L82
            com.google.android.gms.internal.location.zzo r4 = (com.google.android.gms.internal.location.zzo) r4     // Catch: java.lang.Throwable -> L82
            com.google.android.gms.location.LocationRequest$Builder r5 = new com.google.android.gms.location.LocationRequest$Builder     // Catch: java.lang.Throwable -> L82
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L82
            r5.zzb(r8)     // Catch: java.lang.Throwable -> L82
            com.google.android.gms.location.LocationRequest r0 = r5.build()     // Catch: java.lang.Throwable -> L82
            com.google.android.gms.internal.location.zzdd r11 = com.google.android.gms.internal.location.zzdd.zza(r8, r0)     // Catch: java.lang.Throwable -> L82
            com.google.android.gms.internal.location.d0 r15 = new com.google.android.gms.internal.location.d0     // Catch: java.lang.Throwable -> L82
            r15.<init>(r2, r12)     // Catch: java.lang.Throwable -> L82
            com.google.android.gms.internal.location.zzdf r0 = new com.google.android.gms.internal.location.zzdf     // Catch: java.lang.Throwable -> L82
            r10 = 1
            r13 = 0
            r14 = 0
            r9 = r0
            r16 = r3
            r9.<init>(r10, r11, r12, r13, r14, r15, r16)     // Catch: java.lang.Throwable -> L82
            r4.zzz(r0)     // Catch: java.lang.Throwable -> L82
        L80:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L82
            return
        L82:
            r0 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L82
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzda.zzv(com.google.android.gms.internal.location.zzcs, com.google.android.gms.location.LocationRequest, com.google.android.gms.tasks.TaskCompletionSource):void");
    }

    public final void zzw(PendingIntent pendingIntent, LocationRequest locationRequest, TaskCompletionSource taskCompletionSource) throws RemoteException {
        getContext();
        if (p(com.google.android.gms.location.zzm.zzj)) {
            ((zzo) getService()).zzk(zzdb.zza(pendingIntent, null, null), locationRequest, new a0(this, null, taskCompletionSource));
            return;
        }
        LocationRequest.Builder builder = new LocationRequest.Builder(locationRequest);
        builder.zzb(null);
        zzdd zza = zzdd.zza(null, builder.build());
        c0 c0Var = new c0(null, taskCompletionSource);
        int hashCode = pendingIntent.hashCode();
        ((zzo) getService()).zzz(new zzdf(1, zza, null, null, pendingIntent, c0Var, "PendingIntent@" + hashCode));
    }

    public final void zzx(PendingIntent pendingIntent, TaskCompletionSource taskCompletionSource) throws RemoteException {
        Preconditions.checkNotNull(pendingIntent, "PendingIntent must be specified.");
        ((zzo) getService()).zzn(pendingIntent, new x(taskCompletionSource), getContext().getPackageName());
    }

    public final void zzy(List list, TaskCompletionSource taskCompletionSource) throws RemoteException {
        Preconditions.checkArgument((list == null || list.isEmpty()) ? false : true, "geofenceRequestIds can't be null nor empty.");
        ((zzo) getService()).zzo((String[]) list.toArray(new String[0]), new x(taskCompletionSource), getContext().getPackageName());
    }

    public final void zzz(Location location, TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (p(com.google.android.gms.location.zzm.zzh)) {
            ((zzo) getService()).zzv(location, new a0(this, null, taskCompletionSource));
            return;
        }
        ((zzo) getService()).zzu(location);
        taskCompletionSource.setResult(null);
    }
}
