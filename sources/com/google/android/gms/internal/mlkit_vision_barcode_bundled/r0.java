package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes8.dex */
public final class r0 implements w0 {

    /* renamed from: a  reason: collision with root package name */
    public final w0[] f9610a;

    public r0(w0... w0VarArr) {
        this.f9610a = w0VarArr;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.w0
    public final v0 zzb(Class cls) {
        w0[] w0VarArr = this.f9610a;
        for (int i = 0; i < 2; i++) {
            w0 w0Var = w0VarArr[i];
            if (w0Var.zzc(cls)) {
                return w0Var.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(cls.getName()));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.w0
    public final boolean zzc(Class cls) {
        w0[] w0VarArr = this.f9610a;
        for (int i = 0; i < 2; i++) {
            if (w0VarArr[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
