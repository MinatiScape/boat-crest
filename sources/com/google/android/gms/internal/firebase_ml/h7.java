package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public final class h7 implements n7 {

    /* renamed from: a  reason: collision with root package name */
    public n7[] f8686a;

    public h7(n7... n7VarArr) {
        this.f8686a = n7VarArr;
    }

    @Override // com.google.android.gms.internal.firebase_ml.n7
    public final boolean a(Class<?> cls) {
        for (n7 n7Var : this.f8686a) {
            if (n7Var.a(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.firebase_ml.n7
    public final l7 b(Class<?> cls) {
        n7[] n7VarArr;
        for (n7 n7Var : this.f8686a) {
            if (n7Var.a(cls)) {
                return n7Var.b(cls);
            }
        }
        String name = cls.getName();
        throw new UnsupportedOperationException(name.length() != 0 ? "No factory is available for message type: ".concat(name) : new String("No factory is available for message type: "));
    }
}
