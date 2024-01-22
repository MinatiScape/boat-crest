package com.google.android.libraries.places.widget.internal.ui;

import androidx.activity.OnBackPressedCallback;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzg extends OnBackPressedCallback {
    private final /* synthetic */ AutocompleteImplFragment zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzg(AutocompleteImplFragment autocompleteImplFragment, boolean z) {
        super(true);
        this.zza = autocompleteImplFragment;
    }

    @Override // androidx.activity.OnBackPressedCallback
    public final void handleOnBackPressed() {
        this.zza.zze.zzf();
    }
}
