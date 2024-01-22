package com.google.android.gms.internal.mlkit_vision_text_common;

import com.goodix.ble.libcomx.util.HexStringBuilder;
/* loaded from: classes6.dex */
public final class z0 implements zzcz {

    /* renamed from: a  reason: collision with root package name */
    public final int f9921a;
    public final zzcy b;

    public z0(int i, zzcy zzcyVar) {
        this.f9921a = i;
        this.b = zzcyVar;
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return zzcz.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzcz) {
            zzcz zzczVar = (zzcz) obj;
            return this.f9921a == zzczVar.zza() && this.b.equals(zzczVar.zzb());
        }
        return false;
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return (this.f9921a ^ 14552422) + (this.b.hashCode() ^ 2041407134);
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.f9921a + "intEncoding=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzcz
    public final int zza() {
        return this.f9921a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzcz
    public final zzcy zzb() {
        return this.b;
    }
}
