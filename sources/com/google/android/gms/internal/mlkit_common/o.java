package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;
import javax.annotation.CheckForNull;
/* loaded from: classes8.dex */
public final class o extends zzau {
    public static final zzau zza = new o(null, new Object[0], 0);
    public final transient Object[] zzb;
    @CheckForNull
    private final transient Object zzc;
    private final transient int zzd;

    private o(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zzc = obj;
        this.zzb = objArr;
        this.zzd = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r5v12, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r5v2, types: [int[]] */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.lang.Object[]] */
    public static o zzg(int i, Object[] objArr, zzat zzatVar) {
        short[] sArr;
        char c;
        char c2;
        byte[] bArr;
        byte[] bArr2;
        int i2 = i;
        Object[] objArr2 = objArr;
        if (i2 == 0) {
            return (o) zza;
        }
        Object obj = null;
        int i3 = 1;
        if (i2 == 1) {
            Object obj2 = objArr2[0];
            obj2.getClass();
            Object obj3 = objArr2[1];
            obj3.getClass();
            d.a(obj2, obj3);
            return new o(null, objArr2, 1);
        }
        zzaf.zzb(i2, objArr2.length >> 1, FirebaseAnalytics.Param.INDEX);
        int max = Math.max(i2, 2);
        int i4 = 1073741824;
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1);
            i4 = highestOneBit + highestOneBit;
            while (i4 * 0.7d < max) {
                i4 += i4;
            }
        } else if (max >= 1073741824) {
            throw new IllegalArgumentException("collection too large");
        }
        if (i2 == 1) {
            Object obj4 = objArr2[0];
            obj4.getClass();
            Object obj5 = objArr2[1];
            obj5.getClass();
            d.a(obj4, obj5);
            c = 1;
            c2 = 2;
        } else {
            int i5 = i4 - 1;
            char c3 = 65535;
            if (i4 <= 128) {
                byte[] bArr3 = new byte[i4];
                Arrays.fill(bArr3, (byte) -1);
                int i6 = 0;
                int i7 = 0;
                while (i6 < i2) {
                    int i8 = i7 + i7;
                    int i9 = i6 + i6;
                    Object obj6 = objArr2[i9];
                    obj6.getClass();
                    Object obj7 = objArr2[i9 ^ i3];
                    obj7.getClass();
                    d.a(obj6, obj7);
                    int a2 = e.a(obj6.hashCode());
                    while (true) {
                        int i10 = a2 & i5;
                        int i11 = bArr3[i10] & 255;
                        if (i11 == 255) {
                            bArr3[i10] = (byte) i8;
                            if (i7 < i6) {
                                objArr2[i8] = obj6;
                                objArr2[i8 ^ 1] = obj7;
                            }
                            i7++;
                        } else if (obj6.equals(objArr2[i11])) {
                            int i12 = i11 ^ 1;
                            Object obj8 = objArr2[i12];
                            obj8.getClass();
                            i iVar = new i(obj6, obj7, obj8);
                            objArr2[i12] = obj7;
                            obj = iVar;
                            break;
                        } else {
                            a2 = i10 + 1;
                        }
                    }
                    i6++;
                    i3 = 1;
                }
                if (i7 == i2) {
                    bArr = bArr3;
                } else {
                    bArr2 = new Object[]{bArr3, Integer.valueOf(i7), obj};
                    c2 = 2;
                    c = 1;
                    obj = bArr2;
                }
            } else if (i4 <= 32768) {
                sArr = new short[i4];
                Arrays.fill(sArr, (short) -1);
                int i13 = 0;
                for (int i14 = 0; i14 < i2; i14++) {
                    int i15 = i13 + i13;
                    int i16 = i14 + i14;
                    Object obj9 = objArr2[i16];
                    obj9.getClass();
                    Object obj10 = objArr2[i16 ^ 1];
                    obj10.getClass();
                    d.a(obj9, obj10);
                    int a3 = e.a(obj9.hashCode());
                    while (true) {
                        int i17 = a3 & i5;
                        char c4 = (char) sArr[i17];
                        if (c4 == 65535) {
                            sArr[i17] = (short) i15;
                            if (i13 < i14) {
                                objArr2[i15] = obj9;
                                objArr2[i15 ^ 1] = obj10;
                            }
                            i13++;
                        } else if (obj9.equals(objArr2[c4])) {
                            int i18 = c4 ^ 1;
                            Object obj11 = objArr2[i18];
                            obj11.getClass();
                            i iVar2 = new i(obj9, obj10, obj11);
                            objArr2[i18] = obj10;
                            obj = iVar2;
                            break;
                        } else {
                            a3 = i17 + 1;
                        }
                    }
                }
                if (i13 != i2) {
                    c2 = 2;
                    obj = new Object[]{sArr, Integer.valueOf(i13), obj};
                    c = 1;
                }
                bArr = sArr;
            } else {
                int i19 = 1;
                sArr = new int[i4];
                Arrays.fill((int[]) sArr, -1);
                int i20 = 0;
                int i21 = 0;
                while (i20 < i2) {
                    int i22 = i21 + i21;
                    int i23 = i20 + i20;
                    Object obj12 = objArr2[i23];
                    obj12.getClass();
                    Object obj13 = objArr2[i23 ^ i19];
                    obj13.getClass();
                    d.a(obj12, obj13);
                    int a4 = e.a(obj12.hashCode());
                    while (true) {
                        int i24 = a4 & i5;
                        ?? r15 = sArr[i24];
                        if (r15 == c3) {
                            sArr[i24] = i22;
                            if (i21 < i20) {
                                objArr2[i22] = obj12;
                                objArr2[i22 ^ 1] = obj13;
                            }
                            i21++;
                        } else if (obj12.equals(objArr2[r15])) {
                            int i25 = r15 ^ 1;
                            Object obj14 = objArr2[i25];
                            obj14.getClass();
                            i iVar3 = new i(obj12, obj13, obj14);
                            objArr2[i25] = obj13;
                            obj = iVar3;
                            break;
                        } else {
                            a4 = i24 + 1;
                            c3 = 65535;
                        }
                    }
                    i20++;
                    i19 = 1;
                    c3 = 65535;
                }
                if (i21 != i2) {
                    c = 1;
                    c2 = 2;
                    obj = new Object[]{sArr, Integer.valueOf(i21), obj};
                }
                bArr = sArr;
            }
            c2 = 2;
            bArr2 = bArr;
            c = 1;
            obj = bArr2;
        }
        if (obj instanceof Object[]) {
            Object[] objArr3 = (Object[]) obj;
            i iVar4 = (i) objArr3[c2];
            if (zzatVar != null) {
                zzatVar.c = iVar4;
                Object obj15 = objArr3[0];
                int intValue = ((Integer) objArr3[c]).intValue();
                objArr2 = Arrays.copyOf(objArr2, intValue + intValue);
                obj = obj15;
                i2 = intValue;
            } else {
                throw iVar4.a();
            }
        }
        return new o(obj, objArr2, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x009e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x009f A[RETURN] */
    @Override // com.google.android.gms.internal.mlkit_common.zzau, java.util.Map
    @javax.annotation.CheckForNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object get(@javax.annotation.CheckForNull java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.zzc
            java.lang.Object[] r1 = r9.zzb
            int r2 = r9.zzd
            r3 = 0
            if (r10 != 0) goto Lc
        L9:
            r10 = r3
            goto L9c
        Lc:
            r4 = 1
            if (r2 != r4) goto L22
            r0 = 0
            r0 = r1[r0]
            r0.getClass()
            boolean r10 = r0.equals(r10)
            if (r10 == 0) goto L9
            r10 = r1[r4]
            r10.getClass()
            goto L9c
        L22:
            if (r0 != 0) goto L25
            goto L9
        L25:
            boolean r2 = r0 instanceof byte[]
            r5 = -1
            if (r2 == 0) goto L51
            r2 = r0
            byte[] r2 = (byte[]) r2
            int r0 = r2.length
            int r6 = r0 + (-1)
            int r0 = r10.hashCode()
            int r0 = com.google.android.gms.internal.mlkit_common.e.a(r0)
        L38:
            r0 = r0 & r6
            r5 = r2[r0]
            r7 = 255(0xff, float:3.57E-43)
            r5 = r5 & r7
            if (r5 != r7) goto L41
            goto L9
        L41:
            r7 = r1[r5]
            boolean r7 = r10.equals(r7)
            if (r7 == 0) goto L4e
            r10 = r5 ^ 1
            r10 = r1[r10]
            goto L9c
        L4e:
            int r0 = r0 + 1
            goto L38
        L51:
            boolean r2 = r0 instanceof short[]
            if (r2 == 0) goto L7d
            r2 = r0
            short[] r2 = (short[]) r2
            int r0 = r2.length
            int r6 = r0 + (-1)
            int r0 = r10.hashCode()
            int r0 = com.google.android.gms.internal.mlkit_common.e.a(r0)
        L63:
            r0 = r0 & r6
            short r5 = r2[r0]
            char r5 = (char) r5
            r7 = 65535(0xffff, float:9.1834E-41)
            if (r5 != r7) goto L6d
            goto L9
        L6d:
            r7 = r1[r5]
            boolean r7 = r10.equals(r7)
            if (r7 == 0) goto L7a
            r10 = r5 ^ 1
            r10 = r1[r10]
            goto L9c
        L7a:
            int r0 = r0 + 1
            goto L63
        L7d:
            int[] r0 = (int[]) r0
            int r2 = r0.length
            int r2 = r2 + r5
            int r6 = r10.hashCode()
            int r6 = com.google.android.gms.internal.mlkit_common.e.a(r6)
        L89:
            r6 = r6 & r2
            r7 = r0[r6]
            if (r7 != r5) goto L90
            goto L9
        L90:
            r8 = r1[r7]
            boolean r8 = r10.equals(r8)
            if (r8 == 0) goto La0
            r10 = r7 ^ 1
            r10 = r1[r10]
        L9c:
            if (r10 != 0) goto L9f
            return r3
        L9f:
            return r10
        La0:
            int r6 = r6 + 1
            goto L89
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_common.o.get(java.lang.Object):java.lang.Object");
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzau
    public final zzan zza() {
        return new n(this.zzb, 1, this.zzd);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzau
    public final zzav zzd() {
        return new l(this, this.zzb, 0, this.zzd);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzau
    public final zzav zze() {
        return new m(this, new n(this.zzb, 0, this.zzd));
    }
}
