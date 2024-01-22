package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.ArrayDeque;
import java.util.Arrays;
/* loaded from: classes8.dex */
public final class i1 {

    /* renamed from: a */
    public final ArrayDeque f9599a = new ArrayDeque();

    public /* synthetic */ i1(zzgb zzgbVar) {
    }

    public static /* bridge */ /* synthetic */ zzdb a(i1 i1Var, zzdb zzdbVar, zzdb zzdbVar2) {
        i1Var.b(zzdbVar);
        i1Var.b(zzdbVar2);
        zzdb zzdbVar3 = (zzdb) i1Var.f9599a.pop();
        while (!i1Var.f9599a.isEmpty()) {
            zzdbVar3 = new k1((zzdb) i1Var.f9599a.pop(), zzdbVar3);
        }
        return zzdbVar3;
    }

    public static final int c(int i) {
        int binarySearch = Arrays.binarySearch(k1.zza, i);
        return binarySearch < 0 ? (-(binarySearch + 1)) - 1 : binarySearch;
    }

    public final void b(zzdb zzdbVar) {
        zzdb zzdbVar2;
        zzdb zzdbVar3;
        if (zzdbVar.zzh()) {
            int c = c(zzdbVar.zzd());
            int zzc = k1.zzc(c + 1);
            if (!this.f9599a.isEmpty() && ((zzdb) this.f9599a.peek()).zzd() < zzc) {
                int zzc2 = k1.zzc(c);
                zzdb zzdbVar4 = (zzdb) this.f9599a.pop();
                while (!this.f9599a.isEmpty() && ((zzdb) this.f9599a.peek()).zzd() < zzc2) {
                    zzdbVar4 = new k1((zzdb) this.f9599a.pop(), zzdbVar4);
                }
                k1 k1Var = new k1(zzdbVar4, zzdbVar);
                while (!this.f9599a.isEmpty()) {
                    if (((zzdb) this.f9599a.peek()).zzd() >= k1.zzc(c(k1Var.zzd()) + 1)) {
                        break;
                    }
                    k1Var = new k1((zzdb) this.f9599a.pop(), k1Var);
                }
                this.f9599a.push(k1Var);
                return;
            }
            this.f9599a.push(zzdbVar);
        } else if (zzdbVar instanceof k1) {
            k1 k1Var2 = (k1) zzdbVar;
            zzdbVar2 = k1Var2.zzd;
            b(zzdbVar2);
            zzdbVar3 = k1Var2.zze;
            b(zzdbVar3);
        } else {
            throw new IllegalArgumentException("Has a new type of ByteString been created? Found ".concat(String.valueOf(zzdbVar.getClass())));
        }
    }
}
