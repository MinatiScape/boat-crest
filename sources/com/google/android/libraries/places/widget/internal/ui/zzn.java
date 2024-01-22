package com.google.android.libraries.places.widget.internal.ui;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.internal.zzdk;
/* loaded from: classes10.dex */
final class zzn extends DiffUtil.ItemCallback<AutocompletePrediction> {
    private zzn() {
    }

    private static boolean zza(@NonNull AutocompletePrediction autocompletePrediction, @NonNull AutocompletePrediction autocompletePrediction2) {
        try {
            return autocompletePrediction.getPlaceId().equals(autocompletePrediction2.getPlaceId());
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    @SuppressLint({"DiffUtilEquals"})
    public final /* synthetic */ boolean areContentsTheSame(@NonNull AutocompletePrediction autocompletePrediction, @NonNull AutocompletePrediction autocompletePrediction2) {
        return autocompletePrediction.equals(autocompletePrediction2);
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public final /* synthetic */ boolean areItemsTheSame(@NonNull AutocompletePrediction autocompletePrediction, @NonNull AutocompletePrediction autocompletePrediction2) {
        return zza(autocompletePrediction, autocompletePrediction2);
    }
}
