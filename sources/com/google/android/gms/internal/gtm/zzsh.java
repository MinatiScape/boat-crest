package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzsg;
import com.google.android.gms.internal.gtm.zzsh;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public abstract class zzsh<MessageType extends zzsh<MessageType, BuilderType>, BuilderType extends zzsg<MessageType, BuilderType>> implements zzwk {
    public int zzb = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void zzS(Iterable<T> iterable, List<? super T> list) {
        zzvi.zze(iterable);
        if (list instanceof ArrayList) {
            ((ArrayList) list).ensureCapacity(list.size() + iterable.size());
        }
        int size = list.size();
        for (T t : iterable) {
            if (t == 0) {
                int size2 = list.size();
                StringBuilder sb = new StringBuilder(37);
                sb.append("Element at index ");
                sb.append(size2 - size);
                sb.append(" is null.");
                String sb2 = sb.toString();
                int size3 = list.size();
                while (true) {
                    size3--;
                    if (size3 < size) {
                        break;
                    }
                    list.remove(size3);
                }
                throw new NullPointerException(sb2);
            }
            list.add(t);
        }
    }

    public int zzQ() {
        throw null;
    }

    @Override // com.google.android.gms.internal.gtm.zzwk
    public final zztd zzR() {
        try {
            int zzX = zzX();
            zztd zztdVar = zztd.zzb;
            byte[] bArr = new byte[zzX];
            zzto zzF = zzto.zzF(bArr);
            zzaq(zzF);
            zzF.zzG();
            return new zzta(bArr);
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(name.length() + 72);
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ByteString threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    public void zzT(int i) {
        throw null;
    }
}
