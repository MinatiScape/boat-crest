package com.google.android.gms.internal.mlkit_common;

import com.goodix.ble.libcomx.util.HexStringBuilder;
/* loaded from: classes8.dex */
public final class q implements zzbo {

    /* renamed from: a  reason: collision with root package name */
    public final int f9263a;
    public final zzbn b;

    public q(int i, zzbn zzbnVar) {
        this.f9263a = i;
        this.b = zzbnVar;
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return zzbo.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzbo) {
            zzbo zzboVar = (zzbo) obj;
            return this.f9263a == zzboVar.zza() && this.b.equals(zzboVar.zzb());
        }
        return false;
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return (this.f9263a ^ 14552422) + (this.b.hashCode() ^ 2041407134);
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.f9263a + "intEncoding=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzbo
    public final int zza() {
        return this.f9263a;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzbo
    public final zzbn zzb() {
        return this.b;
    }
}
