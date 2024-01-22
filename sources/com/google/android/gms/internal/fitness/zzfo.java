package com.google.android.gms.internal.fitness;

import com.google.android.gms.internal.fitness.zzfo;
import com.google.android.gms.internal.fitness.zzfr;
import java.io.IOException;
/* loaded from: classes8.dex */
public abstract class zzfo<MessageType extends zzfo<MessageType, BuilderType>, BuilderType extends zzfr<MessageType, BuilderType>> implements zzik {
    public int zztu = 0;

    public int a() {
        throw new UnsupportedOperationException();
    }

    public void b(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.fitness.zzik
    public final zzfx zzam() {
        try {
            f2 zzl = zzfx.zzl(zzbp());
            zzb(zzl.b());
            return zzl.a();
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(name.length() + 62 + "ByteString".length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("ByteString");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }
}
