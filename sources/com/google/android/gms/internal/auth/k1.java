package com.google.android.gms.internal.auth;
/* loaded from: classes6.dex */
public final class k1 implements p1 {

    /* renamed from: a  reason: collision with root package name */
    public final p1[] f8528a;

    public k1(p1... p1VarArr) {
        this.f8528a = p1VarArr;
    }

    @Override // com.google.android.gms.internal.auth.p1
    public final o1 zzb(Class cls) {
        p1[] p1VarArr = this.f8528a;
        for (int i = 0; i < 2; i++) {
            p1 p1Var = p1VarArr[i];
            if (p1Var.zzc(cls)) {
                return p1Var.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(cls.getName()));
    }

    @Override // com.google.android.gms.internal.auth.p1
    public final boolean zzc(Class cls) {
        p1[] p1VarArr = this.f8528a;
        for (int i = 0; i < 2; i++) {
            if (p1VarArr[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
