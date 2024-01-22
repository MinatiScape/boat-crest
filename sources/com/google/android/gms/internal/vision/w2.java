package com.google.android.gms.internal.vision;
/* loaded from: classes10.dex */
public final class w2 implements c3 {

    /* renamed from: a  reason: collision with root package name */
    public c3[] f10002a;

    public w2(c3... c3VarArr) {
        this.f10002a = c3VarArr;
    }

    @Override // com.google.android.gms.internal.vision.c3
    public final boolean zza(Class<?> cls) {
        for (c3 c3Var : this.f10002a) {
            if (c3Var.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.vision.c3
    public final d3 zzb(Class<?> cls) {
        c3[] c3VarArr;
        for (c3 c3Var : this.f10002a) {
            if (c3Var.zza(cls)) {
                return c3Var.zzb(cls);
            }
        }
        String name = cls.getName();
        throw new UnsupportedOperationException(name.length() != 0 ? "No factory is available for message type: ".concat(name) : new String("No factory is available for message type: "));
    }
}
