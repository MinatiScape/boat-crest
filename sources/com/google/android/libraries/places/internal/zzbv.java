package com.google.android.libraries.places.internal;

import android.location.Location;
import android.text.TextUtils;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPhotoResponse;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesStatusCodes;
import java.util.ArrayList;
/* loaded from: classes10.dex */
public final class zzbv implements zzy {
    private final zzda zza;
    private final zzdr zzb;
    private final zzx zzc;
    private final zzad zzd;
    private final zzcy zze;
    private final zzb zzf;
    private final zzbg zzg;
    private final zzbk zzh;
    private final zzbo zzi;
    private final zzbs zzj;

    public zzbv(zzda zzdaVar, zzdr zzdrVar, zzx zzxVar, zzad zzadVar, zzcy zzcyVar, zzb zzbVar, zzbg zzbgVar, zzbk zzbkVar, zzbo zzboVar, zzbs zzbsVar) {
        this.zza = zzdaVar;
        this.zzb = zzdrVar;
        this.zzc = zzxVar;
        this.zzd = zzadVar;
        this.zze = zzcyVar;
        this.zzf = zzbVar;
        this.zzg = zzbgVar;
        this.zzh = zzbkVar;
        this.zzi = zzboVar;
        this.zzj = zzbsVar;
    }

    @Override // com.google.android.libraries.places.internal.zzy
    public final Task<FindAutocompletePredictionsResponse> zza(FindAutocompletePredictionsRequest findAutocompletePredictionsRequest) {
        String query = findAutocompletePredictionsRequest.getQuery();
        if (query != null && !TextUtils.isEmpty(query.trim())) {
            zzbm zzbmVar = new zzbm(findAutocompletePredictionsRequest, this.zza.zzb(), this.zza.zza(), this.zza.zzc(), this.zzb);
            final long zza = this.zzf.zza();
            return this.zzc.zza(zzbmVar, zzbl.class).continueWith(new Continuation(this) { // from class: com.google.android.libraries.places.internal.zzby
                private final zzbv zza;

                {
                    this.zza = this;
                }

                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return zzbo.zza((zzbl) task.getResult());
                }
            }).continueWith(new Continuation(this, zza) { // from class: com.google.android.libraries.places.internal.zzbx
                private final zzbv zza;
                private final long zzb;

                {
                    this.zza = this;
                    this.zzb = zza;
                }

                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return this.zza.zzd(this.zzb, task);
                }
            });
        }
        return Tasks.forResult(FindAutocompletePredictionsResponse.newInstance(zzgi.zza()));
    }

    public final /* synthetic */ FetchPlaceResponse zzb(long j, Task task) throws Exception {
        this.zze.zzb(task, j, this.zzf.zza());
        return (FetchPlaceResponse) task.getResult();
    }

    public final /* synthetic */ FetchPhotoResponse zzc(long j, Task task) throws Exception {
        this.zze.zzd(task, j, this.zzf.zza());
        return (FetchPhotoResponse) task.getResult();
    }

    public final /* synthetic */ FindAutocompletePredictionsResponse zzd(long j, Task task) throws Exception {
        this.zze.zza(task, j, this.zzf.zza());
        return (FindAutocompletePredictionsResponse) task.getResult();
    }

    @Override // com.google.android.libraries.places.internal.zzy
    public final Task<FetchPhotoResponse> zza(FetchPhotoRequest fetchPhotoRequest) {
        Integer maxWidth = fetchPhotoRequest.getMaxWidth();
        Integer maxHeight = fetchPhotoRequest.getMaxHeight();
        if (maxWidth == null && maxHeight == null) {
            return Tasks.forException(new ApiException(new Status((int) PlacesStatusCodes.INVALID_REQUEST, "Must include max width or max height in request.")));
        }
        if (maxWidth != null && maxWidth.intValue() <= 0) {
            return Tasks.forException(new ApiException(new Status((int) PlacesStatusCodes.INVALID_REQUEST, String.format("Max Width must not be < 1, but was: %d.", maxWidth))));
        }
        if (maxHeight != null && maxHeight.intValue() <= 0) {
            return Tasks.forException(new ApiException(new Status((int) PlacesStatusCodes.INVALID_REQUEST, String.format("Max Height must not be < 1, but was: %d.", maxHeight))));
        }
        zzbc zzbcVar = new zzbc(fetchPhotoRequest, this.zza.zza(), this.zza.zzc(), this.zzb);
        final long zza = this.zzf.zza();
        return this.zzd.zza(zzbcVar, new zzbd()).continueWith(new Continuation(this) { // from class: com.google.android.libraries.places.internal.zzca
            private final zzbv zza;

            {
                this.zza = this;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return FetchPhotoResponse.newInstance(((zzbb) task.getResult()).zza);
            }
        }).continueWith(new Continuation(this, zza) { // from class: com.google.android.libraries.places.internal.zzbz
            private final zzbv zza;
            private final long zzb;

            {
                this.zza = this;
                this.zzb = zza;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.zza.zzc(this.zzb, task);
            }
        });
    }

    @Override // com.google.android.libraries.places.internal.zzy
    public final Task<FetchPlaceResponse> zza(FetchPlaceRequest fetchPlaceRequest) {
        if (TextUtils.isEmpty(fetchPlaceRequest.getPlaceId())) {
            return Tasks.forException(new ApiException(new Status((int) PlacesStatusCodes.INVALID_REQUEST, "Place ID must not be empty.")));
        }
        if (fetchPlaceRequest.getPlaceFields().isEmpty()) {
            return Tasks.forException(new ApiException(new Status((int) PlacesStatusCodes.INVALID_REQUEST, "Place Fields must not be empty.")));
        }
        zzbi zzbiVar = new zzbi(fetchPlaceRequest, this.zza.zzb(), this.zza.zza(), this.zza.zzc(), this.zzb);
        final long zza = this.zzf.zza();
        return this.zzc.zza(zzbiVar, zzbh.class).continueWith(new Continuation(this) { // from class: com.google.android.libraries.places.internal.zzcc
            private final zzbv zza;

            {
                this.zza = this;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                zzbh zzbhVar = (zzbh) task.getResult();
                int zza2 = zzcl.zza(zzbhVar.status);
                if (!PlacesStatusCodes.isError(zza2)) {
                    zzcj zzcjVar = zzbhVar.result;
                    String[] strArr = zzbhVar.htmlAttributions;
                    return FetchPlaceResponse.newInstance(zzci.zza(zzcjVar, strArr != null ? zzgi.zza((Object[]) strArr) : null));
                }
                throw new ApiException(new Status(zza2, zzcl.zza(zzbhVar.status, zzbhVar.errorMessage)));
            }
        }).continueWith(new Continuation(this, zza) { // from class: com.google.android.libraries.places.internal.zzcb
            private final zzbv zza;
            private final long zzb;

            {
                this.zza = this;
                this.zzb = zza;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.zza.zzb(this.zzb, task);
            }
        });
    }

    @Override // com.google.android.libraries.places.internal.zzy
    public final Task<FindCurrentPlaceResponse> zza(FindCurrentPlaceRequest findCurrentPlaceRequest, Location location, zzgi<zzq> zzgiVar) {
        if (findCurrentPlaceRequest.getPlaceFields().isEmpty()) {
            return Tasks.forException(new ApiException(new Status((int) PlacesStatusCodes.INVALID_REQUEST, "Place Fields must not be empty.")));
        }
        zzbq zzbqVar = new zzbq(findCurrentPlaceRequest, location, zzgiVar, this.zza.zzb(), this.zza.zza(), this.zza.zzc(), this.zzb);
        final long zza = this.zzf.zza();
        return this.zzc.zza(zzbqVar, zzbp.class).continueWith(new Continuation(this) { // from class: com.google.android.libraries.places.internal.zzce
            private final zzbv zza;

            {
                this.zza = this;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                zzbp zzbpVar = (zzbp) task.getResult();
                int zza2 = zzcl.zza(zzbpVar.status);
                if (!PlacesStatusCodes.isError(zza2)) {
                    ArrayList arrayList = new ArrayList();
                    zzck[] zzckVarArr = zzbpVar.predictions;
                    if (zzckVarArr != null) {
                        for (zzck zzckVar : zzckVarArr) {
                            if (zzckVar.zzb() != null) {
                                if (zzckVar.zza() != null) {
                                    zzcj zzb = zzckVar.zzb();
                                    String[] strArr = zzbpVar.htmlAttributions;
                                    arrayList.add(PlaceLikelihood.newInstance(zzci.zza(zzb, strArr != null ? zzgi.zza((Object[]) strArr) : null), zzckVar.zza().doubleValue()));
                                } else {
                                    throw new ApiException(new Status(8, "Unexpected server error: PlaceLikelihood returned without a likelihood value"));
                                }
                            } else {
                                throw new ApiException(new Status(8, "Unexpected server error: PlaceLikelihood returned without a Place value"));
                            }
                        }
                    }
                    return FindCurrentPlaceResponse.newInstance(arrayList);
                }
                throw new ApiException(new Status(zza2, zzcl.zza(zzbpVar.status, zzbpVar.errorMessage)));
            }
        }).continueWith(new Continuation(this, zza) { // from class: com.google.android.libraries.places.internal.zzcd
            private final zzbv zza;
            private final long zzb;

            {
                this.zza = this;
                this.zzb = zza;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.zza.zza(this.zzb, task);
            }
        });
    }

    public final /* synthetic */ FindCurrentPlaceResponse zza(long j, Task task) throws Exception {
        this.zze.zzc(task, j, this.zzf.zza());
        return (FindCurrentPlaceResponse) task.getResult();
    }
}
