package com.google.android.gms.internal.firebase_messaging;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.lang.annotation.Annotation;
/* loaded from: classes7.dex */
public final class r implements zzz {

    /* renamed from: a  reason: collision with root package name */
    public final int f8651a;
    public final zzy b;

    public r(int i, zzy zzyVar) {
        this.f8651a = i;
        this.b = zzyVar;
    }

    @Override // java.lang.annotation.Annotation
    public final Class<? extends Annotation> annotationType() {
        return zzz.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzz) {
            zzz zzzVar = (zzz) obj;
            return this.f8651a == zzzVar.zza() && this.b.equals(zzzVar.zzb());
        }
        return false;
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return (this.f8651a ^ 14552422) + (this.b.hashCode() ^ 2041407134);
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.f8651a + "intEncoding=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // com.google.android.gms.internal.firebase_messaging.zzz
    public final int zza() {
        return this.f8651a;
    }

    @Override // com.google.android.gms.internal.firebase_messaging.zzz
    public final zzy zzb() {
        return this.b;
    }
}
