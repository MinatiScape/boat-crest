package com.google.android.libraries.places.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import com.google.auto.value.AutoValue;
import java.util.List;
@AutoValue
/* loaded from: classes10.dex */
public abstract class zzef {
    @NonNull
    public static zzef zza(@NonNull List<AutocompletePrediction> list) {
        zzft.zza(list);
        return zza(zzeh.SUCCESS_PREDICTIONS).zza(list).zza();
    }

    @NonNull
    public static zzef zzg() {
        return zza(zzeh.START).zza();
    }

    @NonNull
    public static zzef zzh() {
        return zza(zzeh.RESET).zza();
    }

    @NonNull
    public static zzef zzi() {
        return zza(zzeh.LOADING).zza();
    }

    @NonNull
    public static zzef zzj() {
        return zza(zzeh.TRY_AGAIN_PROGRESS_LOADING).zza();
    }

    @NonNull
    public static zzef zzk() {
        return zza(zzeh.FAILURE_UNRESOLVABLE).zza(new Status(16)).zza();
    }

    @NonNull
    public abstract zzeh zza();

    @Nullable
    public abstract String zzb();

    @Nullable
    public abstract zzgi<AutocompletePrediction> zzc();

    @Nullable
    public abstract Place zzd();

    @Nullable
    public abstract AutocompletePrediction zze();

    @Nullable
    public abstract Status zzf();

    @NonNull
    public static zzef zza(@NonNull String str) {
        zzft.zza(str);
        return zza(zzeh.FAILURE_NO_PREDICTIONS).zza(str).zza();
    }

    @NonNull
    public static zzef zza(@NonNull String str, @NonNull Status status) {
        zzft.zza(str);
        zzft.zza(status);
        return zza(zzeh.FAILURE_PREDICTIONS).zza(str).zza(status).zza();
    }

    @NonNull
    public static zzef zza(@NonNull Place place) {
        zzft.zza(place);
        return zza(zzeh.SUCCESS_SELECTION).zza(place).zza();
    }

    @NonNull
    public static zzef zza(@NonNull AutocompletePrediction autocompletePrediction, @NonNull Status status) {
        zzft.zza(autocompletePrediction);
        zzft.zza(status);
        return zza(zzeh.FAILURE_SELECTION).zza(autocompletePrediction).zza(status).zza();
    }

    @NonNull
    public static zzef zza(@NonNull Status status) {
        zzft.zza(status);
        return zza(zzeh.FAILURE_UNRESOLVABLE).zza(status).zza();
    }

    @NonNull
    private static zzee zza(@NonNull zzeh zzehVar) {
        return new zzdz().zza(zzehVar);
    }
}
