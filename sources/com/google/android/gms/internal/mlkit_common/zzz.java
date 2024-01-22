package com.google.android.gms.internal.mlkit_common;

import java.util.Arrays;
import java.util.Objects;
import javax.annotation.CheckForNull;
/* loaded from: classes8.dex */
public final class zzz {

    /* renamed from: a  reason: collision with root package name */
    public final String f9362a;
    public final n6 b;
    public n6 c;

    public /* synthetic */ zzz(String str, zzy zzyVar) {
        n6 n6Var = new n6(null);
        this.b = n6Var;
        this.c = n6Var;
        Objects.requireNonNull(str);
        this.f9362a = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.f9362a);
        sb.append('{');
        n6 n6Var = this.b.c;
        String str = "";
        while (n6Var != null) {
            Object obj = n6Var.b;
            sb.append(str);
            String str2 = n6Var.f9251a;
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
            n6Var = n6Var.c;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }

    public final zzz zza(String str, @CheckForNull Object obj) {
        n6 n6Var = new n6(null);
        this.c.c = n6Var;
        this.c = n6Var;
        n6Var.b = obj;
        n6Var.f9251a = str;
        return this;
    }

    public final zzz zzb(String str, boolean z) {
        String valueOf = String.valueOf(z);
        m6 m6Var = new m6(null);
        this.c.c = m6Var;
        this.c = m6Var;
        m6Var.b = valueOf;
        m6Var.f9251a = "isManifestFile";
        return this;
    }
}
