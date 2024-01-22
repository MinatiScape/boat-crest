package com.google.android.gms.internal.fido;

import java.util.Arrays;
import java.util.Objects;
import javax.annotation.CheckForNull;
/* loaded from: classes7.dex */
public final class zzaj {

    /* renamed from: a  reason: collision with root package name */
    public final String f8636a;
    public final c b;
    public c c;

    public /* synthetic */ zzaj(String str, zzai zzaiVar) {
        c cVar = new c(null);
        this.b = cVar;
        this.c = cVar;
        Objects.requireNonNull(str);
        this.f8636a = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.f8636a);
        sb.append('{');
        c cVar = this.b.c;
        String str = "";
        while (cVar != null) {
            Object obj = cVar.b;
            boolean z = cVar instanceof b;
            sb.append(str);
            String str2 = cVar.f8632a;
            if (str2 != null) {
                sb.append(str2);
                sb.append('=');
            }
            if (obj != null && obj.getClass().isArray()) {
                String deepToString = Arrays.deepToString(new Object[]{obj});
                sb.append((CharSequence) deepToString, 1, deepToString.length() - 1);
            } else {
                sb.append(obj);
            }
            cVar = cVar.c;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }

    public final zzaj zza(String str, int i) {
        String valueOf = String.valueOf(i);
        b bVar = new b(null);
        this.c.c = bVar;
        this.c = bVar;
        bVar.b = valueOf;
        bVar.f8632a = "errorCode";
        return this;
    }

    public final zzaj zzb(String str, @CheckForNull Object obj) {
        c cVar = new c(null);
        this.c.c = cVar;
        this.c = cVar;
        cVar.b = obj;
        cVar.f8632a = str;
        return this;
    }
}
