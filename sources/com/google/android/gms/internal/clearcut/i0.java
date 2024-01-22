package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzca;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes7.dex */
public final class i0<FieldDescriptorType extends zzca<FieldDescriptorType>> {
    public static final i0 d = new i0(true);
    public boolean b;
    public boolean c = false;

    /* renamed from: a  reason: collision with root package name */
    public final u1<FieldDescriptorType, Object> f8581a = u1.g(16);

    public i0() {
    }

    public i0(boolean z) {
        t();
    }

    public static int f(zzfl zzflVar, int i, Object obj) {
        int zzr = zzbn.zzr(i);
        if (zzflVar == zzfl.zzql) {
            zzci.e((zzdo) obj);
            zzr <<= 1;
        }
        return zzr + o(zzflVar, obj);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001e, code lost:
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0026, code lost:
        if ((r3 instanceof com.google.android.gms.internal.clearcut.zzcj) == false) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x002f, code lost:
        if ((r3 instanceof byte[]) == false) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001b, code lost:
        if ((r3 instanceof com.google.android.gms.internal.clearcut.zzcr) == false) goto L7;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0046 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void j(com.google.android.gms.internal.clearcut.zzfl r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.clearcut.zzci.a(r3)
            int[] r0 = com.google.android.gms.internal.clearcut.j0.f8584a
            com.google.android.gms.internal.clearcut.zzfq r2 = r2.zzek()
            int r2 = r2.ordinal()
            r2 = r0[r2]
            r0 = 1
            r1 = 0
            switch(r2) {
                case 1: goto L41;
                case 2: goto L3e;
                case 3: goto L3b;
                case 4: goto L38;
                case 5: goto L35;
                case 6: goto L32;
                case 7: goto L29;
                case 8: goto L20;
                case 9: goto L15;
                default: goto L14;
            }
        L14:
            goto L44
        L15:
            boolean r2 = r3 instanceof com.google.android.gms.internal.clearcut.zzdo
            if (r2 != 0) goto L43
            boolean r2 = r3 instanceof com.google.android.gms.internal.clearcut.zzcr
            if (r2 == 0) goto L1e
            goto L43
        L1e:
            r0 = r1
            goto L43
        L20:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L43
            boolean r2 = r3 instanceof com.google.android.gms.internal.clearcut.zzcj
            if (r2 == 0) goto L1e
            goto L43
        L29:
            boolean r2 = r3 instanceof com.google.android.gms.internal.clearcut.zzbb
            if (r2 != 0) goto L43
            boolean r2 = r3 instanceof byte[]
            if (r2 == 0) goto L1e
            goto L43
        L32:
            boolean r0 = r3 instanceof java.lang.String
            goto L43
        L35:
            boolean r0 = r3 instanceof java.lang.Boolean
            goto L43
        L38:
            boolean r0 = r3 instanceof java.lang.Double
            goto L43
        L3b:
            boolean r0 = r3 instanceof java.lang.Float
            goto L43
        L3e:
            boolean r0 = r3 instanceof java.lang.Long
            goto L43
        L41:
            boolean r0 = r3 instanceof java.lang.Integer
        L43:
            r1 = r0
        L44:
            if (r1 == 0) goto L47
            return
        L47:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Wrong object type used with protocol message reflection."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.i0.j(com.google.android.gms.internal.clearcut.zzfl, java.lang.Object):void");
    }

    public static <T extends zzca<T>> i0<T> k() {
        return d;
    }

    public static int n(zzca<?> zzcaVar, Object obj) {
        zzfl zzau = zzcaVar.zzau();
        int zzc = zzcaVar.zzc();
        if (zzcaVar.zzaw()) {
            int i = 0;
            List<Object> list = (List) obj;
            if (zzcaVar.zzax()) {
                for (Object obj2 : list) {
                    i += o(zzau, obj2);
                }
                return zzbn.zzr(zzc) + i + zzbn.zzz(i);
            }
            for (Object obj3 : list) {
                i += f(zzau, zzc, obj3);
            }
            return i;
        }
        return f(zzau, zzc, obj);
    }

    public static int o(zzfl zzflVar, Object obj) {
        switch (j0.b[zzflVar.ordinal()]) {
            case 1:
                return zzbn.zzb(((Double) obj).doubleValue());
            case 2:
                return zzbn.zzb(((Float) obj).floatValue());
            case 3:
                return zzbn.zze(((Long) obj).longValue());
            case 4:
                return zzbn.zzf(((Long) obj).longValue());
            case 5:
                return zzbn.zzs(((Integer) obj).intValue());
            case 6:
                return zzbn.zzh(((Long) obj).longValue());
            case 7:
                return zzbn.zzv(((Integer) obj).intValue());
            case 8:
                return zzbn.zzb(((Boolean) obj).booleanValue());
            case 9:
                return zzbn.zzd((zzdo) obj);
            case 10:
                return obj instanceof zzcr ? zzbn.zza((zzcr) obj) : zzbn.zzc((zzdo) obj);
            case 11:
                return obj instanceof zzbb ? zzbn.zzb((zzbb) obj) : zzbn.zzh((String) obj);
            case 12:
                return obj instanceof zzbb ? zzbn.zzb((zzbb) obj) : zzbn.zzd((byte[]) obj);
            case 13:
                return zzbn.zzt(((Integer) obj).intValue());
            case 14:
                return zzbn.zzw(((Integer) obj).intValue());
            case 15:
                return zzbn.zzi(((Long) obj).longValue());
            case 16:
                return zzbn.zzu(((Integer) obj).intValue());
            case 17:
                return zzbn.zzg(((Long) obj).longValue());
            case 18:
                return obj instanceof zzcj ? zzbn.zzx(((zzcj) obj).zzc()) : zzbn.zzx(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static boolean p(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        if (key.zzav() == zzfq.MESSAGE) {
            boolean zzaw = key.zzaw();
            Object value = entry.getValue();
            if (zzaw) {
                for (zzdo zzdoVar : (List) value) {
                    if (!zzdoVar.isInitialized()) {
                        return false;
                    }
                }
            } else if (!(value instanceof zzdo)) {
                if (value instanceof zzcr) {
                    return true;
                }
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            } else if (!((zzdo) value).isInitialized()) {
                return false;
            }
        }
        return true;
    }

    public static int r(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        Object value = entry.getValue();
        if (key.zzav() != zzfq.MESSAGE || key.zzaw() || key.zzax()) {
            return n(key, value);
        }
        boolean z = value instanceof zzcr;
        int zzc = entry.getKey().zzc();
        return z ? zzbn.zzb(zzc, (zzcr) value) : zzbn.zzd(zzc, (zzdo) value);
    }

    public static Object s(Object obj) {
        if (obj instanceof zzdv) {
            return ((zzdv) obj).zzci();
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            return bArr2;
        }
        return obj;
    }

    public final Iterator<Map.Entry<FieldDescriptorType, Object>> a() {
        return this.c ? new r0(this.f8581a.o().iterator()) : this.f8581a.o().iterator();
    }

    public final boolean b() {
        return this.f8581a.isEmpty();
    }

    public final boolean c() {
        return this.b;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        i0 i0Var = new i0();
        for (int i = 0; i < this.f8581a.m(); i++) {
            Map.Entry<FieldDescriptorType, Object> h = this.f8581a.h(i);
            i0Var.i(h.getKey(), h.getValue());
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.f8581a.n()) {
            i0Var.i(entry.getKey(), entry.getValue());
        }
        i0Var.c = this.c;
        return i0Var;
    }

    public final boolean d() {
        for (int i = 0; i < this.f8581a.m(); i++) {
            if (!p(this.f8581a.h(i))) {
                return false;
            }
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.f8581a.n()) {
            if (!p(entry)) {
                return false;
            }
        }
        return true;
    }

    public final Iterator<Map.Entry<FieldDescriptorType, Object>> e() {
        return this.c ? new r0(this.f8581a.entrySet().iterator()) : this.f8581a.entrySet().iterator();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof i0) {
            return this.f8581a.equals(((i0) obj).f8581a);
        }
        return false;
    }

    public final Object g(FieldDescriptorType fielddescriptortype) {
        Object obj = this.f8581a.get(fielddescriptortype);
        return obj instanceof zzcr ? zzcr.zzbr() : obj;
    }

    public final void h(i0<FieldDescriptorType> i0Var) {
        for (int i = 0; i < i0Var.f8581a.m(); i++) {
            q(i0Var.f8581a.h(i));
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : i0Var.f8581a.n()) {
            q(entry);
        }
    }

    public final int hashCode() {
        return this.f8581a.hashCode();
    }

    public final void i(FieldDescriptorType fielddescriptortype, Object obj) {
        if (!fielddescriptortype.zzaw()) {
            j(fielddescriptortype.zzau(), obj);
        } else if (!(obj instanceof List)) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList.get(i);
                i++;
                j(fielddescriptortype.zzau(), obj2);
            }
            obj = arrayList;
        }
        if (obj instanceof zzcr) {
            this.c = true;
        }
        this.f8581a.put(fielddescriptortype, obj);
    }

    public final int l() {
        int i = 0;
        for (int i2 = 0; i2 < this.f8581a.m(); i2++) {
            Map.Entry<FieldDescriptorType, Object> h = this.f8581a.h(i2);
            i += n(h.getKey(), h.getValue());
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.f8581a.n()) {
            i += n(entry.getKey(), entry.getValue());
        }
        return i;
    }

    public final int m() {
        int i = 0;
        for (int i2 = 0; i2 < this.f8581a.m(); i2++) {
            i += r(this.f8581a.h(i2));
        }
        for (Map.Entry<FieldDescriptorType, Object> entry : this.f8581a.n()) {
            i += r(entry);
        }
        return i;
    }

    public final void q(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorType key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzcr) {
            value = zzcr.zzbr();
        }
        if (key.zzaw()) {
            Object g = g(key);
            if (g == null) {
                g = new ArrayList();
            }
            for (Object obj : (List) value) {
                ((List) g).add(s(obj));
            }
            this.f8581a.put(key, g);
        } else if (key.zzav() != zzfq.MESSAGE) {
            this.f8581a.put(key, s(value));
        } else {
            Object g2 = g(key);
            if (g2 == null) {
                this.f8581a.put(key, s(value));
            } else {
                this.f8581a.put(key, g2 instanceof zzdv ? key.zza((zzdv) g2, (zzdv) value) : key.zza(((zzdo) g2).zzbc(), (zzdo) value).zzbj());
            }
        }
    }

    public final void t() {
        if (this.b) {
            return;
        }
        this.f8581a.r();
        this.b = true;
    }
}
