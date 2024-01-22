package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.concurrent.RunnableFuture;
import javax.annotation.CheckForNull;
/* loaded from: classes9.dex */
public final class b2 extends s1 implements RunnableFuture {
    @CheckForNull
    public volatile z1 o;

    public b2(zzup zzupVar) {
        this.o = new a2(this, zzupVar);
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public final void run() {
        z1 z1Var = this.o;
        if (z1Var != null) {
            z1Var.run();
        }
        this.o = null;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzec
    @CheckForNull
    public final String zze() {
        z1 z1Var = this.o;
        if (z1Var != null) {
            String obj = z1Var.toString();
            return "task=[" + obj + "]";
        }
        return super.zze();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzec
    public final void zzk() {
        z1 z1Var;
        if (zzn() && (z1Var = this.o) != null) {
            z1Var.zze();
        }
        this.o = null;
    }
}
