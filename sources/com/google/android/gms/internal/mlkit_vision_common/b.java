package com.google.android.gms.internal.mlkit_vision_common;

import com.goodix.ble.libcomx.util.HexStringBuilder;
/* loaded from: classes8.dex */
public final class b implements zzai {

    /* renamed from: a  reason: collision with root package name */
    public final int f9640a;
    public final zzah b;

    public b(int i, zzah zzahVar) {
        this.f9640a = i;
        this.b = zzahVar;
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return zzai.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzai) {
            zzai zzaiVar = (zzai) obj;
            return this.f9640a == zzaiVar.zza() && this.b.equals(zzaiVar.zzb());
        }
        return false;
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return (this.f9640a ^ 14552422) + (this.b.hashCode() ^ 2041407134);
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.f9640a + "intEncoding=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzai
    public final int zza() {
        return this.f9640a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzai
    public final zzah zzb() {
        return this.b;
    }
}
