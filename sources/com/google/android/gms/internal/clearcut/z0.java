package com.google.android.gms.internal.clearcut;
/* loaded from: classes7.dex */
public final class z0 implements f1 {

    /* renamed from: a  reason: collision with root package name */
    public f1[] f8608a;

    public z0(f1... f1VarArr) {
        this.f8608a = f1VarArr;
    }

    @Override // com.google.android.gms.internal.clearcut.f1
    public final boolean zza(Class<?> cls) {
        for (f1 f1Var : this.f8608a) {
            if (f1Var.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.clearcut.f1
    public final e1 zzb(Class<?> cls) {
        f1[] f1VarArr;
        for (f1 f1Var : this.f8608a) {
            if (f1Var.zza(cls)) {
                return f1Var.zzb(cls);
            }
        }
        String name = cls.getName();
        throw new UnsupportedOperationException(name.length() != 0 ? "No factory is available for message type: ".concat(name) : new String("No factory is available for message type: "));
    }
}
