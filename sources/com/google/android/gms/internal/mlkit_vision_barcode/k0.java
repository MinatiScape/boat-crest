package com.google.android.gms.internal.mlkit_vision_barcode;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
/* loaded from: classes9.dex */
public final class k0 extends AbstractMap implements Serializable {
    private static final Object zzd = new Object();
    @CheckForNull
    public transient int[] zza;
    @CheckForNull
    public transient Object[] zzb;
    @CheckForNull
    public transient Object[] zzc;
    @CheckForNull
    private transient Object zze;
    private transient int zzf;
    private transient int zzg;
    @CheckForNull
    private transient Set zzh;
    @CheckForNull
    private transient Set zzi;
    @CheckForNull
    private transient Collection zzj;

    public k0() {
        zzm(3);
    }

    public static /* synthetic */ Object zzh(k0 k0Var) {
        Object obj = k0Var.zze;
        obj.getClass();
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zzp() {
        return (1 << (this.zzf & 31)) - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zzq(@CheckForNull Object obj) {
        if (zzo()) {
            return -1;
        }
        int a2 = m0.a(obj);
        int zzp = zzp();
        Object obj2 = this.zze;
        obj2.getClass();
        int c = l0.c(obj2, a2 & zzp);
        if (c != 0) {
            int i = ~zzp;
            int i2 = a2 & i;
            do {
                int i3 = c - 1;
                int[] iArr = this.zza;
                iArr.getClass();
                int i4 = iArr[i3];
                if ((i4 & i) == i2) {
                    Object[] objArr = this.zzb;
                    objArr.getClass();
                    if (zzay.zza(obj, objArr[i3])) {
                        return i3;
                    }
                }
                c = i4 & zzp;
            } while (c != 0);
            return -1;
        }
        return -1;
    }

    private final int zzr(int i, int i2, int i3, int i4) {
        int i5 = i2 - 1;
        Object d = l0.d(i2);
        if (i4 != 0) {
            l0.e(d, i3 & i5, i4 + 1);
        }
        Object obj = this.zze;
        obj.getClass();
        int[] iArr = this.zza;
        iArr.getClass();
        for (int i6 = 0; i6 <= i; i6++) {
            int c = l0.c(obj, i6);
            while (c != 0) {
                int i7 = c - 1;
                int i8 = iArr[i7];
                int i9 = ((~i) & i8) | i6;
                int i10 = i9 & i5;
                int c2 = l0.c(d, i10);
                l0.e(d, i10, c);
                iArr[i7] = ((~i5) & i9) | (c2 & i5);
                c = i8 & i;
            }
        }
        this.zze = d;
        zzt(i5);
        return i5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object zzs(@CheckForNull Object obj) {
        if (zzo()) {
            return zzd;
        }
        int zzp = zzp();
        Object obj2 = this.zze;
        obj2.getClass();
        int[] iArr = this.zza;
        iArr.getClass();
        Object[] objArr = this.zzb;
        objArr.getClass();
        int b = l0.b(obj, null, zzp, obj2, iArr, objArr, null);
        if (b == -1) {
            return zzd;
        }
        Object[] objArr2 = this.zzc;
        objArr2.getClass();
        Object obj3 = objArr2[b];
        zzn(b, zzp);
        this.zzg--;
        zzl();
        return obj3;
    }

    private final void zzt(int i) {
        this.zzf = ((32 - Integer.numberOfLeadingZeros(i)) & 31) | (this.zzf & (-32));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        if (zzo()) {
            return;
        }
        zzl();
        Map zzj = zzj();
        if (zzj == null) {
            Object[] objArr = this.zzb;
            objArr.getClass();
            Arrays.fill(objArr, 0, this.zzg, (Object) null);
            Object[] objArr2 = this.zzc;
            objArr2.getClass();
            Arrays.fill(objArr2, 0, this.zzg, (Object) null);
            Object obj = this.zze;
            obj.getClass();
            if (obj instanceof byte[]) {
                Arrays.fill((byte[]) obj, (byte) 0);
            } else if (obj instanceof short[]) {
                Arrays.fill((short[]) obj, (short) 0);
            } else {
                Arrays.fill((int[]) obj, 0);
            }
            int[] iArr = this.zza;
            iArr.getClass();
            Arrays.fill(iArr, 0, this.zzg, 0);
            this.zzg = 0;
            return;
        }
        this.zzf = zzdz.zza(size(), 3, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
        zzj.clear();
        this.zze = null;
        this.zzg = 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(@CheckForNull Object obj) {
        Map zzj = zzj();
        if (zzj != null) {
            return zzj.containsKey(obj);
        }
        return zzq(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(@CheckForNull Object obj) {
        Map zzj = zzj();
        if (zzj == null) {
            for (int i = 0; i < this.zzg; i++) {
                Object[] objArr = this.zzc;
                objArr.getClass();
                if (zzay.zza(obj, objArr[i])) {
                    return true;
                }
            }
            return false;
        }
        return zzj.containsValue(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        Set set = this.zzi;
        if (set == null) {
            f0 f0Var = new f0(this);
            this.zzi = f0Var;
            return f0Var;
        }
        return set;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final Object get(@CheckForNull Object obj) {
        Map zzj = zzj();
        if (zzj != null) {
            return zzj.get(obj);
        }
        int zzq = zzq(obj);
        if (zzq == -1) {
            return null;
        }
        Object[] objArr = this.zzc;
        objArr.getClass();
        return objArr[zzq];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        Set set = this.zzh;
        if (set == null) {
            h0 h0Var = new h0(this);
            this.zzh = h0Var;
            return h0Var;
        }
        return set;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final Object put(Object obj, Object obj2) {
        int min;
        if (zzo()) {
            zzbc.zze(zzo(), "Arrays already allocated");
            int i = this.zzf;
            int max = Math.max(i + 1, 2);
            int highestOneBit = Integer.highestOneBit(max);
            if (max > highestOneBit && (highestOneBit = highestOneBit + highestOneBit) <= 0) {
                highestOneBit = 1073741824;
            }
            int max2 = Math.max(4, highestOneBit);
            this.zze = l0.d(max2);
            zzt(max2 - 1);
            this.zza = new int[i];
            this.zzb = new Object[i];
            this.zzc = new Object[i];
        }
        Map zzj = zzj();
        if (zzj == null) {
            int[] iArr = this.zza;
            iArr.getClass();
            Object[] objArr = this.zzb;
            objArr.getClass();
            Object[] objArr2 = this.zzc;
            objArr2.getClass();
            int i2 = this.zzg;
            int i3 = i2 + 1;
            int a2 = m0.a(obj);
            int zzp = zzp();
            int i4 = a2 & zzp;
            Object obj3 = this.zze;
            obj3.getClass();
            int c = l0.c(obj3, i4);
            if (c != 0) {
                int i5 = ~zzp;
                int i6 = a2 & i5;
                int i7 = 0;
                while (true) {
                    int i8 = c - 1;
                    int i9 = iArr[i8];
                    int i10 = i9 & i5;
                    if (i10 == i6 && zzay.zza(obj, objArr[i8])) {
                        Object obj4 = objArr2[i8];
                        objArr2[i8] = obj2;
                        return obj4;
                    }
                    int i11 = i9 & zzp;
                    i7++;
                    if (i11 != 0) {
                        c = i11;
                    } else if (i7 >= 9) {
                        LinkedHashMap linkedHashMap = new LinkedHashMap(zzp() + 1, 1.0f);
                        int zze = zze();
                        while (zze >= 0) {
                            Object[] objArr3 = this.zzb;
                            objArr3.getClass();
                            Object obj5 = objArr3[zze];
                            Object[] objArr4 = this.zzc;
                            objArr4.getClass();
                            linkedHashMap.put(obj5, objArr4[zze]);
                            zze = zzf(zze);
                        }
                        this.zze = linkedHashMap;
                        this.zza = null;
                        this.zzb = null;
                        this.zzc = null;
                        zzl();
                        return linkedHashMap.put(obj, obj2);
                    } else if (i3 > zzp) {
                        zzp = zzr(zzp, l0.a(zzp), a2, i2);
                    } else {
                        iArr[i8] = (i3 & zzp) | i10;
                    }
                }
            } else if (i3 > zzp) {
                zzp = zzr(zzp, l0.a(zzp), a2, i2);
            } else {
                Object obj6 = this.zze;
                obj6.getClass();
                l0.e(obj6, i4, i3);
            }
            int[] iArr2 = this.zza;
            iArr2.getClass();
            int length = iArr2.length;
            if (i3 > length && (min = Math.min((int) LockFreeTaskQueueCore.MAX_CAPACITY_MASK, (Math.max(1, length >>> 1) + length) | 1)) != length) {
                int[] iArr3 = this.zza;
                iArr3.getClass();
                this.zza = Arrays.copyOf(iArr3, min);
                Object[] objArr5 = this.zzb;
                objArr5.getClass();
                this.zzb = Arrays.copyOf(objArr5, min);
                Object[] objArr6 = this.zzc;
                objArr6.getClass();
                this.zzc = Arrays.copyOf(objArr6, min);
            }
            int[] iArr4 = this.zza;
            iArr4.getClass();
            iArr4[i2] = (~zzp) & a2;
            Object[] objArr7 = this.zzb;
            objArr7.getClass();
            objArr7[i2] = obj;
            Object[] objArr8 = this.zzc;
            objArr8.getClass();
            objArr8[i2] = obj2;
            this.zzg = i3;
            zzl();
            return null;
        }
        return zzj.put(obj, obj2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final Object remove(@CheckForNull Object obj) {
        Map zzj = zzj();
        if (zzj != null) {
            return zzj.remove(obj);
        }
        Object zzs = zzs(obj);
        if (zzs == zzd) {
            return null;
        }
        return zzs;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        Map zzj = zzj();
        return zzj != null ? zzj.size() : this.zzg;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        Collection collection = this.zzj;
        if (collection == null) {
            j0 j0Var = new j0(this);
            this.zzj = j0Var;
            return j0Var;
        }
        return collection;
    }

    public final int zze() {
        return isEmpty() ? -1 : 0;
    }

    public final int zzf(int i) {
        int i2 = i + 1;
        if (i2 < this.zzg) {
            return i2;
        }
        return -1;
    }

    @CheckForNull
    public final Map zzj() {
        Object obj = this.zze;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    public final void zzl() {
        this.zzf += 32;
    }

    public final void zzm(int i) {
        this.zzf = zzdz.zza(12, 1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    }

    public final void zzn(int i, int i2) {
        Object obj = this.zze;
        obj.getClass();
        int[] iArr = this.zza;
        iArr.getClass();
        Object[] objArr = this.zzb;
        objArr.getClass();
        Object[] objArr2 = this.zzc;
        objArr2.getClass();
        int size = size() - 1;
        if (i < size) {
            Object obj2 = objArr[size];
            objArr[i] = obj2;
            objArr2[i] = objArr2[size];
            objArr[size] = null;
            objArr2[size] = null;
            iArr[i] = iArr[size];
            iArr[size] = 0;
            int a2 = m0.a(obj2) & i2;
            int c = l0.c(obj, a2);
            int i3 = size + 1;
            if (c == i3) {
                l0.e(obj, a2, i + 1);
                return;
            }
            while (true) {
                int i4 = c - 1;
                int i5 = iArr[i4];
                int i6 = i5 & i2;
                if (i6 == i3) {
                    iArr[i4] = ((i + 1) & i2) | (i5 & (~i2));
                    return;
                }
                c = i6;
            }
        } else {
            objArr[i] = null;
            objArr2[i] = null;
            iArr[i] = 0;
        }
    }

    public final boolean zzo() {
        return this.zze == null;
    }

    public k0(int i) {
        zzm(12);
    }
}
