package com.google.android.gms.analytics;

import java.util.Comparator;
/* loaded from: classes6.dex */
public final class c implements Comparator<zzj> {
    public c(zzg zzgVar) {
    }

    @Override // java.util.Comparator
    public final /* bridge */ /* synthetic */ int compare(zzj zzjVar, zzj zzjVar2) {
        return zzjVar.getClass().getCanonicalName().compareTo(zzjVar2.getClass().getCanonicalName());
    }
}
