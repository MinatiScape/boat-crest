package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.util.Iterator;
/* loaded from: classes10.dex */
public class zzfj {
    private final String zza;

    private zzfj(String str) {
        this.zza = (String) zzft.zza(str);
    }

    public static zzfj zza(String str) {
        return new zzfj(str);
    }

    public zzfo zzb(String str) {
        return new zzfo(this, str, null);
    }

    public <A extends Appendable> A zza(A a2, Iterator<?> it) throws IOException {
        zzft.zza(a2);
        if (it.hasNext()) {
            a2.append(zza(it.next()));
            while (it.hasNext()) {
                a2.append(this.zza);
                a2.append(zza(it.next()));
            }
        }
        return a2;
    }

    private zzfj(zzfj zzfjVar) {
        this.zza = zzfjVar.zza;
    }

    public /* synthetic */ zzfj(zzfj zzfjVar, zzfm zzfmVar) {
        this(zzfjVar);
    }

    public final StringBuilder zza(StringBuilder sb, Iterator<?> it) {
        try {
            zza((zzfj) sb, it);
            return sb;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public zzfj zza() {
        return new zzfl(this, this);
    }

    public CharSequence zza(Object obj) {
        zzft.zza(obj);
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }
}
