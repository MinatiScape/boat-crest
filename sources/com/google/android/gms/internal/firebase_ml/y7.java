package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
/* loaded from: classes7.dex */
public final class y7 extends zzvv {
    public static final int[] zzcox = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903, Integer.MAX_VALUE};
    private final int zzcoy;
    private final zzvv zzcoz;
    private final zzvv zzcpa;
    private final int zzcpb;
    private final int zzcpc;

    private y7(zzvv zzvvVar, zzvv zzvvVar2) {
        this.zzcoz = zzvvVar;
        this.zzcpa = zzvvVar2;
        int size = zzvvVar.size();
        this.zzcpb = size;
        this.zzcoy = size + zzvvVar2.size();
        this.zzcpc = Math.max(zzvvVar.zztr(), zzvvVar2.zztr()) + 1;
    }

    public static zzvv zza(zzvv zzvvVar, zzvv zzvvVar2) {
        if (zzvvVar2.size() == 0) {
            return zzvvVar;
        }
        if (zzvvVar.size() == 0) {
            return zzvvVar2;
        }
        int size = zzvvVar.size() + zzvvVar2.size();
        if (size < 128) {
            return zzb(zzvvVar, zzvvVar2);
        }
        if (zzvvVar instanceof y7) {
            y7 y7Var = (y7) zzvvVar;
            if (y7Var.zzcpa.size() + zzvvVar2.size() < 128) {
                return new y7(y7Var.zzcoz, zzb(y7Var.zzcpa, zzvvVar2));
            } else if (y7Var.zzcoz.zztr() > y7Var.zzcpa.zztr() && y7Var.zztr() > zzvvVar2.zztr()) {
                return new y7(y7Var.zzcoz, new y7(y7Var.zzcpa, zzvvVar2));
            }
        }
        if (size >= zzdz(Math.max(zzvvVar.zztr(), zzvvVar2.zztr()) + 1)) {
            return new y7(zzvvVar, zzvvVar2);
        }
        return a8.a(new a8(null), zzvvVar, zzvvVar2);
    }

    private static zzvv zzb(zzvv zzvvVar, zzvv zzvvVar2) {
        int size = zzvvVar.size();
        int size2 = zzvvVar2.size();
        byte[] bArr = new byte[size + size2];
        zzvvVar.zza(bArr, 0, 0, size);
        zzvvVar2.zza(bArr, 0, size, size2);
        return new h6(bArr);
    }

    public static int zzdz(int i) {
        int[] iArr = zzcox;
        if (i >= iArr.length) {
            return Integer.MAX_VALUE;
        }
        return iArr[i];
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public final boolean equals(Object obj) {
        boolean zza;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzvv)) {
            return false;
        }
        zzvv zzvvVar = (zzvv) obj;
        if (this.zzcoy != zzvvVar.size()) {
            return false;
        }
        if (this.zzcoy == 0) {
            return true;
        }
        int zztt = zztt();
        int zztt2 = zzvvVar.zztt();
        if (zztt != 0 && zztt2 != 0 && zztt != zztt2) {
            return false;
        }
        d8 d8Var = new d8(this, null);
        e6 next = d8Var.next();
        d8 d8Var2 = new d8(zzvvVar, null);
        e6 next2 = d8Var2.next();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int size = next.size() - i;
            int size2 = next2.size() - i2;
            int min = Math.min(size, size2);
            if (i == 0) {
                zza = next.zza(next2, i2, min);
            } else {
                zza = next2.zza(next, i, min);
            }
            if (!zza) {
                return false;
            }
            i3 += min;
            int i4 = this.zzcoy;
            if (i3 >= i4) {
                if (i3 == i4) {
                    return true;
                }
                throw new IllegalStateException();
            }
            if (min == size) {
                next = d8Var.next();
                i = 0;
            } else {
                i += min;
            }
            if (min == size2) {
                next2 = d8Var2.next();
                i2 = 0;
            } else {
                i2 += min;
            }
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public final int size() {
        return this.zzcoy;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public final int zzc(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.zzcpb;
        if (i4 <= i5) {
            return this.zzcoz.zzc(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.zzcpa.zzc(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.zzcpa.zzc(this.zzcoz.zzc(i, i2, i6), 0, i3 - i6);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public final byte zzcw(int i) {
        zzvv.zzg(i, this.zzcoy);
        return zzcx(i);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public final byte zzcx(int i) {
        int i2 = this.zzcpb;
        if (i < i2) {
            return this.zzcoz.zzcx(i);
        }
        return this.zzcpa.zzcx(i - i2);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public final zzvv zzf(int i, int i2) {
        int zzd = zzvv.zzd(i, i2, this.zzcoy);
        if (zzd == 0) {
            return zzvv.zzchp;
        }
        if (zzd == this.zzcoy) {
            return this;
        }
        int i3 = this.zzcpb;
        if (i2 <= i3) {
            return this.zzcoz.zzf(i, i2);
        }
        if (i >= i3) {
            return this.zzcpa.zzf(i - i3, i2 - i3);
        }
        zzvv zzvvVar = this.zzcoz;
        return new y7(zzvvVar.zzf(i, zzvvVar.size()), this.zzcpa.zzf(0, i2 - this.zzcpb));
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public final zzwa zzto() {
        return new b8(this);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public final boolean zztq() {
        int zzb = this.zzcoz.zzb(0, 0, this.zzcpb);
        zzvv zzvvVar = this.zzcpa;
        return zzvvVar.zzb(zzb, 0, zzvvVar.size()) == 0;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public final int zztr() {
        return this.zzcpc;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public final boolean zzts() {
        return this.zzcoy >= zzdz(this.zzcpc);
    }

    public /* synthetic */ y7(zzvv zzvvVar, zzvv zzvvVar2, b8 b8Var) {
        this(zzvvVar, zzvvVar2);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public final void zzb(byte[] bArr, int i, int i2, int i3) {
        int i4 = i + i3;
        int i5 = this.zzcpb;
        if (i4 <= i5) {
            this.zzcoz.zzb(bArr, i, i2, i3);
        } else if (i >= i5) {
            this.zzcpa.zzb(bArr, i - i5, i2, i3);
        } else {
            int i6 = i5 - i;
            this.zzcoz.zzb(bArr, i, i2, i6);
            this.zzcpa.zzb(bArr, 0, i2 + i6, i3 - i6);
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public final String zzb(Charset charset) {
        byte[] bArr;
        int size = size();
        if (size == 0) {
            bArr = zzxd.zzcmh;
        } else {
            byte[] bArr2 = new byte[size];
            zzb(bArr2, 0, 0, size);
            bArr = bArr2;
        }
        return new String(bArr, charset);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public final void zza(zzvs zzvsVar) throws IOException {
        this.zzcoz.zza(zzvsVar);
        this.zzcpa.zza(zzvsVar);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public final int zzb(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.zzcpb;
        if (i4 <= i5) {
            return this.zzcoz.zzb(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.zzcpa.zzb(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.zzcpa.zzb(this.zzcoz.zzb(i, i2, i6), 0, i3 - i6);
    }
}
