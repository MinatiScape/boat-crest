package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzjp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes8.dex */
public final class y2<T extends zzjp<T>> {
    public static final y2 d = new y2(true);

    /* renamed from: a  reason: collision with root package name */
    public final i4<T, Object> f8936a = new a4(16);
    public boolean b;
    public boolean c;

    public y2() {
    }

    public static <T extends zzjp<T>> y2<T> a() {
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void d(T r4, java.lang.Object r5) {
        /*
            com.google.android.gms.internal.measurement.zzmy r0 = r4.zzb()
            com.google.android.gms.internal.measurement.zzkh.b(r5)
            com.google.android.gms.internal.measurement.zzmy r1 = com.google.android.gms.internal.measurement.zzmy.zza
            com.google.android.gms.internal.measurement.zzmz r1 = com.google.android.gms.internal.measurement.zzmz.INT
            com.google.android.gms.internal.measurement.zzmz r0 = r0.zza()
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
            boolean r0 = r5 instanceof com.google.android.gms.internal.measurement.zzlg
            if (r0 != 0) goto L45
            boolean r0 = r5 instanceof com.google.android.gms.internal.measurement.zzkl
            if (r0 == 0) goto L46
            goto L45
        L20:
            boolean r0 = r5 instanceof java.lang.Integer
            if (r0 != 0) goto L45
            boolean r0 = r5 instanceof com.google.android.gms.internal.measurement.zzkb
            if (r0 == 0) goto L46
            goto L45
        L29:
            boolean r0 = r5 instanceof com.google.android.gms.internal.measurement.zziy
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
            com.google.android.gms.internal.measurement.zzmy r4 = r4.zzb()
            com.google.android.gms.internal.measurement.zzmz r4 = r4.zza()
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.y2.d(com.google.android.gms.internal.measurement.zzjp, java.lang.Object):void");
    }

    public final void b() {
        if (this.b) {
            return;
        }
        this.f8936a.a();
        this.b = true;
    }

    public final void c(T t, Object obj) {
        if (t.zzc()) {
            if (obj instanceof List) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll((List) obj);
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    d(t, arrayList.get(i));
                }
                obj = arrayList;
            } else {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
        } else {
            d(t, obj);
        }
        if (obj instanceof zzkl) {
            this.c = true;
        }
        this.f8936a.put(t, obj);
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        y2 y2Var = new y2();
        for (int i = 0; i < this.f8936a.b(); i++) {
            Map.Entry<T, Object> h = this.f8936a.h(i);
            y2Var.c(h.getKey(), h.getValue());
        }
        for (Map.Entry<T, Object> entry : this.f8936a.d()) {
            y2Var.c(entry.getKey(), entry.getValue());
        }
        y2Var.c = this.c;
        return y2Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof y2) {
            return this.f8936a.equals(((y2) obj).f8936a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f8936a.hashCode();
    }

    public y2(boolean z) {
        b();
        b();
    }
}
