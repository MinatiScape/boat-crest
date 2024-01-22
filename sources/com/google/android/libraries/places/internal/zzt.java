package com.google.android.libraries.places.internal;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPhotoResponse;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.internal.zzmq;
import java.util.Locale;
/* loaded from: classes10.dex */
public final class zzt implements zzcy {
    private final zzdn zza;
    private final zzdj zzb;
    private final zzds zzc;

    public zzt(zzdn zzdnVar, zzdj zzdjVar, zzds zzdsVar) {
        this.zza = zzdnVar;
        this.zzb = zzdjVar;
        this.zzc = zzdsVar;
    }

    @Override // com.google.android.libraries.places.internal.zzcy
    public final void zza(FindAutocompletePredictionsRequest findAutocompletePredictionsRequest) {
        zzmq.zzk.zza zza = zzmq.zzk.zza();
        if (findAutocompletePredictionsRequest.getTypeFilter() != null) {
            zza.zza(zzcp.zza(findAutocompletePredictionsRequest.getTypeFilter()));
        }
        zzmq.zzk zzkVar = (zzmq.zzk) ((zzsf) zza.zzg());
        zzmq.zzo.zza zza2 = zzmq.zzo.zza();
        if (zzkVar != null) {
            zza2.zza(zzkVar);
        }
        zzmq.zzah.zzb zza3 = zza().zza(zzmq.zzah.zza.AUTOCOMPLETE);
        zzmq.zzs.zza zza4 = zzdq.zza(this.zzb).zza(zzmq.zzs.zzc.PLACES_QUERY).zza((zzmq.zzah) ((zzsf) zza3.zza((zzmq.zzo) ((zzsf) zza2.zzg())).zzg()));
        if (findAutocompletePredictionsRequest.getSessionToken() != null) {
            zza4.zza(findAutocompletePredictionsRequest.getSessionToken().toString());
        }
        zza((zzmq.zzs) ((zzsf) zza4.zzg()));
    }

    @Override // com.google.android.libraries.places.internal.zzcy
    public final void zzb(Task<FetchPlaceResponse> task, long j, long j2) {
        boolean isSuccessful = task.isSuccessful();
        zza((zzmq.zzi) ((zzsf) zzmq.zzi.zza().zza(zzmq.zzi.zzd.GET_PLACE_BY_ID).zza((zzmq.zzf) ((zzsf) zzmq.zzf.zza().zza(1).zzb(isSuccessful ? 1 : 0).zzg())).zza(zza(task)).zza((int) (j2 - j)).zzg()));
    }

    @Override // com.google.android.libraries.places.internal.zzcy
    public final void zzc(Task<FindCurrentPlaceResponse> task, long j, long j2) {
        zza((zzmq.zzi) ((zzsf) zzmq.zzi.zza().zza(zzmq.zzi.zzd.ESTIMATE_PLACES_BY_LOCATION).zza((zzmq.zzb) ((zzsf) zzmq.zzb.zza().zza(task.isSuccessful() ? task.getResult().getPlaceLikelihoods().size() : 0).zzg())).zza(zza(task)).zza((int) (j2 - j)).zzg()));
    }

    @Override // com.google.android.libraries.places.internal.zzcy
    public final void zzd(Task<FetchPhotoResponse> task, long j, long j2) {
        zza((zzmq.zzi) ((zzsf) zzmq.zzi.zza().zza(zzmq.zzi.zzd.GET_PHOTO).zza(zza(task)).zza((int) (j2 - j)).zzg()));
    }

    @Override // com.google.android.libraries.places.internal.zzcy
    public final void zza(Task<FindAutocompletePredictionsResponse> task, long j, long j2) {
        zza((zzmq.zzi) ((zzsf) zzmq.zzi.zza().zza(zzmq.zzi.zzd.GET_AUTOCOMPLETE_PREDICTIONS).zza((zzmq.zzd) ((zzsf) zzmq.zzd.zza().zza(task.isSuccessful() ? task.getResult().getAutocompletePredictions().size() : 0).zzg())).zza(zza(task)).zza((int) (j2 - j)).zzg()));
    }

    @Override // com.google.android.libraries.places.internal.zzcy
    public final void zza(FetchPlaceRequest fetchPlaceRequest) {
        zzmq.zzah.zzb zza = zza().zza(zzmq.zzah.zza.BY_ID);
        zzmq.zzs.zza zza2 = zzdq.zza(this.zzb).zza(zzmq.zzs.zzc.PLACES_QUERY).zza((zzmq.zzah) ((zzsf) zza.zza((zzmq.zzv) ((zzsf) zzmq.zzv.zza().zza(1).zza((zzmq.zzaf) ((zzsf) zzmq.zzaf.zza().zza(zzcm.zza(fetchPlaceRequest.getPlaceFields())).zzg())).zzg())).zzg()));
        if (fetchPlaceRequest.getSessionToken() != null) {
            zza2.zza(fetchPlaceRequest.getSessionToken().toString());
        }
        zza((zzmq.zzs) ((zzsf) zza2.zzg()));
    }

    @Override // com.google.android.libraries.places.internal.zzcy
    public final void zza(FindCurrentPlaceRequest findCurrentPlaceRequest, Task<FindCurrentPlaceResponse> task, long j, long j2) {
        zzmq.zzy.zza zzaVar;
        if (task.isSuccessful()) {
            zzaVar = zzmq.zzy.zza.NEARBY_SEARCH;
        } else {
            zzaVar = zzmq.zzy.zza.NO_RESULT;
        }
        zza((zzmq.zzs) ((zzsf) zzdq.zza(this.zzb).zza(zzmq.zzs.zzc.GET_CURRENT_PLACE).zza((zzmq.zzy) ((zzsf) zzmq.zzy.zza().zza((zzmq.zzaf) ((zzsf) zzmq.zzaf.zza().zza(zzcm.zza(findCurrentPlaceRequest.getPlaceFields())).zzg())).zza((int) (j2 - j)).zza(zzaVar).zzg())).zzg()));
    }

    @Override // com.google.android.libraries.places.internal.zzcy
    public final void zza(FetchPhotoRequest fetchPhotoRequest) {
        zza((zzmq.zzs) ((zzsf) zzdq.zza(this.zzb).zza(zzmq.zzs.zzc.PLACE_PHOTO_QUERY).zza((zzmq.zzae) ((zzsf) zzmq.zzae.zza().zza(zzmq.zzae.zza.PHOTO_IMAGE).zzg())).zzg()));
    }

    private final zzmq.zzah.zzb zza() {
        Locale zzb = this.zzc.zzb();
        Locale locale = Locale.getDefault();
        zzmq.zzah.zzb zza = zzmq.zzah.zza().zza(zzb.toString());
        if (!zzb.equals(locale)) {
            zza.zzb(locale.toString());
        }
        return zza;
    }

    private final void zza(zzmq.zzi zziVar) {
        zza((zzmq.zzs) ((zzsf) zzdq.zza(this.zzb).zza(zzmq.zzs.zzc.NETWORK_REQUEST_EVENT).zza(zziVar).zzg()));
    }

    private final void zza(zzmq.zzs zzsVar) {
        this.zza.zza(zzdq.zza(zzsVar));
    }

    @VisibleForTesting
    private static <ResponseT> zzmq.zzi.zzf zza(Task<ResponseT> task) {
        ApiException apiException;
        if (task.isSuccessful()) {
            return zzmq.zzi.zzf.SUCCESS;
        }
        Exception exception = task.getException();
        if (exception instanceof ApiException) {
            apiException = (ApiException) exception;
        } else {
            apiException = new ApiException(new Status(13, exception.getMessage()));
        }
        int statusCode = apiException.getStatusCode();
        if (statusCode != 7) {
            if (statusCode != 15) {
                return zzmq.zzi.zzf.INVALID;
            }
            return zzmq.zzi.zzf.TIMEOUT;
        }
        return zzmq.zzi.zzf.NETWORK_ERROR;
    }
}
