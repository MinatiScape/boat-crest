package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzfl extends zzfj {
    private final /* synthetic */ zzfj zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzfl(zzfj zzfjVar, zzfj zzfjVar2) {
        super(zzfjVar2, null);
        this.zza = zzfjVar;
    }

    @Override // com.google.android.libraries.places.internal.zzfj
    public final <A extends Appendable> A zza(A a2, Iterator<?> it) throws IOException {
        String str;
        zzft.zza(a2, "appendable");
        zzft.zza(it, "parts");
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (next != null) {
                a2.append(this.zza.zza(next));
                break;
            }
        }
        while (it.hasNext()) {
            Object next2 = it.next();
            if (next2 != null) {
                str = this.zza.zza;
                a2.append(str);
                a2.append(this.zza.zza(next2));
            }
        }
        return a2;
    }

    @Override // com.google.android.libraries.places.internal.zzfj
    public final zzfo zzb(String str) {
        throw new UnsupportedOperationException("can't use .skipNulls() with maps");
    }
}
