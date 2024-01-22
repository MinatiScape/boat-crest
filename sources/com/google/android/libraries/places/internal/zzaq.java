package com.google.android.libraries.places.internal;

import android.location.Location;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPhotoResponse;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
/* loaded from: classes10.dex */
public final class zzaq implements PlacesClient {
    private final zzy zza;
    private final zzk zzb;
    private final zzs zzc;
    private final zzcy zzd;
    private final zzb zze;

    public zzaq(zzy zzyVar, zzk zzkVar, zzs zzsVar, zzcy zzcyVar, zzb zzbVar) {
        this.zza = zzyVar;
        this.zzb = zzkVar;
        this.zzc = zzsVar;
        this.zzd = zzcyVar;
        this.zze = zzbVar;
    }

    private static void zza(zzh zzhVar, @Nullable zzg zzgVar) {
        if (zzgVar != null) {
            zzf.zza().zza(zzgVar, zzh.zza(zzhVar, zzh.zza("Duration")));
        }
        zzf.zza().zza(zzhVar);
        zzf.zza().zzb(zzh.zza(zzhVar, zzh.zza("Battery")));
    }

    @Override // com.google.android.libraries.places.api.net.PlacesClient
    @NonNull
    public final Task<FetchPhotoResponse> fetchPhoto(@NonNull final FetchPhotoRequest fetchPhotoRequest) {
        try {
            zzft.zza(fetchPhotoRequest, "Request must not be null.");
            final zzg zzb = zzf.zza().zzb();
            return this.zza.zza(fetchPhotoRequest).continueWith(new Continuation(this, fetchPhotoRequest, zzb) { // from class: com.google.android.libraries.places.internal.zzar
                private final zzaq zza;
                private final FetchPhotoRequest zzb;
                private final zzg zzc;

                {
                    this.zza = this;
                    this.zzb = fetchPhotoRequest;
                    this.zzc = zzb;
                }

                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return this.zza.zza(this.zzb, this.zzc, task);
                }
            }).continueWithTask(zzau.zza);
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    @Override // com.google.android.libraries.places.api.net.PlacesClient
    @NonNull
    public final Task<FetchPlaceResponse> fetchPlace(@NonNull final FetchPlaceRequest fetchPlaceRequest) {
        try {
            zzft.zza(fetchPlaceRequest, "Request must not be null.");
            final zzg zzb = zzf.zza().zzb();
            return this.zza.zza(fetchPlaceRequest).continueWith(new Continuation(this, fetchPlaceRequest, zzb) { // from class: com.google.android.libraries.places.internal.zzat
                private final zzaq zza;
                private final FetchPlaceRequest zzb;
                private final zzg zzc;

                {
                    this.zza = this;
                    this.zzb = fetchPlaceRequest;
                    this.zzc = zzb;
                }

                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return this.zza.zza(this.zzb, this.zzc, task);
                }
            }).continueWithTask(zzaw.zza);
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    @Override // com.google.android.libraries.places.api.net.PlacesClient
    @NonNull
    public final Task<FindAutocompletePredictionsResponse> findAutocompletePredictions(@NonNull final FindAutocompletePredictionsRequest findAutocompletePredictionsRequest) {
        try {
            zzft.zza(findAutocompletePredictionsRequest, "Request must not be null.");
            final zzg zzb = zzf.zza().zzb();
            return this.zza.zza(findAutocompletePredictionsRequest).continueWith(new Continuation(this, findAutocompletePredictionsRequest, zzb) { // from class: com.google.android.libraries.places.internal.zzap
                private final zzaq zza;
                private final FindAutocompletePredictionsRequest zzb;
                private final zzg zzc;

                {
                    this.zza = this;
                    this.zzb = findAutocompletePredictionsRequest;
                    this.zzc = zzb;
                }

                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return this.zza.zza(this.zzb, this.zzc, task);
                }
            }).continueWithTask(zzas.zza);
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    @Override // com.google.android.libraries.places.api.net.PlacesClient
    @NonNull
    @RequiresPermission(allOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_WIFI_STATE"})
    public final Task<FindCurrentPlaceResponse> findCurrentPlace(@NonNull final FindCurrentPlaceRequest findCurrentPlaceRequest) {
        try {
            zzft.zza(findCurrentPlaceRequest, "Request must not be null.");
            final long zza = this.zze.zza();
            final zzg zzb = zzf.zza().zzb();
            return this.zzb.zza(findCurrentPlaceRequest.getCancellationToken()).onSuccessTask(new SuccessContinuation(this, findCurrentPlaceRequest) { // from class: com.google.android.libraries.places.internal.zzav
                private final zzaq zza;
                private final FindCurrentPlaceRequest zzb;

                {
                    this.zza = this;
                    this.zzb = findCurrentPlaceRequest;
                }

                @Override // com.google.android.gms.tasks.SuccessContinuation
                public final Task then(Object obj) {
                    return this.zza.zza(this.zzb, (Location) obj);
                }
            }).continueWith(new Continuation(this, findCurrentPlaceRequest, zza, zzb) { // from class: com.google.android.libraries.places.internal.zzay
                private final zzaq zza;
                private final FindCurrentPlaceRequest zzb;
                private final long zzc;
                private final zzg zzd;

                {
                    this.zza = this;
                    this.zzb = findCurrentPlaceRequest;
                    this.zzc = zza;
                    this.zzd = zzb;
                }

                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return this.zza.zza(this.zzb, this.zzc, this.zzd, task);
                }
            }).continueWithTask(zzax.zza);
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    public final /* synthetic */ FindCurrentPlaceResponse zza(FindCurrentPlaceRequest findCurrentPlaceRequest, long j, zzg zzgVar, Task task) throws Exception {
        this.zzd.zza(findCurrentPlaceRequest, task, j, this.zze.zza());
        zza(zzh.zza("FindCurrentPlace"), zzgVar);
        return (FindCurrentPlaceResponse) task.getResult();
    }

    public final /* synthetic */ Task zza(FindCurrentPlaceRequest findCurrentPlaceRequest, Location location) throws Exception {
        return this.zza.zza(findCurrentPlaceRequest, location, this.zzc.zza());
    }

    public final /* synthetic */ FetchPlaceResponse zza(FetchPlaceRequest fetchPlaceRequest, zzg zzgVar, Task task) throws Exception {
        this.zzd.zza(fetchPlaceRequest);
        zza(zzh.zza("FetchPlace"), zzgVar);
        return (FetchPlaceResponse) task.getResult();
    }

    public final /* synthetic */ FetchPhotoResponse zza(FetchPhotoRequest fetchPhotoRequest, zzg zzgVar, Task task) throws Exception {
        this.zzd.zza(fetchPhotoRequest);
        zza(zzh.zza("FetchPhoto"), zzgVar);
        return (FetchPhotoResponse) task.getResult();
    }

    public final /* synthetic */ FindAutocompletePredictionsResponse zza(FindAutocompletePredictionsRequest findAutocompletePredictionsRequest, zzg zzgVar, Task task) throws Exception {
        this.zzd.zza(findAutocompletePredictionsRequest);
        zza(zzh.zza("FindAutocompletePredictions"), zzgVar);
        return (FindAutocompletePredictionsResponse) task.getResult();
    }

    public static final /* synthetic */ Task zza(Task task) {
        ApiException apiException;
        Exception exception = task.getException();
        if (exception != null) {
            if (exception instanceof ApiException) {
                apiException = (ApiException) exception;
            } else {
                apiException = new ApiException(new Status(13, exception.toString()));
            }
            return Tasks.forException(apiException);
        }
        return task;
    }
}
