package com.google.android.libraries.places.internal;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
/* loaded from: classes10.dex */
public final class zzen implements zzek {
    private static final zzgi<Place.Field> zza = zzgi.zza(Place.Field.ID, Place.Field.TYPES);
    private final PlacesClient zzb;
    private final zzed zzc;
    private final AutocompleteSessionToken zzd;
    private zzer zze;
    private zzeq zzf;

    public zzen(PlacesClient placesClient, zzed zzedVar, AutocompleteSessionToken autocompleteSessionToken) {
        this.zzb = placesClient;
        this.zzc = zzedVar;
        this.zzd = autocompleteSessionToken;
    }

    @Override // com.google.android.libraries.places.internal.zzek
    @NonNull
    public final Task<FindAutocompletePredictionsResponse> zza(@NonNull String str) {
        zzft.zza(!TextUtils.isEmpty(str));
        zzer zzerVar = this.zze;
        if (zzerVar != null) {
            if (zzerVar.zzb().equals(str)) {
                return (Task) zzft.zza(zzerVar.zzc());
            }
            zzerVar.zza().cancel();
        }
        final zzei zzeiVar = new zzei(new CancellationTokenSource(), str);
        this.zze = zzeiVar;
        Task continueWithTask = this.zzb.findAutocompletePredictions(FindAutocompletePredictionsRequest.builder().setQuery(str).setLocationBias(this.zzc.zzf()).setLocationRestriction(this.zzc.zzg()).setCountries(this.zzc.zzh()).setTypeFilter(this.zzc.zzi()).setSessionToken(this.zzd).setCancellationToken(zzeiVar.zza().getToken()).build()).continueWithTask(new Continuation(zzeiVar) { // from class: com.google.android.libraries.places.internal.zzem
            private final zzer zza;

            {
                this.zza = zzeiVar;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return zzen.zza(this.zza, task);
            }
        });
        zzeiVar.zza(continueWithTask);
        return continueWithTask;
    }

    @Override // com.google.android.libraries.places.internal.zzek
    @NonNull
    public final Task<FetchPlaceResponse> zza(@NonNull AutocompletePrediction autocompletePrediction) {
        if (zza.containsAll(this.zzc.zzb())) {
            return Tasks.forResult(FetchPlaceResponse.newInstance(Place.builder().setId(autocompletePrediction.getPlaceId()).setTypes(autocompletePrediction.getPlaceTypes().isEmpty() ? null : autocompletePrediction.getPlaceTypes()).build()));
        }
        zzeq zzeqVar = this.zzf;
        if (zzeqVar != null) {
            if (zzeqVar.zzb().equals(autocompletePrediction.getPlaceId())) {
                return (Task) zzft.zza(zzeqVar.zzc());
            }
            zzeqVar.zza().cancel();
        }
        final zzel zzelVar = new zzel(new CancellationTokenSource(), autocompletePrediction.getPlaceId());
        this.zzf = zzelVar;
        Task continueWithTask = this.zzb.fetchPlace(FetchPlaceRequest.builder(autocompletePrediction.getPlaceId(), this.zzc.zzb()).setSessionToken(this.zzd).setCancellationToken(zzelVar.zza().getToken()).build()).continueWithTask(new Continuation(zzelVar) { // from class: com.google.android.libraries.places.internal.zzep
            private final zzeq zza;

            {
                this.zza = zzelVar;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return zzen.zza(this.zza, task);
            }
        });
        zzelVar.zza(continueWithTask);
        return continueWithTask;
    }

    @Override // com.google.android.libraries.places.internal.zzek
    public final void zza() {
        zzer zzerVar = this.zze;
        if (zzerVar != null) {
            zzerVar.zza().cancel();
        }
        zzeq zzeqVar = this.zzf;
        if (zzeqVar != null) {
            zzeqVar.zza().cancel();
        }
        this.zze = null;
        this.zzf = null;
    }

    public static final /* synthetic */ Task zza(zzeq zzeqVar, Task task) throws Exception {
        return zzeqVar.zza().getToken().isCancellationRequested() ? Tasks.forCanceled() : task;
    }

    public static final /* synthetic */ Task zza(zzer zzerVar, Task task) throws Exception {
        return zzerVar.zza().getToken().isCancellationRequested() ? Tasks.forCanceled() : task;
    }
}
