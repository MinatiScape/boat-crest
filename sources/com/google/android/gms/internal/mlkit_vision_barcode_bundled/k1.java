package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
/* loaded from: classes8.dex */
public final class k1 extends zzdb {
    public static final int[] zza = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903, Integer.MAX_VALUE};
    private final int zzc;
    private final zzdb zzd;
    private final zzdb zze;
    private final int zzf;
    private final int zzg;

    private k1(zzdb zzdbVar, zzdb zzdbVar2) {
        this.zzd = zzdbVar;
        this.zze = zzdbVar2;
        int zzd = zzdbVar.zzd();
        this.zzf = zzd;
        this.zzc = zzd + zzdbVar2.zzd();
        this.zzg = Math.max(zzdbVar.zzf(), zzdbVar2.zzf()) + 1;
    }

    private static zzdb zzA(zzdb zzdbVar, zzdb zzdbVar2) {
        int zzd = zzdbVar.zzd();
        int zzd2 = zzdbVar2.zzd();
        byte[] bArr = new byte[zzd + zzd2];
        zzdbVar.zzw(bArr, 0, 0, zzd);
        zzdbVar2.zzw(bArr, 0, zzd, zzd2);
        return new u(bArr);
    }

    public static int zzc(int i) {
        int[] iArr = zza;
        int length = iArr.length;
        if (i >= 47) {
            return Integer.MAX_VALUE;
        }
        return iArr[i];
    }

    public static zzdb zzz(zzdb zzdbVar, zzdb zzdbVar2) {
        if (zzdbVar2.zzd() == 0) {
            return zzdbVar;
        }
        if (zzdbVar.zzd() == 0) {
            return zzdbVar2;
        }
        int zzd = zzdbVar.zzd() + zzdbVar2.zzd();
        if (zzd < 128) {
            return zzA(zzdbVar, zzdbVar2);
        }
        if (zzdbVar instanceof k1) {
            k1 k1Var = (k1) zzdbVar;
            if (k1Var.zze.zzd() + zzdbVar2.zzd() < 128) {
                return new k1(k1Var.zzd, zzA(k1Var.zze, zzdbVar2));
            } else if (k1Var.zzd.zzf() > k1Var.zze.zzf() && k1Var.zzg > zzdbVar2.zzf()) {
                return new k1(k1Var.zzd, new k1(k1Var.zze, zzdbVar2));
            }
        }
        if (zzd >= zzc(Math.max(zzdbVar.zzf(), zzdbVar2.zzf()) + 1)) {
            return new k1(zzdbVar, zzdbVar2);
        }
        return i1.a(new i1(null), zzdbVar, zzdbVar2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final boolean equals(Object obj) {
        boolean zzg;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdb)) {
            return false;
        }
        zzdb zzdbVar = (zzdb) obj;
        if (this.zzc != zzdbVar.zzd()) {
            return false;
        }
        if (this.zzc == 0) {
            return true;
        }
        int zzp = zzp();
        int zzp2 = zzdbVar.zzp();
        if (zzp != 0 && zzp2 != 0 && zzp != zzp2) {
            return false;
        }
        j1 j1Var = new j1(this, null);
        t next = j1Var.next();
        j1 j1Var2 = new j1(zzdbVar, null);
        t next2 = j1Var2.next();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int zzd = next.zzd() - i;
            int zzd2 = next2.zzd() - i2;
            int min = Math.min(zzd, zzd2);
            if (i == 0) {
                zzg = next.zzg(next2, i2, min);
            } else {
                zzg = next2.zzg(next, i, min);
            }
            if (!zzg) {
                return false;
            }
            i3 += min;
            int i4 = this.zzc;
            if (i3 >= i4) {
                if (i3 == i4) {
                    return true;
                }
                throw new IllegalStateException();
            }
            if (min == zzd) {
                next = j1Var.next();
                i = 0;
            } else {
                i += min;
            }
            if (min == zzd2) {
                next2 = j1Var2.next();
                i2 = 0;
            } else {
                i2 += min;
            }
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return new h1(this);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final byte zza(int i) {
        zzdb.zzv(i, this.zzc);
        return zzb(i);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final byte zzb(int i) {
        int i2 = this.zzf;
        return i < i2 ? this.zzd.zzb(i) : this.zze.zzb(i - i2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final int zzd() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final void zze(byte[] bArr, int i, int i2, int i3) {
        int i4 = i + i3;
        int i5 = this.zzf;
        if (i4 <= i5) {
            this.zzd.zze(bArr, i, i2, i3);
        } else if (i >= i5) {
            this.zze.zze(bArr, i - i5, i2, i3);
        } else {
            int i6 = i5 - i;
            this.zzd.zze(bArr, i, i2, i6);
            this.zze.zze(bArr, 0, i2 + i6, i3 - i6);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final int zzf() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final boolean zzh() {
        return this.zzc >= zzc(this.zzg);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final int zzi(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.zzf;
        if (i4 <= i5) {
            return this.zzd.zzi(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.zze.zzi(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.zze.zzi(this.zzd.zzi(i, i2, i6), 0, i3 - i6);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final int zzj(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.zzf;
        if (i4 <= i5) {
            return this.zzd.zzj(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.zze.zzj(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.zze.zzj(this.zzd.zzj(i, i2, i6), 0, i3 - i6);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final zzdb zzk(int i, int i2) {
        int zzo = zzdb.zzo(i, i2, this.zzc);
        if (zzo == 0) {
            return zzdb.zzb;
        }
        if (zzo == this.zzc) {
            return this;
        }
        int i3 = this.zzf;
        if (i2 <= i3) {
            return this.zzd.zzk(i, i2);
        }
        if (i >= i3) {
            return this.zze.zzk(i - i3, i2 - i3);
        }
        zzdb zzdbVar = this.zzd;
        return new k1(zzdbVar.zzk(i, zzdbVar.zzd()), this.zze.zzk(0, i2 - this.zzf));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final String zzl(Charset charset) {
        return new String(zzx(), charset);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final void zzm(zzcr zzcrVar) throws IOException {
        this.zzd.zzm(zzcrVar);
        this.zze.zzm(zzcrVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final boolean zzn() {
        int zzj = this.zzd.zzj(0, 0, this.zzf);
        zzdb zzdbVar = this.zze;
        return zzdbVar.zzj(zzj, 0, zzdbVar.zzd()) == 0;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final zzcw zzq() {
        return new h1(this);
    }
}
