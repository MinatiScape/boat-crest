package com.google.android.libraries.places.internal;

import android.location.Location;
import android.os.Build;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;
/* loaded from: classes10.dex */
public final class zzk {
    private static final long zza;
    private static final long zzb;
    private static final long zzc;
    private final zzb zzd;
    private final FusedLocationProviderClient zze;
    private final zzcr zzf;

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        zza = timeUnit.toMillis(10L);
        zzb = timeUnit.toNanos(24L);
        zzc = timeUnit.toMillis(59L);
    }

    public zzk(zzb zzbVar, FusedLocationProviderClient fusedLocationProviderClient, zzcr zzcrVar) {
        this.zzd = zzbVar;
        this.zze = fusedLocationProviderClient;
        this.zzf = zzcrVar;
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public final Task<Location> zza(@Nullable final CancellationToken cancellationToken) {
        return this.zzf.zza(this.zze.getLastLocation(), cancellationToken, zza, "Location timeout.").continueWithTask(new Continuation(this, cancellationToken) { // from class: com.google.android.libraries.places.internal.zzj
            private final zzk zza;
            private final CancellationToken zzb;

            {
                this.zza = this;
                this.zzb = cancellationToken;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.zza.zza(this.zzb, task);
            }
        });
    }

    public final /* synthetic */ void zza(LocationCallback locationCallback, TaskCompletionSource taskCompletionSource, Task task) {
        this.zze.removeLocationUpdates(locationCallback);
        this.zzf.zza(taskCompletionSource);
    }

    public static /* synthetic */ Task zza(TaskCompletionSource taskCompletionSource, Task task) throws Exception {
        if (task.isComplete()) {
            if (task.isCanceled()) {
                taskCompletionSource.trySetException(new ApiException(new Status(16, "Location request was cancelled. Please try again.")));
            } else if (!task.isSuccessful()) {
                taskCompletionSource.trySetException(new ApiException(new Status(8, task.getException().getMessage())));
            }
        }
        return task;
    }

    public final /* synthetic */ Task zza(CancellationToken cancellationToken, Task task) throws Exception {
        final TaskCompletionSource taskCompletionSource;
        if (task.isSuccessful()) {
            zzb zzbVar = this.zzd;
            Location location = (Location) task.getResult();
            boolean z = false;
            if (location != null && (Build.VERSION.SDK_INT < 17 || zzbVar.zzb() - location.getElapsedRealtimeNanos() <= zzb)) {
                z = true;
            }
            if (z) {
                return task;
            }
        }
        if (cancellationToken != null) {
            taskCompletionSource = new TaskCompletionSource(cancellationToken);
        } else {
            taskCompletionSource = new TaskCompletionSource();
        }
        LocationRequest priority = LocationRequest.create().setPriority(100);
        long j = zza;
        LocationRequest numUpdates = priority.setExpirationDuration(j).setInterval(zzc).setFastestInterval(10L).setNumUpdates(1);
        final zzo zzoVar = new zzo(this, taskCompletionSource);
        this.zze.requestLocationUpdates(numUpdates, zzoVar, Looper.getMainLooper()).continueWithTask(new Continuation(this, taskCompletionSource) { // from class: com.google.android.libraries.places.internal.zzm
            private final zzk zza;
            private final TaskCompletionSource zzb;

            {
                this.zza = this;
                this.zzb = taskCompletionSource;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task2) {
                return zzk.zza(this.zzb, task2);
            }
        });
        this.zzf.zza(taskCompletionSource, j, "Location timeout.");
        taskCompletionSource.getTask().addOnCompleteListener(new OnCompleteListener(this, zzoVar, taskCompletionSource) { // from class: com.google.android.libraries.places.internal.zzl
            private final zzk zza;
            private final LocationCallback zzb;
            private final TaskCompletionSource zzc;

            {
                this.zza = this;
                this.zzb = zzoVar;
                this.zzc = taskCompletionSource;
            }

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task2) {
                this.zza.zza(this.zzb, this.zzc, task2);
            }
        });
        return taskCompletionSource.getTask();
    }
}
