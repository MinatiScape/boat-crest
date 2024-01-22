package com.google.android.libraries.places.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
/* loaded from: classes10.dex */
final class zzfh extends zzfi {
    public static final zzfh zza = new zzfh();

    private zzfh() {
        super("CharMatcher.none()");
    }

    @Override // com.google.android.libraries.places.internal.zzfd
    public final int zza(CharSequence charSequence, int i) {
        zzft.zza(i, charSequence.length(), FirebaseAnalytics.Param.INDEX);
        return -1;
    }

    @Override // com.google.android.libraries.places.internal.zzfd
    public final boolean zzb(char c) {
        return false;
    }
}
