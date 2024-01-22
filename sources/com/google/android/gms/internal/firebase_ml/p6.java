package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zzwt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes7.dex */
public final class p6<T extends zzwt<T>> {
    public static final p6 d = new p6(true);

    /* renamed from: a  reason: collision with root package name */
    public final h8<T, Object> f8717a;
    public boolean b;
    public boolean c;

    public p6() {
        this.f8717a = h8.j(16);
    }

    public static int e(zzaan zzaanVar, int i, Object obj) {
        int zzdf = zzwi.zzdf(i);
        if (zzaanVar == zzaan.zzcrn) {
            zzxd.e((zzyk) obj);
            zzdf <<= 1;
        }
        return zzdf + k(zzaanVar, obj);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0025, code lost:
        if ((r3 instanceof com.google.android.gms.internal.firebase_ml.zzxc) == false) goto L3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x002e, code lost:
        if ((r3 instanceof byte[]) == false) goto L3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001c, code lost:
        if ((r3 instanceof com.google.android.gms.internal.firebase_ml.zzxp) == false) goto L3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void g(com.google.android.gms.internal.firebase_ml.zzaan r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.firebase_ml.zzxd.a(r3)
            int[] r0 = com.google.android.gms.internal.firebase_ml.r6.f8725a
            com.google.android.gms.internal.firebase_ml.zzaaq r2 = r2.zzxi()
            int r2 = r2.ordinal()
            r2 = r0[r2]
            r0 = 1
            r1 = 0
            switch(r2) {
                case 1: goto L40;
                case 2: goto L3d;
                case 3: goto L3a;
                case 4: goto L37;
                case 5: goto L34;
                case 6: goto L31;
                case 7: goto L28;
                case 8: goto L1f;
                case 9: goto L16;
                default: goto L14;
            }
        L14:
            r0 = r1
            goto L42
        L16:
            boolean r2 = r3 instanceof com.google.android.gms.internal.firebase_ml.zzyk
            if (r2 != 0) goto L42
            boolean r2 = r3 instanceof com.google.android.gms.internal.firebase_ml.zzxp
            if (r2 == 0) goto L14
            goto L42
        L1f:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L42
            boolean r2 = r3 instanceof com.google.android.gms.internal.firebase_ml.zzxc
            if (r2 == 0) goto L14
            goto L42
        L28:
            boolean r2 = r3 instanceof com.google.android.gms.internal.firebase_ml.zzvv
            if (r2 != 0) goto L42
            boolean r2 = r3 instanceof byte[]
            if (r2 == 0) goto L14
            goto L42
        L31:
            boolean r0 = r3 instanceof java.lang.String
            goto L42
        L34:
            boolean r0 = r3 instanceof java.lang.Boolean
            goto L42
        L37:
            boolean r0 = r3 instanceof java.lang.Double
            goto L42
        L3a:
            boolean r0 = r3 instanceof java.lang.Float
            goto L42
        L3d:
            boolean r0 = r3 instanceof java.lang.Long
            goto L42
        L40:
            boolean r0 = r3 instanceof java.lang.Integer
        L42:
            if (r0 == 0) goto L45
            return
        L45:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Wrong object type used with protocol message reflection."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.p6.g(com.google.android.gms.internal.firebase_ml.zzaan, java.lang.Object):void");
    }

    public static void h(zzwi zzwiVar, zzaan zzaanVar, int i, Object obj) throws IOException {
        if (zzaanVar == zzaan.zzcrn) {
            zzyk zzykVar = (zzyk) obj;
            zzxd.e(zzykVar);
            zzwiVar.writeTag(i, 3);
            zzykVar.zzb(zzwiVar);
            zzwiVar.writeTag(i, 4);
            return;
        }
        zzwiVar.writeTag(i, zzaanVar.zzxj());
        switch (r6.b[zzaanVar.ordinal()]) {
            case 1:
                zzwiVar.zzc(((Double) obj).doubleValue());
                return;
            case 2:
                zzwiVar.zzq(((Float) obj).floatValue());
                return;
            case 3:
                zzwiVar.zzw(((Long) obj).longValue());
                return;
            case 4:
                zzwiVar.zzw(((Long) obj).longValue());
                return;
            case 5:
                zzwiVar.zzdb(((Integer) obj).intValue());
                return;
            case 6:
                zzwiVar.zzy(((Long) obj).longValue());
                return;
            case 7:
                zzwiVar.zzde(((Integer) obj).intValue());
                return;
            case 8:
                zzwiVar.zzax(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzyk) obj).zzb(zzwiVar);
                return;
            case 10:
                zzwiVar.zzb((zzyk) obj);
                return;
            case 11:
                if (obj instanceof zzvv) {
                    zzwiVar.zzc((zzvv) obj);
                    return;
                } else {
                    zzwiVar.zzck((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzvv) {
                    zzwiVar.zzc((zzvv) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzwiVar.i(bArr, 0, bArr.length);
                return;
            case 13:
                zzwiVar.zzdc(((Integer) obj).intValue());
                return;
            case 14:
                zzwiVar.zzde(((Integer) obj).intValue());
                return;
            case 15:
                zzwiVar.zzy(((Long) obj).longValue());
                return;
            case 16:
                zzwiVar.zzdd(((Integer) obj).intValue());
                return;
            case 17:
                zzwiVar.zzx(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzxc) {
                    zzwiVar.zzdb(((zzxc) obj).zzd());
                    return;
                } else {
                    zzwiVar.zzdb(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    public static int k(zzaan zzaanVar, Object obj) {
        switch (r6.b[zzaanVar.ordinal()]) {
            case 1:
                return zzwi.zzd(((Double) obj).doubleValue());
            case 2:
                return zzwi.zzr(((Float) obj).floatValue());
            case 3:
                return zzwi.zzz(((Long) obj).longValue());
            case 4:
                return zzwi.zzaa(((Long) obj).longValue());
            case 5:
                return zzwi.zzdg(((Integer) obj).intValue());
            case 6:
                return zzwi.zzac(((Long) obj).longValue());
            case 7:
                return zzwi.zzdj(((Integer) obj).intValue());
            case 8:
                return zzwi.zzay(((Boolean) obj).booleanValue());
            case 9:
                return zzwi.zzd((zzyk) obj);
            case 10:
                if (obj instanceof zzxp) {
                    return zzwi.zza((zzxp) obj);
                }
                return zzwi.zzc((zzyk) obj);
            case 11:
                if (obj instanceof zzvv) {
                    return zzwi.zzd((zzvv) obj);
                }
                return zzwi.zzcl((String) obj);
            case 12:
                if (obj instanceof zzvv) {
                    return zzwi.zzd((zzvv) obj);
                }
                return zzwi.zzh((byte[]) obj);
            case 13:
                return zzwi.zzdh(((Integer) obj).intValue());
            case 14:
                return zzwi.zzdk(((Integer) obj).intValue());
            case 15:
                return zzwi.zzad(((Long) obj).longValue());
            case 16:
                return zzwi.zzdi(((Integer) obj).intValue());
            case 17:
                return zzwi.zzab(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzxc) {
                    return zzwi.zzdl(((zzxc) obj).zzd());
                }
                return zzwi.zzdl(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int l(zzwt<?> zzwtVar, Object obj) {
        zzaan zzui = zzwtVar.zzui();
        int zzd = zzwtVar.zzd();
        if (zzwtVar.zzuk()) {
            int i = 0;
            if (zzwtVar.zzul()) {
                for (Object obj2 : (List) obj) {
                    i += k(zzui, obj2);
                }
                return zzwi.zzdf(zzd) + i + zzwi.zzdo(i);
            }
            for (Object obj3 : (List) obj) {
                i += e(zzui, zzd, obj3);
            }
            return i;
        }
        return e(zzui, zzd, obj);
    }

    public static <T extends zzwt<T>> boolean m(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        if (key.zzuj() == zzaaq.MESSAGE) {
            if (key.zzuk()) {
                for (zzyk zzykVar : (List) entry.getValue()) {
                    if (!zzykVar.isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzyk) {
                    if (!((zzyk) value).isInitialized()) {
                        return false;
                    }
                } else if (value instanceof zzxp) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public static int o(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        Object value = entry.getValue();
        if (key.zzuj() == zzaaq.MESSAGE && !key.zzuk() && !key.zzul()) {
            if (value instanceof zzxp) {
                return zzwi.zzb(entry.getKey().zzd(), (zzxp) value);
            }
            return zzwi.zzb(entry.getKey().zzd(), (zzyk) value);
        }
        return l(key, value);
    }

    public static Object p(Object obj) {
        if (obj instanceof zzyt) {
            return ((zzyt) obj).zzti();
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            return bArr2;
        }
        return obj;
    }

    public static <T extends zzwt<T>> p6<T> r() {
        return d;
    }

    public final Iterator<Map.Entry<T, Object>> a() {
        if (this.c) {
            return new y6(this.f8717a.p().iterator());
        }
        return this.f8717a.p().iterator();
    }

    public final boolean b() {
        return this.b;
    }

    public final boolean c() {
        for (int i = 0; i < this.f8717a.n(); i++) {
            if (!m(this.f8717a.k(i))) {
                return false;
            }
        }
        for (Map.Entry<T, Object> entry : this.f8717a.o()) {
            if (!m(entry)) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        p6 p6Var = new p6();
        for (int i = 0; i < this.f8717a.n(); i++) {
            Map.Entry<T, Object> k = this.f8717a.k(i);
            p6Var.j(k.getKey(), k.getValue());
        }
        for (Map.Entry<T, Object> entry : this.f8717a.o()) {
            p6Var.j(entry.getKey(), entry.getValue());
        }
        p6Var.c = this.c;
        return p6Var;
    }

    public final Iterator<Map.Entry<T, Object>> d() {
        if (this.c) {
            return new y6(this.f8717a.entrySet().iterator());
        }
        return this.f8717a.entrySet().iterator();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof p6) {
            return this.f8717a.equals(((p6) obj).f8717a);
        }
        return false;
    }

    public final Object f(T t) {
        Object obj = this.f8717a.get(t);
        if (obj instanceof zzxp) {
            zzxp zzxpVar = (zzxp) obj;
            return zzxp.zzvl();
        }
        return obj;
    }

    public final int hashCode() {
        return this.f8717a.hashCode();
    }

    public final void i(p6<T> p6Var) {
        for (int i = 0; i < p6Var.f8717a.n(); i++) {
            n(p6Var.f8717a.k(i));
        }
        for (Map.Entry<T, Object> entry : p6Var.f8717a.o()) {
            n(entry);
        }
    }

    public final void j(T t, Object obj) {
        if (t.zzuk()) {
            if (obj instanceof List) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll((List) obj);
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj2 = arrayList.get(i);
                    i++;
                    g(t.zzui(), obj2);
                }
                obj = arrayList;
            } else {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
        } else {
            g(t.zzui(), obj);
        }
        if (obj instanceof zzxp) {
            this.c = true;
        }
        this.f8717a.put(t, obj);
    }

    public final void n(Map.Entry<T, Object> entry) {
        zzyk zzvb;
        T key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzxp) {
            zzxp zzxpVar = (zzxp) value;
            value = zzxp.zzvl();
        }
        if (key.zzuk()) {
            Object f = f(key);
            if (f == null) {
                f = new ArrayList();
            }
            for (Object obj : (List) value) {
                ((List) f).add(p(obj));
            }
            this.f8717a.put(key, f);
        } else if (key.zzuj() == zzaaq.MESSAGE) {
            Object f2 = f(key);
            if (f2 == null) {
                this.f8717a.put(key, p(value));
                return;
            }
            if (f2 instanceof zzyt) {
                zzvb = key.zza((zzyt) f2, (zzyt) value);
            } else {
                zzvb = key.zza(((zzyk) f2).zzut(), (zzyk) value).zzvb();
            }
            this.f8717a.put(key, zzvb);
        } else {
            this.f8717a.put(key, p(value));
        }
    }

    public final void q() {
        if (this.b) {
            return;
        }
        this.f8717a.m();
        this.b = true;
    }

    public final int s() {
        int i = 0;
        for (int i2 = 0; i2 < this.f8717a.n(); i2++) {
            i += o(this.f8717a.k(i2));
        }
        for (Map.Entry<T, Object> entry : this.f8717a.o()) {
            i += o(entry);
        }
        return i;
    }

    public p6(boolean z) {
        this(h8.j(0));
        q();
    }

    public p6(h8<T, Object> h8Var) {
        this.f8717a = h8Var;
        q();
    }
}
