package com.google.android.gms.internal.mlkit_code_scanner;

import com.goodix.ble.libcomx.util.HexStringBuilder;
/* loaded from: classes8.dex */
public final class b implements zzah {

    /* renamed from: a  reason: collision with root package name */
    public final int f8973a;
    public final zzag b;

    public b(int i, zzag zzagVar) {
        this.f8973a = i;
        this.b = zzagVar;
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return zzah.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzah) {
            zzah zzahVar = (zzah) obj;
            return this.f8973a == zzahVar.zza() && this.b.equals(zzahVar.zzb());
        }
        return false;
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return (this.f8973a ^ 14552422) + (this.b.hashCode() ^ 2041407134);
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.f8973a + "intEncoding=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzah
    public final int zza() {
        return this.f8973a;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzah
    public final zzag zzb() {
        return this.b;
    }
}
