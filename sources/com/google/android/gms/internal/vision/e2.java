package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgk;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public final class e2<T extends zzgk<T>> {
    public static final e2 d = new e2(true);

    /* renamed from: a  reason: collision with root package name */
    public final t3<T, Object> f9969a;
    public boolean b;
    public boolean c;

    public e2() {
        this.f9969a = t3.h(16);
    }

    public static int e(zzka zzkaVar, int i, Object obj) {
        int zzbb = zzga.zzbb(i);
        if (zzkaVar == zzka.zzacb) {
            zzgt.e((zzic) obj);
            zzbb <<= 1;
        }
        return zzbb + j(zzkaVar, obj);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0025, code lost:
        if ((r3 instanceof com.google.android.gms.internal.vision.zzgw) == false) goto L3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x002e, code lost:
        if ((r3 instanceof byte[]) == false) goto L3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001c, code lost:
        if ((r3 instanceof com.google.android.gms.internal.vision.zzhd) == false) goto L3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void i(com.google.android.gms.internal.vision.zzka r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.vision.zzgt.a(r3)
            int[] r0 = com.google.android.gms.internal.vision.g2.f9977a
            com.google.android.gms.internal.vision.zzkd r2 = r2.zzip()
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
            boolean r2 = r3 instanceof com.google.android.gms.internal.vision.zzic
            if (r2 != 0) goto L42
            boolean r2 = r3 instanceof com.google.android.gms.internal.vision.zzhd
            if (r2 == 0) goto L14
            goto L42
        L1f:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L42
            boolean r2 = r3 instanceof com.google.android.gms.internal.vision.zzgw
            if (r2 == 0) goto L14
            goto L42
        L28:
            boolean r2 = r3 instanceof com.google.android.gms.internal.vision.zzfh
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.e2.i(com.google.android.gms.internal.vision.zzka, java.lang.Object):void");
    }

    public static int j(zzka zzkaVar, Object obj) {
        switch (g2.b[zzkaVar.ordinal()]) {
            case 1:
                return zzga.zzb(((Double) obj).doubleValue());
            case 2:
                return zzga.zzt(((Float) obj).floatValue());
            case 3:
                return zzga.zzv(((Long) obj).longValue());
            case 4:
                return zzga.zzw(((Long) obj).longValue());
            case 5:
                return zzga.zzbc(((Integer) obj).intValue());
            case 6:
                return zzga.zzy(((Long) obj).longValue());
            case 7:
                return zzga.zzbf(((Integer) obj).intValue());
            case 8:
                return zzga.zzl(((Boolean) obj).booleanValue());
            case 9:
                return zzga.zzd((zzic) obj);
            case 10:
                if (obj instanceof zzhd) {
                    return zzga.zza((zzhd) obj);
                }
                return zzga.zzc((zzic) obj);
            case 11:
                if (obj instanceof zzfh) {
                    return zzga.zzb((zzfh) obj);
                }
                return zzga.zzy((String) obj);
            case 12:
                if (obj instanceof zzfh) {
                    return zzga.zzb((zzfh) obj);
                }
                return zzga.zzf((byte[]) obj);
            case 13:
                return zzga.zzbd(((Integer) obj).intValue());
            case 14:
                return zzga.zzbg(((Integer) obj).intValue());
            case 15:
                return zzga.zzz(((Long) obj).longValue());
            case 16:
                return zzga.zzbe(((Integer) obj).intValue());
            case 17:
                return zzga.zzx(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzgw) {
                    return zzga.zzbh(((zzgw) obj).zzag());
                }
                return zzga.zzbh(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static <T extends zzgk<T>> boolean l(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        if (key.zzft() == zzkd.MESSAGE) {
            if (key.zzfu()) {
                for (zzic zzicVar : (List) entry.getValue()) {
                    if (!zzicVar.isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzic) {
                    if (!((zzic) value).isInitialized()) {
                        return false;
                    }
                } else if (value instanceof zzhd) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public static int m(zzgk<?> zzgkVar, Object obj) {
        zzka zzfs = zzgkVar.zzfs();
        int zzag = zzgkVar.zzag();
        if (zzgkVar.zzfu()) {
            int i = 0;
            if (zzgkVar.zzfv()) {
                for (Object obj2 : (List) obj) {
                    i += j(zzfs, obj2);
                }
                return zzga.zzbb(zzag) + i + zzga.zzbj(i);
            }
            for (Object obj3 : (List) obj) {
                i += e(zzfs, zzag, obj3);
            }
            return i;
        }
        return e(zzfs, zzag, obj);
    }

    public static int o(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        Object value = entry.getValue();
        if (key.zzft() == zzkd.MESSAGE && !key.zzfu() && !key.zzfv()) {
            if (value instanceof zzhd) {
                return zzga.zzb(entry.getKey().zzag(), (zzhd) value);
            }
            return zzga.zzb(entry.getKey().zzag(), (zzic) value);
        }
        return m(key, value);
    }

    public static <T extends zzgk<T>> e2<T> q() {
        return d;
    }

    public static Object s(Object obj) {
        if (obj instanceof zzih) {
            return ((zzih) obj).zzdm();
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            return bArr2;
        }
        return obj;
    }

    public final Iterator<Map.Entry<T, Object>> a() {
        if (this.c) {
            return new p2(this.f9969a.p().iterator());
        }
        return this.f9969a.p().iterator();
    }

    public final boolean b() {
        return this.b;
    }

    public final boolean c() {
        for (int i = 0; i < this.f9969a.n(); i++) {
            if (!l(this.f9969a.i(i))) {
                return false;
            }
        }
        for (Map.Entry<T, Object> entry : this.f9969a.o()) {
            if (!l(entry)) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        e2 e2Var = new e2();
        for (int i = 0; i < this.f9969a.n(); i++) {
            Map.Entry<T, Object> i2 = this.f9969a.i(i);
            e2Var.h(i2.getKey(), i2.getValue());
        }
        for (Map.Entry<T, Object> entry : this.f9969a.o()) {
            e2Var.h(entry.getKey(), entry.getValue());
        }
        e2Var.c = this.c;
        return e2Var;
    }

    public final Iterator<Map.Entry<T, Object>> d() {
        if (this.c) {
            return new p2(this.f9969a.entrySet().iterator());
        }
        return this.f9969a.entrySet().iterator();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof e2) {
            return this.f9969a.equals(((e2) obj).f9969a);
        }
        return false;
    }

    public final Object f(T t) {
        Object obj = this.f9969a.get(t);
        if (obj instanceof zzhd) {
            zzhd zzhdVar = (zzhd) obj;
            return zzhd.zzgu();
        }
        return obj;
    }

    public final void g(e2<T> e2Var) {
        for (int i = 0; i < e2Var.f9969a.n(); i++) {
            n(e2Var.f9969a.i(i));
        }
        for (Map.Entry<T, Object> entry : e2Var.f9969a.o()) {
            n(entry);
        }
    }

    public final void h(T t, Object obj) {
        if (t.zzfu()) {
            if (obj instanceof List) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll((List) obj);
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj2 = arrayList.get(i);
                    i++;
                    i(t.zzfs(), obj2);
                }
                obj = arrayList;
            } else {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
        } else {
            i(t.zzfs(), obj);
        }
        if (obj instanceof zzhd) {
            this.c = true;
        }
        this.f9969a.put(t, obj);
    }

    public final int hashCode() {
        return this.f9969a.hashCode();
    }

    public final void k(T t, Object obj) {
        List list;
        if (t.zzfu()) {
            i(t.zzfs(), obj);
            Object f = f(t);
            if (f == null) {
                list = new ArrayList();
                this.f9969a.put(t, list);
            } else {
                list = (List) f;
            }
            list.add(obj);
            return;
        }
        throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
    }

    public final void n(Map.Entry<T, Object> entry) {
        zzic zzgc;
        T key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzhd) {
            zzhd zzhdVar = (zzhd) value;
            value = zzhd.zzgu();
        }
        if (key.zzfu()) {
            Object f = f(key);
            if (f == null) {
                f = new ArrayList();
            }
            for (Object obj : (List) value) {
                ((List) f).add(s(obj));
            }
            this.f9969a.put(key, f);
        } else if (key.zzft() == zzkd.MESSAGE) {
            Object f2 = f(key);
            if (f2 == null) {
                this.f9969a.put(key, s(value));
                return;
            }
            if (f2 instanceof zzih) {
                zzgc = key.zza((zzih) f2, (zzih) value);
            } else {
                zzgc = key.zza(((zzic) f2).zzgi(), (zzic) value).zzgc();
            }
            this.f9969a.put(key, zzgc);
        } else {
            this.f9969a.put(key, s(value));
        }
    }

    public final void p() {
        if (this.b) {
            return;
        }
        this.f9969a.m();
        this.b = true;
    }

    public final int r() {
        int i = 0;
        for (int i2 = 0; i2 < this.f9969a.n(); i2++) {
            i += o(this.f9969a.i(i2));
        }
        for (Map.Entry<T, Object> entry : this.f9969a.o()) {
            i += o(entry);
        }
        return i;
    }

    public e2(boolean z) {
        this(t3.h(0));
        p();
    }

    public e2(t3<T, Object> t3Var) {
        this.f9969a = t3Var;
        p();
    }
}
