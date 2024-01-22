package com.google.android.gms.internal.fitness;
/* loaded from: classes8.dex */
public final class e3 implements k3 {

    /* renamed from: a  reason: collision with root package name */
    public k3[] f8822a;

    public e3(k3... k3VarArr) {
        this.f8822a = k3VarArr;
    }

    @Override // com.google.android.gms.internal.fitness.k3
    public final boolean zzb(Class<?> cls) {
        for (k3 k3Var : this.f8822a) {
            if (k3Var.zzb(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.fitness.k3
    public final l3 zzc(Class<?> cls) {
        k3[] k3VarArr;
        for (k3 k3Var : this.f8822a) {
            if (k3Var.zzb(cls)) {
                return k3Var.zzc(cls);
            }
        }
        String name = cls.getName();
        throw new UnsupportedOperationException(name.length() != 0 ? "No factory is available for message type: ".concat(name) : new String("No factory is available for message type: "));
    }
}
