package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzet;
import com.google.android.gms.internal.vision.zzew;
import java.io.IOException;
/* loaded from: classes10.dex */
public abstract class zzew<MessageType extends zzet<MessageType, BuilderType>, BuilderType extends zzew<MessageType, BuilderType>> implements zzib {
    public abstract BuilderType zza(MessageType messagetype);

    public abstract BuilderType zza(zzft zzftVar, zzgd zzgdVar) throws IOException;

    public BuilderType zza(byte[] bArr, int i, int i2, zzgd zzgdVar) throws zzhc {
        try {
            zzft a2 = zzft.a(bArr, 0, i2, false);
            zza(a2, zzgdVar);
            a2.zzar(0);
            return this;
        } catch (zzhc e) {
            throw e;
        } catch (IOException e2) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(name.length() + 60 + "byte array".length());
            sb.append("Reading ");
            sb.append(name);
            sb.append(" from a ");
            sb.append("byte array");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e2);
        }
    }

    @Override // 
    /* renamed from: zzdn */
    public abstract BuilderType clone();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.vision.zzib
    public final /* synthetic */ zzib zza(zzic zzicVar) {
        if (zzgd().getClass().isInstance(zzicVar)) {
            return zza((zzew<MessageType, BuilderType>) ((zzet) zzicVar));
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
