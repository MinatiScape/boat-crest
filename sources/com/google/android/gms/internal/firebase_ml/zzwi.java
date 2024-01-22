package com.google.android.gms.internal.firebase_ml;

import com.htsmart.wristband2.WristbandManager;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes7.dex */
public abstract class zzwi extends zzvs {
    public static final Logger b = Logger.getLogger(zzwi.class.getName());
    public static final boolean c = b.J();

    /* renamed from: a  reason: collision with root package name */
    public l6 f8810a;

    /* loaded from: classes7.dex */
    public static class zzb extends IOException {
        public zzb() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        public zzb(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public zzb(java.lang.String r3, java.lang.Throwable r4) {
            /*
                r2 = this;
                java.lang.String r0 = "CodedOutputStream was writing to a flat byte array and ran out of space.: "
                java.lang.String r3 = java.lang.String.valueOf(r3)
                int r1 = r3.length()
                if (r1 == 0) goto L11
                java.lang.String r3 = r0.concat(r3)
                goto L16
            L11:
                java.lang.String r3 = new java.lang.String
                r3.<init>(r0)
            L16:
                r2.<init>(r3, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzwi.zzb.<init>(java.lang.String, java.lang.Throwable):void");
        }
    }

    public zzwi() {
    }

    public static int a(zzyk zzykVar, c8 c8Var) {
        zzvl zzvlVar = (zzvl) zzykVar;
        int b2 = zzvlVar.b();
        if (b2 == -1) {
            b2 = c8Var.i(zzvlVar);
            zzvlVar.a(b2);
        }
        return zzdh(b2) + b2;
    }

    public static long d(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int e(int i, zzyk zzykVar, c8 c8Var) {
        return zzdf(i) + a(zzykVar, c8Var);
    }

    @Deprecated
    public static int f(int i, zzyk zzykVar, c8 c8Var) {
        int zzdf = zzdf(i) << 1;
        zzvl zzvlVar = (zzvl) zzykVar;
        int b2 = zzvlVar.b();
        if (b2 == -1) {
            b2 = c8Var.i(zzvlVar);
            zzvlVar.a(b2);
        }
        return zzdf + b2;
    }

    public static int g(int i) {
        return zzdh(i) + i;
    }

    public static int h(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public static int zzaa(long j) {
        int i;
        if (((-128) & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if (((-34359738368L) & j) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if (((-2097152) & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & (-16384)) != 0 ? i + 1 : i;
    }

    public static int zzab(long j) {
        return zzaa(d(j));
    }

    public static int zzac(long j) {
        return 8;
    }

    public static int zzad(long j) {
        return 8;
    }

    public static int zzay(boolean z) {
        return 1;
    }

    public static int zzcl(String str) {
        int length;
        try {
            length = e.c(str);
        } catch (h unused) {
            length = str.getBytes(zzxd.f8814a).length;
        }
        return zzdh(length) + length;
    }

    public static int zzd(double d) {
        return 8;
    }

    public static int zzd(int i, long j) {
        return zzdf(i) + zzaa(j);
    }

    public static int zzdf(int i) {
        return zzdh(i << 3);
    }

    public static int zzdg(int i) {
        if (i >= 0) {
            return zzdh(i);
        }
        return 10;
    }

    public static int zzdh(int i) {
        if ((i & WristbandManager.SYNC_STATE_FAILED_UNKNOWN) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    public static int zzdi(int i) {
        return zzdh(h(i));
    }

    public static int zzdj(int i) {
        return 4;
    }

    public static int zzdk(int i) {
        return 4;
    }

    public static int zzdl(int i) {
        return zzdg(i);
    }

    @Deprecated
    public static int zzdo(int i) {
        return zzdh(i);
    }

    public static int zze(int i, long j) {
        return zzdf(i) + zzaa(j);
    }

    public static int zzf(int i, long j) {
        return zzdf(i) + zzaa(d(j));
    }

    public static zzwi zzg(byte[] bArr) {
        return new a(bArr, 0, bArr.length);
    }

    public static int zzh(int i, long j) {
        return zzdf(i) + 8;
    }

    public static int zzl(int i, int i2) {
        return zzdf(i) + zzdg(i2);
    }

    public static int zzm(int i, int i2) {
        return zzdf(i) + zzdh(i2);
    }

    public static int zzn(int i, int i2) {
        return zzdf(i) + zzdh(h(i2));
    }

    public static int zzo(int i, int i2) {
        return zzdf(i) + 4;
    }

    public static int zzp(int i, int i2) {
        return zzdf(i) + 4;
    }

    public static int zzr(float f) {
        return 4;
    }

    public static int zzz(long j) {
        return zzaa(j);
    }

    public abstract void b(int i, zzyk zzykVar, c8 c8Var) throws IOException;

    public final void c(String str, h hVar) throws IOException {
        b.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) hVar);
        byte[] bytes = str.getBytes(zzxd.f8814a);
        try {
            zzdc(bytes.length);
            zzb(bytes, 0, bytes.length);
        } catch (zzb e) {
            throw e;
        } catch (IndexOutOfBoundsException e2) {
            throw new zzb(e2);
        }
    }

    public abstract void i(byte[] bArr, int i, int i2) throws IOException;

    public abstract void writeTag(int i, int i2) throws IOException;

    public final void zza(int i, float f) throws IOException {
        zzk(i, Float.floatToRawIntBits(f));
    }

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzvv zzvvVar) throws IOException;

    public abstract void zza(int i, zzyk zzykVar) throws IOException;

    public abstract void zza(int i, boolean z) throws IOException;

    public final void zzax(boolean z) throws IOException {
        zzd(z ? (byte) 1 : (byte) 0);
    }

    public final void zzb(int i, long j) throws IOException {
        zza(i, d(j));
    }

    public abstract void zzb(int i, zzvv zzvvVar) throws IOException;

    public abstract void zzb(int i, String str) throws IOException;

    public abstract void zzb(zzyk zzykVar) throws IOException;

    public final void zzc(double d) throws IOException {
        zzy(Double.doubleToRawLongBits(d));
    }

    public abstract void zzc(int i, long j) throws IOException;

    public abstract void zzc(zzvv zzvvVar) throws IOException;

    public abstract void zzck(String str) throws IOException;

    public abstract void zzd(byte b2) throws IOException;

    public abstract void zzdb(int i) throws IOException;

    public abstract void zzdc(int i) throws IOException;

    public final void zzdd(int i) throws IOException {
        zzdc(h(i));
    }

    public abstract void zzde(int i) throws IOException;

    public abstract void zzh(int i, int i2) throws IOException;

    public abstract void zzi(int i, int i2) throws IOException;

    public final void zzj(int i, int i2) throws IOException {
        zzi(i, h(i2));
    }

    public abstract void zzk(int i, int i2) throws IOException;

    public final void zzq(float f) throws IOException {
        zzde(Float.floatToRawIntBits(f));
    }

    public abstract int zzty();

    public final void zztz() {
        if (zzty() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public abstract void zzw(long j) throws IOException;

    public final void zzx(long j) throws IOException {
        zzw(d(j));
    }

    public abstract void zzy(long j) throws IOException;

    /* loaded from: classes7.dex */
    public static class a extends zzwi {
        public final byte[] d;
        public final int e;
        public int f;

        public a(byte[] bArr, int i, int i2) {
            super();
            Objects.requireNonNull(bArr, "buffer");
            int i3 = i2 + 0;
            if ((i2 | 0 | (bArr.length - i3)) >= 0) {
                this.d = bArr;
                this.f = 0;
                this.e = i3;
                return;
            }
            throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), 0, Integer.valueOf(i2)));
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void b(int i, zzyk zzykVar, c8 c8Var) throws IOException {
            writeTag(i, 2);
            zzvl zzvlVar = (zzvl) zzykVar;
            int b = zzvlVar.b();
            if (b == -1) {
                b = c8Var.i(zzvlVar);
                zzvlVar.a(b);
            }
            zzdc(b);
            c8Var.g(zzykVar, this.f8810a);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void i(byte[] bArr, int i, int i2) throws IOException {
            zzdc(i2);
            k(bArr, 0, i2);
        }

        public final void k(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.d, this.f, i2);
                this.f += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f), Integer.valueOf(this.e), Integer.valueOf(i2)), e);
            }
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void writeTag(int i, int i2) throws IOException {
            zzdc((i << 3) | i2);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void zza(int i, long j) throws IOException {
            writeTag(i, 0);
            zzw(j);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void zzb(int i, String str) throws IOException {
            writeTag(i, 2);
            zzck(str);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void zzc(int i, long j) throws IOException {
            writeTag(i, 1);
            zzy(j);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void zzck(String str) throws IOException {
            int i = this.f;
            try {
                int zzdh = zzwi.zzdh(str.length() * 3);
                int zzdh2 = zzwi.zzdh(str.length());
                if (zzdh2 == zzdh) {
                    int i2 = i + zzdh2;
                    this.f = i2;
                    int a2 = e.a(str, this.d, i2, zzty());
                    this.f = i;
                    zzdc((a2 - i) - zzdh2);
                    this.f = a2;
                    return;
                }
                zzdc(e.c(str));
                this.f = e.a(str, this.d, this.f, zzty());
            } catch (h e) {
                this.f = i;
                c(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(e2);
            }
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void zzd(byte b) throws IOException {
            try {
                byte[] bArr = this.d;
                int i = this.f;
                this.f = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f), Integer.valueOf(this.e), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void zzdb(int i) throws IOException {
            if (i >= 0) {
                zzdc(i);
            } else {
                zzw(i);
            }
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void zzdc(int i) throws IOException {
            if (!zzwi.c || t5.b() || zzty() < 5) {
                while ((i & WristbandManager.SYNC_STATE_FAILED_UNKNOWN) != 0) {
                    try {
                        byte[] bArr = this.d;
                        int i2 = this.f;
                        this.f = i2 + 1;
                        bArr[i2] = (byte) ((i & 127) | 128);
                        i >>>= 7;
                    } catch (IndexOutOfBoundsException e) {
                        throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f), Integer.valueOf(this.e), 1), e);
                    }
                }
                byte[] bArr2 = this.d;
                int i3 = this.f;
                this.f = i3 + 1;
                bArr2[i3] = (byte) i;
            } else if ((i & WristbandManager.SYNC_STATE_FAILED_UNKNOWN) == 0) {
                byte[] bArr3 = this.d;
                int i4 = this.f;
                this.f = i4 + 1;
                b.i(bArr3, i4, (byte) i);
            } else {
                byte[] bArr4 = this.d;
                int i5 = this.f;
                this.f = i5 + 1;
                b.i(bArr4, i5, (byte) (i | 128));
                int i6 = i >>> 7;
                if ((i6 & WristbandManager.SYNC_STATE_FAILED_UNKNOWN) == 0) {
                    byte[] bArr5 = this.d;
                    int i7 = this.f;
                    this.f = i7 + 1;
                    b.i(bArr5, i7, (byte) i6);
                    return;
                }
                byte[] bArr6 = this.d;
                int i8 = this.f;
                this.f = i8 + 1;
                b.i(bArr6, i8, (byte) (i6 | 128));
                int i9 = i6 >>> 7;
                if ((i9 & WristbandManager.SYNC_STATE_FAILED_UNKNOWN) == 0) {
                    byte[] bArr7 = this.d;
                    int i10 = this.f;
                    this.f = i10 + 1;
                    b.i(bArr7, i10, (byte) i9);
                    return;
                }
                byte[] bArr8 = this.d;
                int i11 = this.f;
                this.f = i11 + 1;
                b.i(bArr8, i11, (byte) (i9 | 128));
                int i12 = i9 >>> 7;
                if ((i12 & WristbandManager.SYNC_STATE_FAILED_UNKNOWN) == 0) {
                    byte[] bArr9 = this.d;
                    int i13 = this.f;
                    this.f = i13 + 1;
                    b.i(bArr9, i13, (byte) i12);
                    return;
                }
                byte[] bArr10 = this.d;
                int i14 = this.f;
                this.f = i14 + 1;
                b.i(bArr10, i14, (byte) (i12 | 128));
                byte[] bArr11 = this.d;
                int i15 = this.f;
                this.f = i15 + 1;
                b.i(bArr11, i15, (byte) (i12 >>> 7));
            }
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void zzde(int i) throws IOException {
            try {
                byte[] bArr = this.d;
                int i2 = this.f;
                int i3 = i2 + 1;
                this.f = i3;
                bArr[i2] = (byte) i;
                int i4 = i3 + 1;
                this.f = i4;
                bArr[i3] = (byte) (i >> 8);
                int i5 = i4 + 1;
                this.f = i5;
                bArr[i4] = (byte) (i >> 16);
                this.f = i5 + 1;
                bArr[i5] = (byte) (i >>> 24);
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f), Integer.valueOf(this.e), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void zzh(int i, int i2) throws IOException {
            writeTag(i, 0);
            zzdb(i2);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void zzi(int i, int i2) throws IOException {
            writeTag(i, 0);
            zzdc(i2);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void zzk(int i, int i2) throws IOException {
            writeTag(i, 5);
            zzde(i2);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final int zzty() {
            return this.e - this.f;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void zzw(long j) throws IOException {
            if (zzwi.c && zzty() >= 10) {
                while ((j & (-128)) != 0) {
                    byte[] bArr = this.d;
                    int i = this.f;
                    this.f = i + 1;
                    b.i(bArr, i, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr2 = this.d;
                int i2 = this.f;
                this.f = i2 + 1;
                b.i(bArr2, i2, (byte) j);
                return;
            }
            while ((j & (-128)) != 0) {
                try {
                    byte[] bArr3 = this.d;
                    int i3 = this.f;
                    this.f = i3 + 1;
                    bArr3[i3] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f), Integer.valueOf(this.e), 1), e);
                }
            }
            byte[] bArr4 = this.d;
            int i4 = this.f;
            this.f = i4 + 1;
            bArr4[i4] = (byte) j;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void zzy(long j) throws IOException {
            try {
                byte[] bArr = this.d;
                int i = this.f;
                int i2 = i + 1;
                this.f = i2;
                bArr[i] = (byte) j;
                int i3 = i2 + 1;
                this.f = i3;
                bArr[i2] = (byte) (j >> 8);
                int i4 = i3 + 1;
                this.f = i4;
                bArr[i3] = (byte) (j >> 16);
                int i5 = i4 + 1;
                this.f = i5;
                bArr[i4] = (byte) (j >> 24);
                int i6 = i5 + 1;
                this.f = i6;
                bArr[i5] = (byte) (j >> 32);
                int i7 = i6 + 1;
                this.f = i7;
                bArr[i6] = (byte) (j >> 40);
                int i8 = i7 + 1;
                this.f = i8;
                bArr[i7] = (byte) (j >> 48);
                this.f = i8 + 1;
                bArr[i8] = (byte) (j >> 56);
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f), Integer.valueOf(this.e), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void zza(int i, boolean z) throws IOException {
            writeTag(i, 0);
            zzd(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void zzb(int i, zzvv zzvvVar) throws IOException {
            writeTag(1, 3);
            zzi(2, i);
            zza(3, zzvvVar);
            writeTag(1, 4);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void zzc(zzvv zzvvVar) throws IOException {
            zzdc(zzvvVar.size());
            zzvvVar.zza(this);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void zza(int i, zzvv zzvvVar) throws IOException {
            writeTag(i, 2);
            zzc(zzvvVar);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void zza(int i, zzyk zzykVar) throws IOException {
            writeTag(1, 3);
            zzi(2, i);
            writeTag(3, 2);
            zzb(zzykVar);
            writeTag(1, 4);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwi
        public final void zzb(zzyk zzykVar) throws IOException {
            zzdc(zzykVar.zzuo());
            zzykVar.zzb(this);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzvs
        public final void zzb(byte[] bArr, int i, int i2) throws IOException {
            k(bArr, i, i2);
        }
    }

    public static int zzb(int i, float f) {
        return zzdf(i) + 4;
    }

    public static int zzc(int i, String str) {
        return zzdf(i) + zzcl(str);
    }

    public static int zzh(byte[] bArr) {
        int length = bArr.length;
        return zzdh(length) + length;
    }

    public static int zzq(int i, int i2) {
        return zzdf(i) + zzdg(i2);
    }

    public final void zza(int i, double d) throws IOException {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public static int zza(int i, zzxt zzxtVar) {
        int zzdf = zzdf(i);
        int zzuo = zzxtVar.zzuo();
        return zzdf + zzdh(zzuo) + zzuo;
    }

    public static int zzb(int i, double d) {
        return zzdf(i) + 8;
    }

    public static int zzc(int i, zzvv zzvvVar) {
        int zzdf = zzdf(i);
        int size = zzvvVar.size();
        return zzdf + zzdh(size) + size;
    }

    public static int zzd(int i, zzvv zzvvVar) {
        return (zzdf(1) << 1) + zzm(2, i) + zzc(3, zzvvVar);
    }

    public static int zzg(int i, long j) {
        return zzdf(i) + 8;
    }

    public static int zzb(int i, boolean z) {
        return zzdf(i) + 1;
    }

    public static int zzb(int i, zzyk zzykVar) {
        return (zzdf(1) << 1) + zzm(2, i) + zzdf(3) + zzc(zzykVar);
    }

    public static int zza(zzxt zzxtVar) {
        int zzuo = zzxtVar.zzuo();
        return zzdh(zzuo) + zzuo;
    }

    public static int zzc(zzyk zzykVar) {
        int zzuo = zzykVar.zzuo();
        return zzdh(zzuo) + zzuo;
    }

    public static int zzd(zzvv zzvvVar) {
        int size = zzvvVar.size();
        return zzdh(size) + size;
    }

    public static int zzb(int i, zzxt zzxtVar) {
        return (zzdf(1) << 1) + zzm(2, i) + zza(3, zzxtVar);
    }

    @Deprecated
    public static int zzd(zzyk zzykVar) {
        return zzykVar.zzuo();
    }
}
