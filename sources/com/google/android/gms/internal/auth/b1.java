package com.google.android.gms.internal.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes6.dex */
public final class b1 {
    public static final b1 d = new b1(true);

    /* renamed from: a  reason: collision with root package name */
    public final j2 f8517a = new c2(16);
    public boolean b;
    public boolean c;

    public b1() {
    }

    public static b1 a() {
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void d(com.google.android.gms.internal.auth.zzeo r4, java.lang.Object r5) {
        /*
            com.google.android.gms.internal.auth.zzhn r0 = r4.zzb()
            com.google.android.gms.internal.auth.zzez.b(r5)
            com.google.android.gms.internal.auth.zzhn r1 = com.google.android.gms.internal.auth.zzhn.zza
            com.google.android.gms.internal.auth.zzho r1 = com.google.android.gms.internal.auth.zzho.INT
            com.google.android.gms.internal.auth.zzho r0 = r0.zza()
            int r0 = r0.ordinal()
            switch(r0) {
                case 0: goto L41;
                case 1: goto L3e;
                case 2: goto L3b;
                case 3: goto L38;
                case 4: goto L35;
                case 5: goto L32;
                case 6: goto L29;
                case 7: goto L20;
                case 8: goto L17;
                default: goto L16;
            }
        L16:
            goto L46
        L17:
            boolean r0 = r5 instanceof com.google.android.gms.internal.auth.zzfw
            if (r0 != 0) goto L45
            boolean r0 = r5 instanceof com.google.android.gms.internal.auth.zzfb
            if (r0 == 0) goto L46
            goto L45
        L20:
            boolean r0 = r5 instanceof java.lang.Integer
            if (r0 != 0) goto L45
            boolean r0 = r5 instanceof com.google.android.gms.internal.auth.zzew
            if (r0 == 0) goto L46
            goto L45
        L29:
            boolean r0 = r5 instanceof com.google.android.gms.internal.auth.zzee
            if (r0 != 0) goto L45
            boolean r0 = r5 instanceof byte[]
            if (r0 == 0) goto L46
            goto L45
        L32:
            boolean r0 = r5 instanceof java.lang.String
            goto L43
        L35:
            boolean r0 = r5 instanceof java.lang.Boolean
            goto L43
        L38:
            boolean r0 = r5 instanceof java.lang.Double
            goto L43
        L3b:
            boolean r0 = r5 instanceof java.lang.Float
            goto L43
        L3e:
            boolean r0 = r5 instanceof java.lang.Long
            goto L43
        L41:
            boolean r0 = r5 instanceof java.lang.Integer
        L43:
            if (r0 == 0) goto L46
        L45:
            return
        L46:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r1 = 3
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = 0
            int r3 = r4.zza()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1[r2] = r3
            r2 = 1
            com.google.android.gms.internal.auth.zzhn r4 = r4.zzb()
            com.google.android.gms.internal.auth.zzho r4 = r4.zza()
            r1[r2] = r4
            r4 = 2
            java.lang.Class r5 = r5.getClass()
            java.lang.String r5 = r5.getName()
            r1[r4] = r5
            java.lang.String r4 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r4 = java.lang.String.format(r4, r1)
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.b1.d(com.google.android.gms.internal.auth.zzeo, java.lang.Object):void");
    }

    public final void b() {
        if (this.b) {
            return;
        }
        this.f8517a.a();
        this.b = true;
    }

    public final void c(zzeo zzeoVar, Object obj) {
        if (zzeoVar.zzc()) {
            if (obj instanceof List) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll((List) obj);
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    d(zzeoVar, arrayList.get(i));
                }
                obj = arrayList;
            } else {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
        } else {
            d(zzeoVar, obj);
        }
        if (obj instanceof zzfb) {
            this.c = true;
        }
        this.f8517a.put(zzeoVar, obj);
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        b1 b1Var = new b1();
        for (int i = 0; i < this.f8517a.b(); i++) {
            Map.Entry h = this.f8517a.h(i);
            b1Var.c((zzeo) h.getKey(), h.getValue());
        }
        for (Map.Entry entry : this.f8517a.d()) {
            b1Var.c((zzeo) entry.getKey(), entry.getValue());
        }
        b1Var.c = this.c;
        return b1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b1) {
            return this.f8517a.equals(((b1) obj).f8517a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f8517a.hashCode();
    }

    public b1(boolean z) {
        b();
        b();
    }
}
