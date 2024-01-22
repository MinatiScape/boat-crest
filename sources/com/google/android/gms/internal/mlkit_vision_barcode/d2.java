package com.google.android.gms.internal.mlkit_vision_barcode;

import com.goodix.ble.libcomx.util.HexStringBuilder;
/* loaded from: classes9.dex */
public final class d2 implements zzfg {

    /* renamed from: a  reason: collision with root package name */
    public final int f9382a;
    public final zzff b;

    public d2(int i, zzff zzffVar) {
        this.f9382a = i;
        this.b = zzffVar;
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return zzfg.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzfg) {
            zzfg zzfgVar = (zzfg) obj;
            return this.f9382a == zzfgVar.zza() && this.b.equals(zzfgVar.zzb());
        }
        return false;
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return (this.f9382a ^ 14552422) + (this.b.hashCode() ^ 2041407134);
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.f9382a + "intEncoding=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzfg
    public final int zza() {
        return this.f9382a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzfg
    public final zzff zzb() {
        return this.b;
    }
}
