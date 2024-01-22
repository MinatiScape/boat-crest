package com.google.android.libraries.places.widget.internal.ui;

import com.google.android.libraries.places.internal.zzeh;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
/* loaded from: classes10.dex */
final /* synthetic */ class zzh {
    public static final /* synthetic */ int[] zza;
    public static final /* synthetic */ int[] zzb;

    static {
        int[] iArr = new int[zzeh.values().length];
        zzb = iArr;
        try {
            iArr[zzeh.START.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zzb[zzeh.RESET.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zzb[zzeh.LOADING.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            zzb[zzeh.TRY_AGAIN_PROGRESS_LOADING.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            zzb[zzeh.SUCCESS_PREDICTIONS.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            zzb[zzeh.FAILURE_NO_PREDICTIONS.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            zzb[zzeh.FAILURE_UNRESOLVABLE.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            zzb[zzeh.FAILURE_SELECTION.ordinal()] = 8;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            zzb[zzeh.FAILURE_PREDICTIONS.ordinal()] = 9;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            zzb[zzeh.SUCCESS_SELECTION.ordinal()] = 10;
        } catch (NoSuchFieldError unused10) {
        }
        int[] iArr2 = new int[AutocompleteActivityMode.values().length];
        zza = iArr2;
        try {
            iArr2[AutocompleteActivityMode.FULLSCREEN.ordinal()] = 1;
        } catch (NoSuchFieldError unused11) {
        }
        try {
            zza[AutocompleteActivityMode.OVERLAY.ordinal()] = 2;
        } catch (NoSuchFieldError unused12) {
        }
    }
}
