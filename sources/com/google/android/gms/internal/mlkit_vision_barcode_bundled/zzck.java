package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcj;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzck;
import java.io.IOException;
/* loaded from: classes8.dex */
public abstract class zzck<MessageType extends zzck<MessageType, BuilderType>, BuilderType extends zzcj<MessageType, BuilderType>> implements zzfo {
    public int zzb = 0;

    public int a(l1 l1Var) {
        throw null;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo
    public final zzdb zzC() {
        try {
            int zzE = zzE();
            zzdb zzdbVar = zzdb.zzb;
            byte[] bArr = new byte[zzE];
            zzdj zzA = zzdj.zzA(bArr, 0, zzE);
            zzaa(zzA);
            zzA.zzB();
            return new u(bArr);
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a ByteString threw an IOException (should never happen).", e);
        }
    }
}
