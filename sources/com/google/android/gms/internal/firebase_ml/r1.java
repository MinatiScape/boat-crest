package com.google.android.gms.internal.firebase_ml;

import com.google.firebase.analytics.FirebaseAnalytics;
/* loaded from: classes7.dex */
public final class r1 extends p1 {
    public static final r1 b = new r1();

    public r1() {
        super("CharMatcher.none()");
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzlw
    public final int zza(CharSequence charSequence, int i) {
        zzml.zza(i, charSequence.length(), FirebaseAnalytics.Param.INDEX);
        return -1;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzlw
    public final boolean zzb(char c) {
        return false;
    }
}
