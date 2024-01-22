package com.google.android.libraries.places.widget.internal.ui;

import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.libraries.places.internal.zzdk;
/* loaded from: classes10.dex */
final class zzf extends RecyclerView.OnScrollListener {
    private final /* synthetic */ AutocompleteImplFragment zza;

    public zzf(AutocompleteImplFragment autocompleteImplFragment) {
        this.zza = autocompleteImplFragment;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public final void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
        EditText editText;
        if (i == 1) {
            try {
                this.zza.zze.zzb();
                editText = this.zza.zzg;
                editText.clearFocus();
            } catch (Error | RuntimeException e) {
                zzdk.zza(e);
                throw e;
            }
        }
    }
}
