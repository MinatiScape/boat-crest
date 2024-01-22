package com.google.android.gms.internal.firebase_ml;

import java.util.ArrayDeque;
import java.util.Arrays;
/* loaded from: classes7.dex */
public final class a8 {

    /* renamed from: a */
    public final ArrayDeque<zzvv> f8664a;

    public a8() {
        this.f8664a = new ArrayDeque<>();
    }

    public static /* synthetic */ zzvv a(a8 a8Var, zzvv zzvvVar, zzvv zzvvVar2) {
        return a8Var.b(zzvvVar, zzvvVar2);
    }

    public static int c(int i) {
        int binarySearch = Arrays.binarySearch(y7.zzcox, i);
        return binarySearch < 0 ? (-(binarySearch + 1)) - 1 : binarySearch;
    }

    public final zzvv b(zzvv zzvvVar, zzvv zzvvVar2) {
        d(zzvvVar);
        d(zzvvVar2);
        zzvv pop = this.f8664a.pop();
        while (!this.f8664a.isEmpty()) {
            pop = new y7(this.f8664a.pop(), pop, null);
        }
        return pop;
    }

    public final void d(zzvv zzvvVar) {
        zzvv zzvvVar2;
        while (!zzvvVar.zzts()) {
            if (zzvvVar instanceof y7) {
                y7 y7Var = (y7) zzvvVar;
                zzvvVar2 = y7Var.zzcoz;
                d(zzvvVar2);
                zzvvVar = y7Var.zzcpa;
            } else {
                String valueOf = String.valueOf(zzvvVar.getClass());
                StringBuilder sb = new StringBuilder(valueOf.length() + 49);
                sb.append("Has a new type of ByteString been created? Found ");
                sb.append(valueOf);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        int c = c(zzvvVar.size());
        int zzdz = y7.zzdz(c + 1);
        if (!this.f8664a.isEmpty() && this.f8664a.peek().size() < zzdz) {
            int zzdz2 = y7.zzdz(c);
            zzvv pop = this.f8664a.pop();
            while (!this.f8664a.isEmpty() && this.f8664a.peek().size() < zzdz2) {
                pop = new y7(this.f8664a.pop(), pop, null);
            }
            y7 y7Var2 = new y7(pop, zzvvVar, null);
            while (!this.f8664a.isEmpty()) {
                if (this.f8664a.peek().size() >= y7.zzdz(c(y7Var2.size()) + 1)) {
                    break;
                }
                y7Var2 = new y7(this.f8664a.pop(), y7Var2, null);
            }
            this.f8664a.push(y7Var2);
            return;
        }
        this.f8664a.push(zzvvVar);
    }

    public /* synthetic */ a8(b8 b8Var) {
        this();
    }
}
