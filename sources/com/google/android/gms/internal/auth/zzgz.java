package com.google.android.gms.internal.auth;

import java.util.Arrays;
/* loaded from: classes6.dex */
public final class zzgz {
    public static final zzgz e = new zzgz(0, new int[0], new Object[0], false);

    /* renamed from: a  reason: collision with root package name */
    public int f8563a;
    public int[] b;
    public Object[] c;
    public boolean d;

    public zzgz() {
        this(0, new int[8], new Object[8], true);
    }

    public zzgz(int i, int[] iArr, Object[] objArr, boolean z) {
        this.f8563a = i;
        this.b = iArr;
        this.c = objArr;
        this.d = z;
    }

    public static zzgz a(zzgz zzgzVar, zzgz zzgzVar2) {
        int i = zzgzVar.f8563a + zzgzVar2.f8563a;
        int[] copyOf = Arrays.copyOf(zzgzVar.b, i);
        System.arraycopy(zzgzVar2.b, 0, copyOf, zzgzVar.f8563a, zzgzVar2.f8563a);
        Object[] copyOf2 = Arrays.copyOf(zzgzVar.c, i);
        System.arraycopy(zzgzVar2.c, 0, copyOf2, zzgzVar.f8563a, zzgzVar2.f8563a);
        return new zzgz(i, copyOf, copyOf2, true);
    }

    public static zzgz b() {
        return new zzgz(0, new int[8], new Object[8], true);
    }

    public static zzgz zza() {
        return e;
    }

    public final void c(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.f8563a; i2++) {
            q1.b(sb, i, String.valueOf(this.b[i2] >>> 3), this.c[i2]);
        }
    }

    public final void d(int i, Object obj) {
        if (this.d) {
            int i2 = this.f8563a;
            int[] iArr = this.b;
            if (i2 == iArr.length) {
                int i3 = i2 + (i2 < 4 ? 8 : i2 >> 1);
                this.b = Arrays.copyOf(iArr, i3);
                this.c = Arrays.copyOf(this.c, i3);
            }
            int[] iArr2 = this.b;
            int i4 = this.f8563a;
            iArr2[i4] = i;
            this.c[i4] = obj;
            this.f8563a = i4 + 1;
            return;
        }
        throw new UnsupportedOperationException();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof zzgz)) {
            zzgz zzgzVar = (zzgz) obj;
            int i = this.f8563a;
            if (i == zzgzVar.f8563a) {
                int[] iArr = this.b;
                int[] iArr2 = zzgzVar.b;
                int i2 = 0;
                while (true) {
                    if (i2 >= i) {
                        Object[] objArr = this.c;
                        Object[] objArr2 = zzgzVar.c;
                        int i3 = this.f8563a;
                        for (int i4 = 0; i4 < i3; i4++) {
                            if (objArr[i4].equals(objArr2[i4])) {
                            }
                        }
                        return true;
                    } else if (iArr[i2] != iArr2[i2]) {
                        break;
                    } else {
                        i2++;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.f8563a;
        int i2 = (i + 527) * 31;
        int[] iArr = this.b;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.c;
        int i7 = this.f8563a;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    public final void zzd() {
        this.d = false;
    }
}
