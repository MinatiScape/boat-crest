package com.google.android.recaptcha.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.f;
import kotlin.ranges.IntRange;
import kotlin.ranges.h;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzcw implements zzca {
    @NotNull
    public static final zzcw zza = new zzcw();

    private zzcw() {
    }

    @Override // com.google.android.recaptcha.internal.zzca
    public final void zza(int i, @NotNull zzbh zzbhVar, @NotNull zznl... zznlVarArr) throws zzs {
        if (zznlVarArr.length == 2) {
            Object zza2 = zzbhVar.zze().zza(zznlVarArr[0]);
            if (true != (zza2 instanceof Object)) {
                zza2 = null;
            }
            if (zza2 != null) {
                Object zza3 = zzbhVar.zze().zza(zznlVarArr[1]);
                if (true != (zza3 instanceof Object)) {
                    zza3 = null;
                }
                if (zza3 != null) {
                    zzbhVar.zze().zzf(i, zzb(zza2, zza3));
                    return;
                }
                throw new zzs(4, 5, null);
            }
            throw new zzs(4, 5, null);
        }
        throw new zzs(4, 3, null);
    }

    @NotNull
    public final Object zzb(@NotNull Object obj, @NotNull Object obj2) throws zzs {
        boolean z = obj instanceof Byte;
        if (z && (obj2 instanceof Byte)) {
            return Byte.valueOf((byte) (((Number) obj).byteValue() ^ ((Number) obj2).byteValue()));
        }
        boolean z2 = obj instanceof Short;
        if (z2 && (obj2 instanceof Short)) {
            return Short.valueOf((short) (((Number) obj).shortValue() ^ ((Number) obj2).shortValue()));
        }
        boolean z3 = obj instanceof Integer;
        if (z3 && (obj2 instanceof Integer)) {
            return Integer.valueOf(((Number) obj).intValue() ^ ((Number) obj2).intValue());
        }
        boolean z4 = obj instanceof Long;
        if (z4 && (obj2 instanceof Long)) {
            return Long.valueOf(((Number) obj2).longValue() ^ ((Number) obj).longValue());
        }
        int i = 0;
        if (obj instanceof String) {
            if (obj2 instanceof Byte) {
                byte[] bytes = ((String) obj).getBytes(Charsets.UTF_8);
                int length = bytes.length;
                ArrayList arrayList = new ArrayList(length);
                while (i < length) {
                    arrayList.add(Byte.valueOf((byte) (bytes[i] ^ ((Number) obj2).byteValue())));
                    i++;
                }
                return (Serializable) CollectionsKt___CollectionsKt.toByteArray(arrayList);
            } else if (obj2 instanceof Integer) {
                char[] charArray = ((String) obj).toCharArray();
                int length2 = charArray.length;
                ArrayList arrayList2 = new ArrayList(length2);
                while (i < length2) {
                    arrayList2.add(Integer.valueOf(charArray[i] ^ ((Number) obj2).intValue()));
                    i++;
                }
                return (Serializable) CollectionsKt___CollectionsKt.toIntArray(arrayList2);
            }
        }
        if (z && (obj2 instanceof byte[])) {
            byte[] bArr = (byte[]) obj2;
            ArrayList arrayList3 = new ArrayList(bArr.length);
            for (byte b : bArr) {
                arrayList3.add(Byte.valueOf((byte) (b ^ ((Number) obj).byteValue())));
            }
            return (Serializable) arrayList3.toArray(new Byte[0]);
        } else if (z2 && (obj2 instanceof short[])) {
            short[] sArr = (short[]) obj2;
            ArrayList arrayList4 = new ArrayList(sArr.length);
            for (short s : sArr) {
                arrayList4.add(Short.valueOf((short) (s ^ ((Number) obj).shortValue())));
            }
            return (Serializable) arrayList4.toArray(new Short[0]);
        } else if (z3 && (obj2 instanceof int[])) {
            int[] iArr = (int[]) obj2;
            ArrayList arrayList5 = new ArrayList(iArr.length);
            for (int i2 : iArr) {
                arrayList5.add(Integer.valueOf(i2 ^ ((Number) obj).intValue()));
            }
            return (Serializable) arrayList5.toArray(new Integer[0]);
        } else if (z4 && (obj2 instanceof long[])) {
            long[] jArr = (long[]) obj2;
            ArrayList arrayList6 = new ArrayList(jArr.length);
            for (long j : jArr) {
                arrayList6.add(Long.valueOf(j ^ ((Number) obj).longValue()));
            }
            return (Serializable) arrayList6.toArray(new Long[0]);
        } else {
            boolean z5 = obj instanceof byte[];
            if (z5 && (obj2 instanceof Byte)) {
                byte[] bArr2 = (byte[]) obj;
                ArrayList arrayList7 = new ArrayList(bArr2.length);
                for (byte b2 : bArr2) {
                    arrayList7.add(Byte.valueOf((byte) (b2 ^ ((Number) obj2).byteValue())));
                }
                return (Serializable) arrayList7.toArray(new Byte[0]);
            }
            boolean z6 = obj instanceof short[];
            if (z6 && (obj2 instanceof Short)) {
                short[] sArr2 = (short[]) obj;
                ArrayList arrayList8 = new ArrayList(sArr2.length);
                for (short s2 : sArr2) {
                    arrayList8.add(Short.valueOf((short) (s2 ^ ((Number) obj2).shortValue())));
                }
                return (Serializable) arrayList8.toArray(new Short[0]);
            }
            boolean z7 = obj instanceof int[];
            if (z7 && (obj2 instanceof Integer)) {
                int[] iArr2 = (int[]) obj;
                ArrayList arrayList9 = new ArrayList(iArr2.length);
                for (int i3 : iArr2) {
                    arrayList9.add(Integer.valueOf(i3 ^ ((Number) obj2).intValue()));
                }
                return (Serializable) arrayList9.toArray(new Integer[0]);
            }
            boolean z8 = obj instanceof long[];
            if (z8 && (obj2 instanceof Long)) {
                long[] jArr2 = (long[]) obj;
                ArrayList arrayList10 = new ArrayList(jArr2.length);
                for (long j2 : jArr2) {
                    arrayList10.add(Long.valueOf(j2 ^ ((Number) obj2).longValue()));
                }
                return (Serializable) arrayList10.toArray(new Long[0]);
            } else if (z5 && (obj2 instanceof byte[])) {
                byte[] bArr3 = (byte[]) obj;
                int length3 = bArr3.length;
                byte[] bArr4 = (byte[]) obj2;
                zzbz.zzb(this, length3, bArr4.length);
                IntRange until = h.until(0, length3);
                ArrayList arrayList11 = new ArrayList(f.collectionSizeOrDefault(until, 10));
                Iterator<Integer> it = until.iterator();
                while (it.hasNext()) {
                    int nextInt = ((IntIterator) it).nextInt();
                    arrayList11.add(Byte.valueOf((byte) (bArr4[nextInt] ^ bArr3[nextInt])));
                }
                return (Serializable) arrayList11.toArray(new Byte[0]);
            } else if (z6 && (obj2 instanceof short[])) {
                short[] sArr3 = (short[]) obj;
                int length4 = sArr3.length;
                short[] sArr4 = (short[]) obj2;
                zzbz.zzb(this, length4, sArr4.length);
                IntRange until2 = h.until(0, length4);
                ArrayList arrayList12 = new ArrayList(f.collectionSizeOrDefault(until2, 10));
                Iterator<Integer> it2 = until2.iterator();
                while (it2.hasNext()) {
                    int nextInt2 = ((IntIterator) it2).nextInt();
                    arrayList12.add(Short.valueOf((short) (sArr4[nextInt2] ^ sArr3[nextInt2])));
                }
                return (Serializable) arrayList12.toArray(new Short[0]);
            } else if (z7 && (obj2 instanceof int[])) {
                int[] iArr3 = (int[]) obj;
                int length5 = iArr3.length;
                int[] iArr4 = (int[]) obj2;
                zzbz.zzb(this, length5, iArr4.length);
                IntRange until3 = h.until(0, length5);
                ArrayList arrayList13 = new ArrayList(f.collectionSizeOrDefault(until3, 10));
                Iterator<Integer> it3 = until3.iterator();
                while (it3.hasNext()) {
                    int nextInt3 = ((IntIterator) it3).nextInt();
                    arrayList13.add(Integer.valueOf(iArr4[nextInt3] ^ iArr3[nextInt3]));
                }
                return (Serializable) arrayList13.toArray(new Integer[0]);
            } else if (z8 && (obj2 instanceof long[])) {
                long[] jArr3 = (long[]) obj;
                int length6 = jArr3.length;
                long[] jArr4 = (long[]) obj2;
                zzbz.zzb(this, length6, jArr4.length);
                IntRange until4 = h.until(0, length6);
                ArrayList arrayList14 = new ArrayList(f.collectionSizeOrDefault(until4, 10));
                Iterator<Integer> it4 = until4.iterator();
                while (it4.hasNext()) {
                    int nextInt4 = ((IntIterator) it4).nextInt();
                    arrayList14.add(Long.valueOf(jArr3[nextInt4] ^ jArr4[nextInt4]));
                }
                return (Serializable) arrayList14.toArray(new Long[0]);
            } else {
                throw new zzs(4, 5, null);
            }
        }
    }
}
