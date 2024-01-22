package com.google.android.gms.internal.clearcut;
/* loaded from: classes7.dex */
public final class zzfw implements Cloneable {
    public int[] h;
    public a3[] i;
    public int j;

    static {
        new a3();
    }

    public zzfw() {
        this(10);
    }

    public zzfw(int i) {
        int i2 = i << 2;
        int i3 = 4;
        while (true) {
            if (i3 >= 32) {
                break;
            }
            int i4 = (1 << i3) - 12;
            if (i2 <= i4) {
                i2 = i4;
                break;
            }
            i3++;
        }
        int i5 = i2 / 4;
        this.h = new int[i5];
        this.i = new a3[i5];
        this.j = 0;
    }

    public final int a() {
        return this.j;
    }

    public final a3 b(int i) {
        return this.i[i];
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        int i = this.j;
        zzfw zzfwVar = new zzfw(i);
        System.arraycopy(this.h, 0, zzfwVar.h, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            a3[] a3VarArr = this.i;
            if (a3VarArr[i2] != null) {
                zzfwVar.i[i2] = (a3) a3VarArr[i2].clone();
            }
        }
        zzfwVar.j = i;
        return zzfwVar;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzfw) {
            zzfw zzfwVar = (zzfw) obj;
            int i = this.j;
            if (i != zzfwVar.j) {
                return false;
            }
            int[] iArr = this.h;
            int[] iArr2 = zzfwVar.h;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    z = true;
                    break;
                } else if (iArr[i2] != iArr2[i2]) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                a3[] a3VarArr = this.i;
                a3[] a3VarArr2 = zzfwVar.i;
                int i3 = this.j;
                int i4 = 0;
                while (true) {
                    if (i4 >= i3) {
                        z2 = true;
                        break;
                    } else if (!a3VarArr[i4].equals(a3VarArr2[i4])) {
                        z2 = false;
                        break;
                    } else {
                        i4++;
                    }
                }
                if (z2) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int i = 17;
        for (int i2 = 0; i2 < this.j; i2++) {
            i = (((i * 31) + this.h[i2]) * 31) + this.i[i2].hashCode();
        }
        return i;
    }

    public final boolean isEmpty() {
        return this.j == 0;
    }
}
