package com.google.android.gms.internal.mlkit_vision_text_common;

import java.io.IOException;
import java.util.Iterator;
import javax.annotation.CheckForNull;
/* loaded from: classes6.dex */
public final class zzv {

    /* renamed from: a  reason: collision with root package name */
    public final String f9957a = "\n";

    public zzv(String str) {
    }

    public static final CharSequence a(@CheckForNull Object obj) {
        obj.getClass();
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }

    public static zzv zza(String str) {
        return new zzv("\n");
    }

    public final String zzb(Iterable iterable) {
        Iterator it = iterable.iterator();
        StringBuilder sb = new StringBuilder();
        try {
            if (it.hasNext()) {
                sb.append(a(it.next()));
                while (it.hasNext()) {
                    sb.append((CharSequence) this.f9957a);
                    sb.append(a(it.next()));
                }
            }
            return sb.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
