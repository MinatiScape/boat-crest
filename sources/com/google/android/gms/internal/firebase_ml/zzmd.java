package com.google.android.gms.internal.firebase_ml;

import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes7.dex */
public final class zzmd {

    /* renamed from: a  reason: collision with root package name */
    public final String f8786a;
    public final u1 b;
    public u1 c;

    public zzmd(String str) {
        u1 u1Var = new u1();
        this.b = u1Var;
        this.c = u1Var;
        this.f8786a = (String) zzml.checkNotNull(str);
    }

    public final zzmd a(String str, @NullableDecl Object obj) {
        u1 u1Var = new u1();
        this.c.c = u1Var;
        this.c = u1Var;
        u1Var.b = obj;
        u1Var.f8737a = (String) zzml.checkNotNull(str);
        return this;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.f8786a);
        sb.append('{');
        u1 u1Var = this.b.c;
        String str = "";
        while (u1Var != null) {
            Object obj = u1Var.b;
            sb.append(str);
            String str2 = u1Var.f8737a;
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
            u1Var = u1Var.c;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }

    public final zzmd zza(String str, boolean z) {
        return a(str, String.valueOf(z));
    }

    public final zzmd zzb(String str, int i) {
        return a(str, String.valueOf(i));
    }

    public final zzmd zzh(String str, @NullableDecl Object obj) {
        return a(str, obj);
    }

    public final zzmd zza(String str, float f) {
        return a(str, String.valueOf(f));
    }
}
