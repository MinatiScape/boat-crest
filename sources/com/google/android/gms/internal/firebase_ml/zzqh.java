package com.google.android.gms.internal.firebase_ml;

import java.util.Objects;
/* loaded from: classes7.dex */
public final class zzqh<T> {

    /* renamed from: a  reason: collision with root package name */
    public final String f8796a;
    public final T b;

    public zzqh(String str, T t) {
        Objects.requireNonNull(str, "Null firebasePersistentKey");
        this.f8796a = str;
        Objects.requireNonNull(t, "Null options");
        this.b = t;
    }

    public static <T> zzqh<T> zzj(String str, T t) {
        return new zzqh<>(str, t);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzqh) {
            zzqh zzqhVar = (zzqh) obj;
            if (this.f8796a.equals(zzqhVar.f8796a) && this.b.equals(zzqhVar.b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return com.google.android.gms.common.internal.Objects.hashCode(this.f8796a, this.b);
    }

    public final String toString() {
        String str = this.f8796a;
        String valueOf = String.valueOf(this.b);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 58 + valueOf.length());
        sb.append("MlModelDriverInstanceKey{firebasePersistentKey=");
        sb.append(str);
        sb.append(", options=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
