package com.google.android.libraries.places.widget;

import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
/* loaded from: classes10.dex */
final /* synthetic */ class zzb {
    public static final /* synthetic */ int[] zza;

    static {
        int[] iArr = new int[AutocompleteActivityMode.values().length];
        zza = iArr;
        try {
            iArr[AutocompleteActivityMode.OVERLAY.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zza[AutocompleteActivityMode.FULLSCREEN.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
    }
}
