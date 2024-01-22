package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzrz;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
final class zzrx<T extends zzrz<T>> {
    private static final zzrx zzd = new zzrx(true);
    public final zzuj<T, Object> zza;
    private boolean zzb;
    private boolean zzc;

    private zzrx() {
        this.zza = zzuj.zza(16);
    }

    public static <T extends zzrz<T>> zzrx<T> zza() {
        return zzd;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzrx zzrxVar = new zzrx();
        for (int i = 0; i < this.zza.zzc(); i++) {
            Map.Entry<T, Object> zzb = this.zza.zzb(i);
            zzrxVar.zzb((zzrx) zzb.getKey(), zzb.getValue());
        }
        for (Map.Entry<T, Object> entry : this.zza.zzd()) {
            zzrxVar.zzb((zzrx) entry.getKey(), entry.getValue());
        }
        zzrxVar.zzc = this.zzc;
        return zzrxVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzrx) {
            return this.zza.equals(((zzrx) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final void zzb() {
        if (this.zzb) {
            return;
        }
        this.zza.zza();
        this.zzb = true;
    }

    public final boolean zzc() {
        return this.zzb;
    }

    public final Iterator<Map.Entry<T, Object>> zzd() {
        if (this.zzc) {
            return new zzsu(this.zza.entrySet().iterator());
        }
        return this.zza.entrySet().iterator();
    }

    public final Iterator<Map.Entry<T, Object>> zze() {
        if (this.zzc) {
            return new zzsu(this.zza.zze().iterator());
        }
        return this.zza.zze().iterator();
    }

    public final boolean zzf() {
        for (int i = 0; i < this.zza.zzc(); i++) {
            if (!zza((Map.Entry) this.zza.zzb(i))) {
                return false;
            }
        }
        for (Map.Entry<T, Object> entry : this.zza.zzd()) {
            if (!zza((Map.Entry) entry)) {
                return false;
            }
        }
        return true;
    }

    public final int zzg() {
        int i = 0;
        for (int i2 = 0; i2 < this.zza.zzc(); i2++) {
            i += zzc(this.zza.zzb(i2));
        }
        for (Map.Entry<T, Object> entry : this.zza.zzd()) {
            i += zzc(entry);
        }
        return i;
    }

    private final Object zza(T t) {
        Object obj = this.zza.get(t);
        if (obj instanceof zzst) {
            zzst zzstVar = (zzst) obj;
            return zzst.zza();
        }
        return obj;
    }

    private static int zzc(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        Object value = entry.getValue();
        if (key.zzc() == zzvr.MESSAGE && !key.zzd() && !key.zze()) {
            if (value instanceof zzst) {
                return zzrs.zzb(entry.getKey().zza(), (zzst) value);
            }
            return zzrs.zzb(entry.getKey().zza(), (zzto) value);
        }
        return zza((zzrz<?>) key, value);
    }

    private zzrx(boolean z) {
        this(zzuj.zza(0));
        zzb();
    }

    private final void zzb(T t, Object obj) {
        if (t.zzd()) {
            if (obj instanceof List) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll((List) obj);
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj2 = arrayList.get(i);
                    i++;
                    zza(t.zzb(), obj2);
                }
                obj = arrayList;
            } else {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
        } else {
            zza(t.zzb(), obj);
        }
        if (obj instanceof zzst) {
            this.zzc = true;
        }
        this.zza.zza((zzuj<T, Object>) t, (T) obj);
    }

    private zzrx(zzuj<T, Object> zzujVar) {
        this.zza = zzujVar;
        zzb();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0025, code lost:
        if ((r3 instanceof com.google.android.libraries.places.internal.zzsj) == false) goto L3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x002e, code lost:
        if ((r3 instanceof byte[]) == false) goto L3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001c, code lost:
        if ((r3 instanceof com.google.android.libraries.places.internal.zzst) == false) goto L3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void zza(com.google.android.libraries.places.internal.zzvk r2, java.lang.Object r3) {
        /*
            com.google.android.libraries.places.internal.zzsg.zza(r3)
            int[] r0 = com.google.android.libraries.places.internal.zzsa.zza
            com.google.android.libraries.places.internal.zzvr r2 = r2.zza()
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
            boolean r2 = r3 instanceof com.google.android.libraries.places.internal.zzto
            if (r2 != 0) goto L42
            boolean r2 = r3 instanceof com.google.android.libraries.places.internal.zzst
            if (r2 == 0) goto L14
            goto L42
        L1f:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L42
            boolean r2 = r3 instanceof com.google.android.libraries.places.internal.zzsj
            if (r2 == 0) goto L14
            goto L42
        L28:
            boolean r2 = r3 instanceof com.google.android.libraries.places.internal.zzrb
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzrx.zza(com.google.android.libraries.places.internal.zzvk, java.lang.Object):void");
    }

    private final void zzb(Map.Entry<T, Object> entry) {
        zzto zzg;
        T key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzst) {
            zzst zzstVar = (zzst) value;
            value = zzst.zza();
        }
        if (key.zzd()) {
            Object zza = zza((zzrx<T>) key);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object obj : (List) value) {
                ((List) zza).add(zza(obj));
            }
            this.zza.zza((zzuj<T, Object>) key, (T) zza);
        } else if (key.zzc() == zzvr.MESSAGE) {
            Object zza2 = zza((zzrx<T>) key);
            if (zza2 == null) {
                this.zza.zza((zzuj<T, Object>) key, (T) zza(value));
                return;
            }
            if (zza2 instanceof zztu) {
                zzg = key.zza((zztu) zza2, (zztu) value);
            } else {
                zzg = key.zza(((zzto) zza2).zzl(), (zzto) value).zzg();
            }
            this.zza.zza((zzuj<T, Object>) key, (T) zzg);
        } else {
            this.zza.zza((zzuj<T, Object>) key, (T) zza(value));
        }
    }

    private static <T extends zzrz<T>> boolean zza(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        if (key.zzc() == zzvr.MESSAGE) {
            if (key.zzd()) {
                for (zzto zztoVar : (List) entry.getValue()) {
                    if (!zztoVar.zzc()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzto) {
                    if (!((zzto) value).zzc()) {
                        return false;
                    }
                } else if (value instanceof zzst) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzrx<T> zzrxVar) {
        for (int i = 0; i < zzrxVar.zza.zzc(); i++) {
            zzb(zzrxVar.zza.zzb(i));
        }
        for (Map.Entry<T, Object> entry : zzrxVar.zza.zzd()) {
            zzb(entry);
        }
    }

    private static Object zza(Object obj) {
        if (obj instanceof zztu) {
            return ((zztu) obj).zza();
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            return bArr2;
        }
        return obj;
    }

    private static int zzb(zzvk zzvkVar, Object obj) {
        switch (zzsa.zzb[zzvkVar.ordinal()]) {
            case 1:
                return zzrs.zzb(((Double) obj).doubleValue());
            case 2:
                return zzrs.zzb(((Float) obj).floatValue());
            case 3:
                return zzrs.zzd(((Long) obj).longValue());
            case 4:
                return zzrs.zze(((Long) obj).longValue());
            case 5:
                return zzrs.zzf(((Integer) obj).intValue());
            case 6:
                return zzrs.zzg(((Long) obj).longValue());
            case 7:
                return zzrs.zzi(((Integer) obj).intValue());
            case 8:
                return zzrs.zzb(((Boolean) obj).booleanValue());
            case 9:
                return zzrs.zzc((zzto) obj);
            case 10:
                if (obj instanceof zzst) {
                    return zzrs.zza((zzst) obj);
                }
                return zzrs.zzb((zzto) obj);
            case 11:
                if (obj instanceof zzrb) {
                    return zzrs.zzb((zzrb) obj);
                }
                return zzrs.zzb((String) obj);
            case 12:
                if (obj instanceof zzrb) {
                    return zzrs.zzb((zzrb) obj);
                }
                return zzrs.zzb((byte[]) obj);
            case 13:
                return zzrs.zzg(((Integer) obj).intValue());
            case 14:
                return zzrs.zzj(((Integer) obj).intValue());
            case 15:
                return zzrs.zzh(((Long) obj).longValue());
            case 16:
                return zzrs.zzh(((Integer) obj).intValue());
            case 17:
                return zzrs.zzf(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzsj) {
                    return zzrs.zzk(((zzsj) obj).zza());
                }
                return zzrs.zzk(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static void zza(zzrs zzrsVar, zzvk zzvkVar, int i, Object obj) throws IOException {
        if (zzvkVar == zzvk.zzj) {
            zzto zztoVar = (zzto) obj;
            zzsg.zza(zztoVar);
            zzrsVar.zza(i, 3);
            zztoVar.zza(zzrsVar);
            zzrsVar.zza(i, 4);
            return;
        }
        zzrsVar.zza(i, zzvkVar.zzb());
        switch (zzsa.zzb[zzvkVar.ordinal()]) {
            case 1:
                zzrsVar.zza(((Double) obj).doubleValue());
                return;
            case 2:
                zzrsVar.zza(((Float) obj).floatValue());
                return;
            case 3:
                zzrsVar.zza(((Long) obj).longValue());
                return;
            case 4:
                zzrsVar.zza(((Long) obj).longValue());
                return;
            case 5:
                zzrsVar.zza(((Integer) obj).intValue());
                return;
            case 6:
                zzrsVar.zzc(((Long) obj).longValue());
                return;
            case 7:
                zzrsVar.zzd(((Integer) obj).intValue());
                return;
            case 8:
                zzrsVar.zza(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzto) obj).zza(zzrsVar);
                return;
            case 10:
                zzrsVar.zza((zzto) obj);
                return;
            case 11:
                if (obj instanceof zzrb) {
                    zzrsVar.zza((zzrb) obj);
                    return;
                } else {
                    zzrsVar.zza((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzrb) {
                    zzrsVar.zza((zzrb) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzrsVar.zzb(bArr, 0, bArr.length);
                return;
            case 13:
                zzrsVar.zzb(((Integer) obj).intValue());
                return;
            case 14:
                zzrsVar.zzd(((Integer) obj).intValue());
                return;
            case 15:
                zzrsVar.zzc(((Long) obj).longValue());
                return;
            case 16:
                zzrsVar.zzc(((Integer) obj).intValue());
                return;
            case 17:
                zzrsVar.zzb(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzsj) {
                    zzrsVar.zza(((zzsj) obj).zza());
                    return;
                } else {
                    zzrsVar.zza(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    public static int zza(zzvk zzvkVar, int i, Object obj) {
        int zze = zzrs.zze(i);
        if (zzvkVar == zzvk.zzj) {
            zzsg.zza((zzto) obj);
            zze <<= 1;
        }
        return zze + zzb(zzvkVar, obj);
    }

    public static int zza(zzrz<?> zzrzVar, Object obj) {
        zzvk zzb = zzrzVar.zzb();
        int zza = zzrzVar.zza();
        if (zzrzVar.zzd()) {
            int i = 0;
            if (zzrzVar.zze()) {
                for (Object obj2 : (List) obj) {
                    i += zzb(zzb, obj2);
                }
                return zzrs.zze(zza) + i + zzrs.zzl(i);
            }
            for (Object obj3 : (List) obj) {
                i += zza(zzb, zza, obj3);
            }
            return i;
        }
        return zza(zzb, zza, obj);
    }
}
