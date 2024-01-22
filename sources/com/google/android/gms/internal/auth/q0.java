package com.google.android.gms.internal.auth;

import java.util.Comparator;
/* loaded from: classes6.dex */
public final class q0 implements Comparator {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzee zzeeVar = (zzee) obj;
        zzee zzeeVar2 = (zzee) obj2;
        p0 p0Var = new p0(zzeeVar);
        p0 p0Var2 = new p0(zzeeVar2);
        while (p0Var.hasNext() && p0Var2.hasNext()) {
            int compareTo = Integer.valueOf(p0Var.zza() & 255).compareTo(Integer.valueOf(p0Var2.zza() & 255));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return Integer.valueOf(zzeeVar.zzd()).compareTo(Integer.valueOf(zzeeVar2.zzd()));
    }
}
