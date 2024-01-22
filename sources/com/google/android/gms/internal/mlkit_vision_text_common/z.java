package com.google.android.gms.internal.mlkit_vision_text_common;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
/* loaded from: classes6.dex */
public final class z extends AbstractMap implements Serializable {
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

    public z() {
        zzo(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object[] zzA() {
        Object[] objArr = this.zzb;
        objArr.getClass();
        return objArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object[] zzB() {
        Object[] objArr = this.zzc;
        objArr.getClass();
        return objArr;
    }

    public static /* synthetic */ int zzb(z zVar) {
        int i = zVar.zzg;
        zVar.zzg = i - 1;
        return i;
    }

    public static /* synthetic */ Object zzg(z zVar, int i) {
        return zVar.zzA()[i];
    }

    public static /* synthetic */ Object zzj(z zVar, int i) {
        return zVar.zzB()[i];
    }

    public static /* synthetic */ Object zzk(z zVar) {
        Object obj = zVar.zze;
        obj.getClass();
        return obj;
    }

    public static /* synthetic */ void zzm(z zVar, int i, Object obj) {
        zVar.zzB()[i] = obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zzu() {
        return (1 << (this.zzf & 31)) - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zzv(@CheckForNull Object obj) {
        if (zzq()) {
            return -1;
        }
        int a2 = b0.a(obj);
        int zzu = zzu();
        Object obj2 = this.zze;
        obj2.getClass();
        int c = a0.c(obj2, a2 & zzu);
        if (c != 0) {
            int i = ~zzu;
            int i2 = a2 & i;
            do {
                int i3 = c - 1;
                int i4 = zzz()[i3];
                if ((i4 & i) == i2 && zzw.zza(obj, zzA()[i3])) {
                    return i3;
                }
                c = i4 & zzu;
            } while (c != 0);
            return -1;
        }
        return -1;
    }

    private final int zzw(int i, int i2, int i3, int i4) {
        Object d = a0.d(i2);
        int i5 = i2 - 1;
        if (i4 != 0) {
            a0.e(d, i3 & i5, i4 + 1);
        }
        Object obj = this.zze;
        obj.getClass();
        int[] zzz = zzz();
        for (int i6 = 0; i6 <= i; i6++) {
            int c = a0.c(obj, i6);
            while (c != 0) {
                int i7 = c - 1;
                int i8 = zzz[i7];
                int i9 = ((~i) & i8) | i6;
                int i10 = i9 & i5;
                int c2 = a0.c(d, i10);
                a0.e(d, i10, c);
                zzz[i7] = ((~i5) & i9) | (c2 & i5);
                c = i8 & i;
            }
        }
        this.zze = d;
        zzy(i5);
        return i5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object zzx(@CheckForNull Object obj) {
        if (zzq()) {
            return zzd;
        }
        int zzu = zzu();
        Object obj2 = this.zze;
        obj2.getClass();
        int b = a0.b(obj, null, zzu, obj2, zzz(), zzA(), null);
        if (b == -1) {
            return zzd;
        }
        Object obj3 = zzB()[b];
        zzp(b, zzu);
        this.zzg--;
        zzn();
        return obj3;
    }

    private final void zzy(int i) {
        this.zzf = ((32 - Integer.numberOfLeadingZeros(i)) & 31) | (this.zzf & (-32));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int[] zzz() {
        int[] iArr = this.zza;
        iArr.getClass();
        return iArr;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        if (zzq()) {
            return;
        }
        zzn();
        Map zzl = zzl();
        if (zzl != null) {
            this.zzf = zzcs.zza(size(), 3, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
            zzl.clear();
            this.zze = null;
            this.zzg = 0;
            return;
        }
        Arrays.fill(zzA(), 0, this.zzg, (Object) null);
        Arrays.fill(zzB(), 0, this.zzg, (Object) null);
        Object obj = this.zze;
        obj.getClass();
        if (obj instanceof byte[]) {
            Arrays.fill((byte[]) obj, (byte) 0);
        } else if (obj instanceof short[]) {
            Arrays.fill((short[]) obj, (short) 0);
        } else {
            Arrays.fill((int[]) obj, 0);
        }
        Arrays.fill(zzz(), 0, this.zzg, 0);
        this.zzg = 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(@CheckForNull Object obj) {
        Map zzl = zzl();
        if (zzl != null) {
            return zzl.containsKey(obj);
        }
        return zzv(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(@CheckForNull Object obj) {
        Map zzl = zzl();
        if (zzl == null) {
            for (int i = 0; i < this.zzg; i++) {
                if (zzw.zza(obj, zzB()[i])) {
                    return true;
                }
            }
            return false;
        }
        return zzl.containsValue(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        Set set = this.zzi;
        if (set == null) {
            u uVar = new u(this);
            this.zzi = uVar;
            return uVar;
        }
        return set;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final Object get(@CheckForNull Object obj) {
        Map zzl = zzl();
        if (zzl != null) {
            return zzl.get(obj);
        }
        int zzv = zzv(obj);
        if (zzv == -1) {
            return null;
        }
        return zzB()[zzv];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        Set set = this.zzh;
        if (set == null) {
            w wVar = new w(this);
            this.zzh = wVar;
            return wVar;
        }
        return set;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final Object put(Object obj, Object obj2) {
        int min;
        if (zzq()) {
            zzaa.zzd(zzq(), "Arrays already allocated");
            int i = this.zzf;
            int max = Math.max(i + 1, 2);
            int highestOneBit = Integer.highestOneBit(max);
            if (max > highestOneBit && (highestOneBit = highestOneBit + highestOneBit) <= 0) {
                highestOneBit = 1073741824;
            }
            int max2 = Math.max(4, highestOneBit);
            this.zze = a0.d(max2);
            zzy(max2 - 1);
            this.zza = new int[i];
            this.zzb = new Object[i];
            this.zzc = new Object[i];
        }
        Map zzl = zzl();
        if (zzl != null) {
            return zzl.put(obj, obj2);
        }
        int[] zzz = zzz();
        Object[] zzA = zzA();
        Object[] zzB = zzB();
        int i2 = this.zzg;
        int i3 = i2 + 1;
        int a2 = b0.a(obj);
        int zzu = zzu();
        int i4 = a2 & zzu;
        Object obj3 = this.zze;
        obj3.getClass();
        int c = a0.c(obj3, i4);
        if (c != 0) {
            int i5 = ~zzu;
            int i6 = a2 & i5;
            int i7 = 0;
            while (true) {
                int i8 = c - 1;
                int i9 = zzz[i8];
                int i10 = i9 & i5;
                if (i10 == i6 && zzw.zza(obj, zzA[i8])) {
                    Object obj4 = zzB[i8];
                    zzB[i8] = obj2;
                    return obj4;
                }
                int i11 = i9 & zzu;
                i7++;
                if (i11 != 0) {
                    c = i11;
                } else if (i7 >= 9) {
                    LinkedHashMap linkedHashMap = new LinkedHashMap(zzu() + 1, 1.0f);
                    int zze = zze();
                    while (zze >= 0) {
                        linkedHashMap.put(zzA()[zze], zzB()[zze]);
                        zze = zzf(zze);
                    }
                    this.zze = linkedHashMap;
                    this.zza = null;
                    this.zzb = null;
                    this.zzc = null;
                    zzn();
                    return linkedHashMap.put(obj, obj2);
                } else if (i3 > zzu) {
                    zzu = zzw(zzu, a0.a(zzu), a2, i2);
                } else {
                    zzz[i8] = (i3 & zzu) | i10;
                }
            }
        } else if (i3 > zzu) {
            zzu = zzw(zzu, a0.a(zzu), a2, i2);
        } else {
            Object obj5 = this.zze;
            obj5.getClass();
            a0.e(obj5, i4, i3);
        }
        int length = zzz().length;
        if (i3 > length && (min = Math.min((int) LockFreeTaskQueueCore.MAX_CAPACITY_MASK, (Math.max(1, length >>> 1) + length) | 1)) != length) {
            this.zza = Arrays.copyOf(zzz(), min);
            this.zzb = Arrays.copyOf(zzA(), min);
            this.zzc = Arrays.copyOf(zzB(), min);
        }
        zzz()[i2] = (~zzu) & a2;
        zzA()[i2] = obj;
        zzB()[i2] = obj2;
        this.zzg = i3;
        zzn();
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final Object remove(@CheckForNull Object obj) {
        Map zzl = zzl();
        if (zzl != null) {
            return zzl.remove(obj);
        }
        Object zzx = zzx(obj);
        if (zzx == zzd) {
            return null;
        }
        return zzx;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        Map zzl = zzl();
        return zzl != null ? zzl.size() : this.zzg;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        Collection collection = this.zzj;
        if (collection == null) {
            y yVar = new y(this);
            this.zzj = yVar;
            return yVar;
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
    public final Map zzl() {
        Object obj = this.zze;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    public final void zzn() {
        this.zzf += 32;
    }

    public final void zzo(int i) {
        this.zzf = zzcs.zza(12, 1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    }

    public final void zzp(int i, int i2) {
        Object obj = this.zze;
        obj.getClass();
        int[] zzz = zzz();
        Object[] zzA = zzA();
        Object[] zzB = zzB();
        int size = size() - 1;
        if (i < size) {
            Object obj2 = zzA[size];
            zzA[i] = obj2;
            zzB[i] = zzB[size];
            zzA[size] = null;
            zzB[size] = null;
            zzz[i] = zzz[size];
            zzz[size] = 0;
            int a2 = b0.a(obj2) & i2;
            int c = a0.c(obj, a2);
            int i3 = size + 1;
            if (c == i3) {
                a0.e(obj, a2, i + 1);
                return;
            }
            while (true) {
                int i4 = c - 1;
                int i5 = zzz[i4];
                int i6 = i5 & i2;
                if (i6 == i3) {
                    zzz[i4] = ((i + 1) & i2) | (i5 & (~i2));
                    return;
                }
                c = i6;
            }
        } else {
            zzA[i] = null;
            zzB[i] = null;
            zzz[i] = 0;
        }
    }

    public final boolean zzq() {
        return this.zze == null;
    }

    public z(int i) {
        zzo(12);
    }
}
