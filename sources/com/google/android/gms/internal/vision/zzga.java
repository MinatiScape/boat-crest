package com.google.android.gms.internal.vision;

import com.htsmart.wristband2.WristbandManager;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes10.dex */
public abstract class zzga extends zzfi {
    public static final Logger b = Logger.getLogger(zzga.class.getName());
    public static final boolean c = j4.t();

    /* renamed from: a  reason: collision with root package name */
    public a2 f10019a;

    /* loaded from: classes10.dex */
    public static class zza extends IOException {
        public zza() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        public zza(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public zza(java.lang.String r3, java.lang.Throwable r4) {
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzga.zza.<init>(java.lang.String, java.lang.Throwable):void");
        }
    }

    public zzga() {
    }

    public static int a(zzic zzicVar, o3 o3Var) {
        zzet zzetVar = (zzet) zzicVar;
        int b2 = zzetVar.b();
        if (b2 == -1) {
            b2 = o3Var.f(zzetVar);
            zzetVar.a(b2);
        }
        return zzbd(b2) + b2;
    }

    public static long d(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int e(int i, zzic zzicVar, o3 o3Var) {
        return zzbb(i) + a(zzicVar, o3Var);
    }

    public static int f(int i) {
        return (i >> 31) ^ (i << 1);
    }

    @Deprecated
    public static int g(int i, zzic zzicVar, o3 o3Var) {
        int zzbb = zzbb(i) << 1;
        zzet zzetVar = (zzet) zzicVar;
        int b2 = zzetVar.b();
        if (b2 == -1) {
            b2 = o3Var.f(zzetVar);
            zzetVar.a(b2);
        }
        return zzbb + b2;
    }

    public static int zzb(double d) {
        return 8;
    }

    public static int zzbb(int i) {
        return zzbd(i << 3);
    }

    public static int zzbc(int i) {
        if (i >= 0) {
            return zzbd(i);
        }
        return 10;
    }

    public static int zzbd(int i) {
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

    public static int zzbe(int i) {
        return zzbd(f(i));
    }

    public static int zzbf(int i) {
        return 4;
    }

    public static int zzbg(int i) {
        return 4;
    }

    public static int zzbh(int i) {
        return zzbc(i);
    }

    @Deprecated
    public static int zzbj(int i) {
        return zzbd(i);
    }

    public static int zzc(int i, zzfh zzfhVar) {
        int zzbb = zzbb(i);
        int size = zzfhVar.size();
        return zzbb + zzbd(size) + size;
    }

    public static int zzd(int i, long j) {
        return zzbb(i) + zzw(j);
    }

    public static zzga zze(byte[] bArr) {
        return new a(bArr, 0, bArr.length);
    }

    public static int zzf(int i, long j) {
        return zzbb(i) + zzw(d(j));
    }

    public static int zzg(int i, long j) {
        return zzbb(i) + 8;
    }

    public static int zzh(int i, long j) {
        return zzbb(i) + 8;
    }

    public static int zzl(int i, int i2) {
        return zzbb(i) + zzbd(i2);
    }

    public static int zzl(boolean z) {
        return 1;
    }

    public static int zzm(int i, int i2) {
        return zzbb(i) + zzbd(f(i2));
    }

    public static int zzn(int i, int i2) {
        return zzbb(i) + 4;
    }

    public static int zzo(int i, int i2) {
        return zzbb(i) + 4;
    }

    public static int zzp(int i, int i2) {
        return zzbb(i) + zzbc(i2);
    }

    public static int zzt(float f) {
        return 4;
    }

    public static int zzv(long j) {
        return zzw(j);
    }

    public static int zzw(long j) {
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

    public static int zzx(long j) {
        return zzw(d(j));
    }

    public static int zzy(long j) {
        return 8;
    }

    public static int zzy(String str) {
        int length;
        try {
            length = m4.a(str);
        } catch (p4 unused) {
            length = str.getBytes(zzgt.f10024a).length;
        }
        return zzbd(length) + length;
    }

    public static int zzz(long j) {
        return 8;
    }

    public abstract void b(int i, zzic zzicVar, o3 o3Var) throws IOException;

    public final void c(String str, p4 p4Var) throws IOException {
        b.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) p4Var);
        byte[] bytes = str.getBytes(zzgt.f10024a);
        try {
            zzay(bytes.length);
            zzc(bytes, 0, bytes.length);
        } catch (zza e) {
            throw e;
        } catch (IndexOutOfBoundsException e2) {
            throw new zza(e2);
        }
    }

    public abstract void writeTag(int i, int i2) throws IOException;

    public final void zza(int i, float f) throws IOException {
        zzj(i, Float.floatToRawIntBits(f));
    }

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzfh zzfhVar) throws IOException;

    public abstract void zza(int i, zzic zzicVar) throws IOException;

    public abstract void zza(int i, String str) throws IOException;

    public abstract void zza(int i, boolean z) throws IOException;

    public abstract void zza(zzfh zzfhVar) throws IOException;

    public abstract void zzax(int i) throws IOException;

    public abstract void zzay(int i) throws IOException;

    public final void zzaz(int i) throws IOException {
        zzay(f(i));
    }

    public final void zzb(int i, long j) throws IOException {
        zza(i, d(j));
    }

    public abstract void zzb(int i, zzfh zzfhVar) throws IOException;

    public abstract void zzb(zzic zzicVar) throws IOException;

    public abstract void zzba(int i) throws IOException;

    public abstract void zzc(byte b2) throws IOException;

    public abstract void zzc(int i, long j) throws IOException;

    public abstract int zzfg();

    public final void zzfh() {
        if (zzfg() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public abstract void zzg(int i, int i2) throws IOException;

    public abstract void zzh(int i, int i2) throws IOException;

    public final void zzi(int i, int i2) throws IOException {
        zzh(i, f(i2));
    }

    public abstract void zzj(int i, int i2) throws IOException;

    public final void zzk(boolean z) throws IOException {
        zzc(z ? (byte) 1 : (byte) 0);
    }

    public final void zzs(float f) throws IOException {
        zzba(Float.floatToRawIntBits(f));
    }

    public abstract void zzs(long j) throws IOException;

    public final void zzt(long j) throws IOException {
        zzs(d(j));
    }

    public abstract void zzu(long j) throws IOException;

    public abstract void zzx(String str) throws IOException;

    /* loaded from: classes10.dex */
    public static class a extends zzga {
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

        @Override // com.google.android.gms.internal.vision.zzga
        public final void b(int i, zzic zzicVar, o3 o3Var) throws IOException {
            writeTag(i, 2);
            zzet zzetVar = (zzet) zzicVar;
            int b = zzetVar.b();
            if (b == -1) {
                b = o3Var.f(zzetVar);
                zzetVar.a(b);
            }
            zzay(b);
            o3Var.h(zzicVar, this.f10019a);
        }

        public final void i(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.d, this.f, i2);
                this.f += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f), Integer.valueOf(this.e), Integer.valueOf(i2)), e);
            }
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void writeTag(int i, int i2) throws IOException {
            zzay((i << 3) | i2);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zza(int i, long j) throws IOException {
            writeTag(i, 0);
            zzs(j);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzax(int i) throws IOException {
            if (i >= 0) {
                zzay(i);
            } else {
                zzs(i);
            }
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzay(int i) throws IOException {
            if (!zzga.c || e1.a() || zzfg() < 5) {
                while ((i & WristbandManager.SYNC_STATE_FAILED_UNKNOWN) != 0) {
                    try {
                        byte[] bArr = this.d;
                        int i2 = this.f;
                        this.f = i2 + 1;
                        bArr[i2] = (byte) ((i & 127) | 128);
                        i >>>= 7;
                    } catch (IndexOutOfBoundsException e) {
                        throw new zza(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f), Integer.valueOf(this.e), 1), e);
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
                j4.h(bArr3, i4, (byte) i);
            } else {
                byte[] bArr4 = this.d;
                int i5 = this.f;
                this.f = i5 + 1;
                j4.h(bArr4, i5, (byte) (i | 128));
                int i6 = i >>> 7;
                if ((i6 & WristbandManager.SYNC_STATE_FAILED_UNKNOWN) == 0) {
                    byte[] bArr5 = this.d;
                    int i7 = this.f;
                    this.f = i7 + 1;
                    j4.h(bArr5, i7, (byte) i6);
                    return;
                }
                byte[] bArr6 = this.d;
                int i8 = this.f;
                this.f = i8 + 1;
                j4.h(bArr6, i8, (byte) (i6 | 128));
                int i9 = i6 >>> 7;
                if ((i9 & WristbandManager.SYNC_STATE_FAILED_UNKNOWN) == 0) {
                    byte[] bArr7 = this.d;
                    int i10 = this.f;
                    this.f = i10 + 1;
                    j4.h(bArr7, i10, (byte) i9);
                    return;
                }
                byte[] bArr8 = this.d;
                int i11 = this.f;
                this.f = i11 + 1;
                j4.h(bArr8, i11, (byte) (i9 | 128));
                int i12 = i9 >>> 7;
                if ((i12 & WristbandManager.SYNC_STATE_FAILED_UNKNOWN) == 0) {
                    byte[] bArr9 = this.d;
                    int i13 = this.f;
                    this.f = i13 + 1;
                    j4.h(bArr9, i13, (byte) i12);
                    return;
                }
                byte[] bArr10 = this.d;
                int i14 = this.f;
                this.f = i14 + 1;
                j4.h(bArr10, i14, (byte) (i12 | 128));
                byte[] bArr11 = this.d;
                int i15 = this.f;
                this.f = i15 + 1;
                j4.h(bArr11, i15, (byte) (i12 >>> 7));
            }
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzb(int i, zzfh zzfhVar) throws IOException {
            writeTag(1, 3);
            zzh(2, i);
            zza(3, zzfhVar);
            writeTag(1, 4);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzba(int i) throws IOException {
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
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f), Integer.valueOf(this.e), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzc(int i, long j) throws IOException {
            writeTag(i, 1);
            zzu(j);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final int zzfg() {
            return this.e - this.f;
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzg(int i, int i2) throws IOException {
            writeTag(i, 0);
            zzax(i2);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzh(int i, int i2) throws IOException {
            writeTag(i, 0);
            zzay(i2);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzj(int i, int i2) throws IOException {
            writeTag(i, 5);
            zzba(i2);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzs(long j) throws IOException {
            if (zzga.c && zzfg() >= 10) {
                while ((j & (-128)) != 0) {
                    byte[] bArr = this.d;
                    int i = this.f;
                    this.f = i + 1;
                    j4.h(bArr, i, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr2 = this.d;
                int i2 = this.f;
                this.f = i2 + 1;
                j4.h(bArr2, i2, (byte) j);
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
                    throw new zza(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f), Integer.valueOf(this.e), 1), e);
                }
            }
            byte[] bArr4 = this.d;
            int i4 = this.f;
            this.f = i4 + 1;
            bArr4[i4] = (byte) j;
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzu(long j) throws IOException {
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
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f), Integer.valueOf(this.e), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzx(String str) throws IOException {
            int i = this.f;
            try {
                int zzbd = zzga.zzbd(str.length() * 3);
                int zzbd2 = zzga.zzbd(str.length());
                if (zzbd2 == zzbd) {
                    int i2 = i + zzbd2;
                    this.f = i2;
                    int b = m4.b(str, this.d, i2, zzfg());
                    this.f = i;
                    zzay((b - i) - zzbd2);
                    this.f = b;
                    return;
                }
                zzay(m4.a(str));
                this.f = m4.b(str, this.d, this.f, zzfg());
            } catch (p4 e) {
                this.f = i;
                c(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zza(e2);
            }
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zza(int i, boolean z) throws IOException {
            writeTag(i, 0);
            zzc(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzc(byte b) throws IOException {
            try {
                byte[] bArr = this.d;
                int i = this.f;
                this.f = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f), Integer.valueOf(this.e), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zza(int i, String str) throws IOException {
            writeTag(i, 2);
            zzx(str);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zzb(zzic zzicVar) throws IOException {
            zzay(zzicVar.zzgf());
            zzicVar.zzb(this);
        }

        @Override // com.google.android.gms.internal.vision.zzfi
        public final void zzc(byte[] bArr, int i, int i2) throws IOException {
            i(bArr, i, i2);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zza(int i, zzfh zzfhVar) throws IOException {
            writeTag(i, 2);
            zza(zzfhVar);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zza(zzfh zzfhVar) throws IOException {
            zzay(zzfhVar.size());
            zzfhVar.zza(this);
        }

        @Override // com.google.android.gms.internal.vision.zzga
        public final void zza(int i, zzic zzicVar) throws IOException {
            writeTag(1, 3);
            zzh(2, i);
            writeTag(3, 2);
            zzb(zzicVar);
            writeTag(1, 4);
        }
    }

    public static int zzb(int i, float f) {
        return zzbb(i) + 4;
    }

    public static int zzk(int i, int i2) {
        return zzbb(i) + zzbc(i2);
    }

    public final void zza(int i, double d) throws IOException {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public static int zzb(int i, double d) {
        return zzbb(i) + 8;
    }

    public static int zzd(int i, zzfh zzfhVar) {
        return (zzbb(1) << 1) + zzl(2, i) + zzc(3, zzfhVar);
    }

    public static int zze(int i, long j) {
        return zzbb(i) + zzw(j);
    }

    public static int zzf(byte[] bArr) {
        int length = bArr.length;
        return zzbd(length) + length;
    }

    public final void zza(double d) throws IOException {
        zzu(Double.doubleToRawLongBits(d));
    }

    public static int zza(int i, zzhh zzhhVar) {
        int zzbb = zzbb(i);
        int zzgf = zzhhVar.zzgf();
        return zzbb + zzbd(zzgf) + zzgf;
    }

    public static int zzb(int i, boolean z) {
        return zzbb(i) + 1;
    }

    public static int zzc(zzic zzicVar) {
        int zzgf = zzicVar.zzgf();
        return zzbd(zzgf) + zzgf;
    }

    public static int zzb(int i, String str) {
        return zzbb(i) + zzy(str);
    }

    public static int zzb(int i, zzic zzicVar) {
        return (zzbb(1) << 1) + zzl(2, i) + zzbb(3) + zzc(zzicVar);
    }

    @Deprecated
    public static int zzd(zzic zzicVar) {
        return zzicVar.zzgf();
    }

    public static int zza(zzhh zzhhVar) {
        int zzgf = zzhhVar.zzgf();
        return zzbd(zzgf) + zzgf;
    }

    public static int zzb(int i, zzhh zzhhVar) {
        return (zzbb(1) << 1) + zzl(2, i) + zza(3, zzhhVar);
    }

    public static int zzb(zzfh zzfhVar) {
        int size = zzfhVar.size();
        return zzbd(size) + size;
    }
}
