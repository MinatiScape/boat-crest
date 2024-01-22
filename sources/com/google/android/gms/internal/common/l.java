package com.google.android.gms.internal.common;

import com.google.firebase.analytics.FirebaseAnalytics;
/* loaded from: classes7.dex */
public final class l extends o {
    public final /* synthetic */ m n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(m mVar, zzx zzxVar, CharSequence charSequence) {
        super(zzxVar, charSequence);
        this.n = mVar;
    }

    @Override // com.google.android.gms.internal.common.o
    public final int c(int i) {
        return i + 1;
    }

    @Override // com.google.android.gms.internal.common.o
    public final int d(int i) {
        zzo zzoVar = this.n.f8628a;
        CharSequence charSequence = this.j;
        int length = charSequence.length();
        zzs.zzb(i, length, FirebaseAnalytics.Param.INDEX);
        while (i < length) {
            if (zzoVar.zza(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
