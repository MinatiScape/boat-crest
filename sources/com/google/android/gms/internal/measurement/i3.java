package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class i3 implements n3 {

    /* renamed from: a  reason: collision with root package name */
    public final n3[] f8913a;

    public i3(n3... n3VarArr) {
        this.f8913a = n3VarArr;
    }

    @Override // com.google.android.gms.internal.measurement.n3
    public final m3 zzb(Class<?> cls) {
        n3[] n3VarArr = this.f8913a;
        for (int i = 0; i < 2; i++) {
            n3 n3Var = n3VarArr[i];
            if (n3Var.zzc(cls)) {
                return n3Var.zzb(cls);
            }
        }
        String name = cls.getName();
        throw new UnsupportedOperationException(name.length() != 0 ? "No factory is available for message type: ".concat(name) : new String("No factory is available for message type: "));
    }

    @Override // com.google.android.gms.internal.measurement.n3
    public final boolean zzc(Class<?> cls) {
        n3[] n3VarArr = this.f8913a;
        for (int i = 0; i < 2; i++) {
            if (n3VarArr[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
