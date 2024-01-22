package com.google.android.libraries.places.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import java.util.List;
/* loaded from: classes10.dex */
public final class zzes extends ViewModel {
    private final zzek zza;
    private final zzey zzb;
    private final zzfa zzc;
    private final MutableLiveData<zzef> zzd;

    /* loaded from: classes10.dex */
    public static final class zza implements ViewModelProvider.Factory {
        private final zzek zza;
        private final zzey zzb;
        private final zzfa zzc;

        public zza(@NonNull zzek zzekVar, @NonNull zzey zzeyVar, @NonNull zzfa zzfaVar) {
            this.zza = zzekVar;
            this.zzb = zzeyVar;
            this.zzc = zzfaVar;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        @NonNull
        public final <T extends ViewModel> T create(@NonNull Class<T> cls) {
            zzft.zza(cls == zzes.class, "This factory can only be used to instantiate its enclosing class.");
            return new zzes(this.zza, this.zzb, this.zzc);
        }
    }

    private zzes(@NonNull zzek zzekVar, @NonNull zzey zzeyVar, @NonNull zzfa zzfaVar) {
        this.zzd = new MutableLiveData<>();
        this.zza = zzekVar;
        this.zzb = zzeyVar;
        this.zzc = zzfaVar;
    }

    @Override // androidx.lifecycle.ViewModel
    public final void onCleared() {
        try {
            this.zza.zza();
            this.zzb.zzq();
            this.zzc.zza(this.zzb);
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    @NonNull
    public final LiveData<zzef> zza() {
        return this.zzd;
    }

    public final void zzb() {
        this.zzb.zzw();
    }

    public final void zzc() {
        this.zzb.zzv();
        zza("");
    }

    public final void zzd() {
        this.zzb.zzx();
    }

    public final void zze() {
        this.zzb.zzy();
    }

    public final void zzf() {
        this.zzb.zzp();
        zza(zzef.zzk());
    }

    public final void zza(@Nullable Bundle bundle) {
        if (bundle == null) {
            this.zzd.setValue(zzef.zzg());
        }
    }

    public final void zzb(@NonNull String str) {
        this.zza.zza();
        zza(str);
        zza(zzef.zzj());
    }

    public final void zza(@NonNull final String str) {
        this.zzb.zza(str);
        if (str.isEmpty()) {
            this.zza.zza();
            zza(zzef.zzh());
            return;
        }
        Task<FindAutocompletePredictionsResponse> zza2 = this.zza.zza(str);
        if (!zza2.isComplete()) {
            zza(zzef.zzi());
        }
        zza2.addOnCompleteListener(new OnCompleteListener(this, str) { // from class: com.google.android.libraries.places.internal.zzev
            private final zzes zza;
            private final String zzb;

            {
                this.zza = this;
                this.zzb = str;
            }

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.zza.zza(this.zzb, task);
            }
        });
    }

    public final void zza(@NonNull final AutocompletePrediction autocompletePrediction, int i) {
        this.zzb.zza(i);
        Task<FetchPlaceResponse> zza2 = this.zza.zza(autocompletePrediction);
        if (!zza2.isComplete()) {
            zza(zzef.zzi());
        }
        zza2.addOnCompleteListener(new OnCompleteListener(this, autocompletePrediction) { // from class: com.google.android.libraries.places.internal.zzeu
            private final zzes zza;
            private final AutocompletePrediction zzb;

            {
                this.zza = this;
                this.zzb = autocompletePrediction;
            }

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.zza.zza(this.zzb, task);
            }
        });
    }

    private final void zza(@NonNull zzef zzefVar) {
        if (zzefVar.equals(this.zzd.getValue())) {
            return;
        }
        this.zzd.setValue(zzefVar);
    }

    @NonNull
    private static Status zza(@NonNull Exception exc) {
        if (exc instanceof ApiException) {
            return ((ApiException) exc).getStatus();
        }
        return new Status(13, exc.getMessage());
    }

    private static boolean zza(@NonNull Status status) {
        return status.isCanceled() || status.getStatusCode() == 9012 || status.getStatusCode() == 9011;
    }

    public final /* synthetic */ void zza(AutocompletePrediction autocompletePrediction, Task task) {
        if (task.isCanceled()) {
            return;
        }
        Exception exception = task.getException();
        if (exception == null) {
            this.zzb.zzt();
            zza(zzef.zza(((FetchPlaceResponse) task.getResult()).getPlace()));
            return;
        }
        this.zzb.zzu();
        Status zza2 = zza(exception);
        if (zza(zza2)) {
            zza(zzef.zza(zza2));
        } else {
            zza(zzef.zza(autocompletePrediction, zza2));
        }
    }

    public final /* synthetic */ void zza(String str, Task task) {
        if (task.isCanceled()) {
            return;
        }
        Exception exception = task.getException();
        if (exception == null) {
            this.zzb.zzr();
            List<AutocompletePrediction> autocompletePredictions = ((FindAutocompletePredictionsResponse) task.getResult()).getAutocompletePredictions();
            if (autocompletePredictions.isEmpty()) {
                zza(zzef.zza(str));
                return;
            } else {
                zza(zzef.zza(autocompletePredictions));
                return;
            }
        }
        this.zzb.zzs();
        Status zza2 = zza(exception);
        if (zza(zza2)) {
            zza(zzef.zza(zza2));
        } else {
            zza(zzef.zza(str, zza2));
        }
    }
}
