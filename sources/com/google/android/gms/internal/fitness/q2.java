package com.google.android.gms.internal.fitness;

import com.google.android.gms.internal.fitness.zzgv;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes8.dex */
public final class q2<T extends zzgv<T>> {
    public static final q2 d = new q2(true);

    /* renamed from: a  reason: collision with root package name */
    public final y3<T, Object> f8845a;
    public boolean b;
    public boolean c;

    public q2() {
        this.f8845a = y3.g(16);
    }

    public static int e(zzkg zzkgVar, int i, Object obj) {
        int zzr = zzgk.zzr(i);
        if (zzkgVar == zzkg.zzade) {
            zzhc.e((zzik) obj);
            zzr <<= 1;
        }
        return zzr + l(zzkgVar, obj);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0025, code lost:
        if ((r3 instanceof com.google.android.gms.internal.fitness.zzhb) == false) goto L3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x002e, code lost:
        if ((r3 instanceof byte[]) == false) goto L3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001c, code lost:
        if ((r3 instanceof com.google.android.gms.internal.fitness.zzhl) == false) goto L3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void i(com.google.android.gms.internal.fitness.zzkg r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.fitness.zzhc.a(r3)
            int[] r0 = com.google.android.gms.internal.fitness.p2.f8844a
            com.google.android.gms.internal.fitness.zzkj r2 = r2.zzdx()
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
            boolean r2 = r3 instanceof com.google.android.gms.internal.fitness.zzik
            if (r2 != 0) goto L42
            boolean r2 = r3 instanceof com.google.android.gms.internal.fitness.zzhl
            if (r2 == 0) goto L14
            goto L42
        L1f:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L42
            boolean r2 = r3 instanceof com.google.android.gms.internal.fitness.zzhb
            if (r2 == 0) goto L14
            goto L42
        L28:
            boolean r2 = r3 instanceof com.google.android.gms.internal.fitness.zzfx
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fitness.q2.i(com.google.android.gms.internal.fitness.zzkg, java.lang.Object):void");
    }

    public static int k(zzgv<?> zzgvVar, Object obj) {
        zzkg zzbl = zzgvVar.zzbl();
        int zzc = zzgvVar.zzc();
        if (zzgvVar.zzbn()) {
            int i = 0;
            if (zzgvVar.zzbo()) {
                for (Object obj2 : (List) obj) {
                    i += l(zzbl, obj2);
                }
                return zzgk.zzr(zzc) + i + zzgk.zzz(i);
            }
            for (Object obj3 : (List) obj) {
                i += e(zzbl, zzc, obj3);
            }
            return i;
        }
        return e(zzbl, zzc, obj);
    }

    public static int l(zzkg zzkgVar, Object obj) {
        switch (p2.b[zzkgVar.ordinal()]) {
            case 1:
                return zzgk.zzc(((Double) obj).doubleValue());
            case 2:
                return zzgk.zzc(((Float) obj).floatValue());
            case 3:
                return zzgk.zzd(((Long) obj).longValue());
            case 4:
                return zzgk.zze(((Long) obj).longValue());
            case 5:
                return zzgk.zzs(((Integer) obj).intValue());
            case 6:
                return zzgk.zzg(((Long) obj).longValue());
            case 7:
                return zzgk.zzv(((Integer) obj).intValue());
            case 8:
                return zzgk.zzb(((Boolean) obj).booleanValue());
            case 9:
                return zzgk.zzd((zzik) obj);
            case 10:
                if (obj instanceof zzhl) {
                    return zzgk.zza((zzhl) obj);
                }
                return zzgk.zzc((zzik) obj);
            case 11:
                if (obj instanceof zzfx) {
                    return zzgk.zzb((zzfx) obj);
                }
                return zzgk.zzm((String) obj);
            case 12:
                if (obj instanceof zzfx) {
                    return zzgk.zzb((zzfx) obj);
                }
                return zzgk.zzb((byte[]) obj);
            case 13:
                return zzgk.zzt(((Integer) obj).intValue());
            case 14:
                return zzgk.zzw(((Integer) obj).intValue());
            case 15:
                return zzgk.zzh(((Long) obj).longValue());
            case 16:
                return zzgk.zzu(((Integer) obj).intValue());
            case 17:
                return zzgk.zzf(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzhb) {
                    return zzgk.zzx(((zzhb) obj).zzc());
                }
                return zzgk.zzx(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static <T extends zzgv<T>> boolean m(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        if (key.zzbm() == zzkj.MESSAGE) {
            if (key.zzbn()) {
                for (zzik zzikVar : (List) entry.getValue()) {
                    if (!zzikVar.isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzik) {
                    if (!((zzik) value).isInitialized()) {
                        return false;
                    }
                } else if (value instanceof zzhl) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public static <T extends zzgv<T>> q2<T> n() {
        return d;
    }

    public static int q(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        Object value = entry.getValue();
        if (key.zzbm() == zzkj.MESSAGE && !key.zzbn() && !key.zzbo()) {
            if (value instanceof zzhl) {
                return zzgk.zzb(entry.getKey().zzc(), (zzhl) value);
            }
            return zzgk.zzb(entry.getKey().zzc(), (zzik) value);
        }
        return k(key, value);
    }

    public static Object r(Object obj) {
        if (obj instanceof zziq) {
            return ((zziq) obj).zzao();
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
            return new x2(this.f8845a.p().iterator());
        }
        return this.f8845a.p().iterator();
    }

    public final boolean b() {
        return this.b;
    }

    public final boolean c() {
        for (int i = 0; i < this.f8845a.n(); i++) {
            if (!m(this.f8845a.h(i))) {
                return false;
            }
        }
        for (Map.Entry<T, Object> entry : this.f8845a.o()) {
            if (!m(entry)) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        q2 q2Var = new q2();
        for (int i = 0; i < this.f8845a.n(); i++) {
            Map.Entry<T, Object> h = this.f8845a.h(i);
            q2Var.h(h.getKey(), h.getValue());
        }
        for (Map.Entry<T, Object> entry : this.f8845a.o()) {
            q2Var.h(entry.getKey(), entry.getValue());
        }
        q2Var.c = this.c;
        return q2Var;
    }

    public final Iterator<Map.Entry<T, Object>> d() {
        if (this.c) {
            return new x2(this.f8845a.entrySet().iterator());
        }
        return this.f8845a.entrySet().iterator();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof q2) {
            return this.f8845a.equals(((q2) obj).f8845a);
        }
        return false;
    }

    public final Object f(T t) {
        Object obj = this.f8845a.get(t);
        if (obj instanceof zzhl) {
            zzhl zzhlVar = (zzhl) obj;
            return zzhl.zzce();
        }
        return obj;
    }

    public final void g(q2<T> q2Var) {
        for (int i = 0; i < q2Var.f8845a.n(); i++) {
            p(q2Var.f8845a.h(i));
        }
        for (Map.Entry<T, Object> entry : q2Var.f8845a.o()) {
            p(entry);
        }
    }

    public final void h(T t, Object obj) {
        if (t.zzbn()) {
            if (obj instanceof List) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll((List) obj);
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj2 = arrayList.get(i);
                    i++;
                    i(t.zzbl(), obj2);
                }
                obj = arrayList;
            } else {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
        } else {
            i(t.zzbl(), obj);
        }
        if (obj instanceof zzhl) {
            this.c = true;
        }
        this.f8845a.put(t, obj);
    }

    public final int hashCode() {
        return this.f8845a.hashCode();
    }

    public final void j() {
        if (this.b) {
            return;
        }
        this.f8845a.j();
        this.b = true;
    }

    public final int o() {
        int i = 0;
        for (int i2 = 0; i2 < this.f8845a.n(); i2++) {
            i += q(this.f8845a.h(i2));
        }
        for (Map.Entry<T, Object> entry : this.f8845a.o()) {
            i += q(entry);
        }
        return i;
    }

    public final void p(Map.Entry<T, Object> entry) {
        zzik zzca;
        T key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzhl) {
            zzhl zzhlVar = (zzhl) value;
            value = zzhl.zzce();
        }
        if (key.zzbn()) {
            Object f = f(key);
            if (f == null) {
                f = new ArrayList();
            }
            for (Object obj : (List) value) {
                ((List) f).add(r(obj));
            }
            this.f8845a.put(key, f);
        } else if (key.zzbm() == zzkj.MESSAGE) {
            Object f2 = f(key);
            if (f2 == null) {
                this.f8845a.put(key, r(value));
                return;
            }
            if (f2 instanceof zziq) {
                zzca = key.zza((zziq) f2, (zziq) value);
            } else {
                zzca = key.zza(((zzik) f2).zzbt(), (zzik) value).zzca();
            }
            this.f8845a.put(key, zzca);
        } else {
            this.f8845a.put(key, r(value));
        }
    }

    public q2(boolean z) {
        this(y3.g(0));
        j();
    }

    public q2(y3<T, Object> y3Var) {
        this.f8845a = y3Var;
        j();
    }
}
