package com.google.android.gms.internal.measurement;

import com.htsmart.wristband2.WristbandManager;
import java.io.IOException;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class r2 extends zzjg {
    public final byte[] d;
    public final int e;
    public int f;

    public r2(byte[] bArr, int i, int i2) {
        super(null);
        Objects.requireNonNull(bArr, "buffer");
        int length = bArr.length;
        if (((length - i2) | i2) >= 0) {
            this.d = bArr;
            this.f = 0;
            this.e = i2;
            return;
        }
        throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(length), 0, Integer.valueOf(i2)));
    }

    public final void e(byte[] bArr, int i, int i2) throws IOException {
        try {
            System.arraycopy(bArr, 0, this.d, this.f, i2);
            this.f += i2;
        } catch (IndexOutOfBoundsException e) {
            throw new zzje(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f), Integer.valueOf(this.e), Integer.valueOf(i2)), e);
        }
    }

    public final void f(String str) throws IOException {
        int i = this.f;
        try {
            int zzA = zzjg.zzA(str.length() * 3);
            int zzA2 = zzjg.zzA(str.length());
            if (zzA2 == zzA) {
                int i2 = i + zzA2;
                this.f = i2;
                int b = y4.b(str, this.d, i2, this.e - i2);
                this.f = i;
                zzq((b - i) - zzA2);
                this.f = b;
                return;
            }
            zzq(y4.c(str));
            byte[] bArr = this.d;
            int i3 = this.f;
            this.f = y4.b(str, bArr, i3, this.e - i3);
        } catch (x4 e) {
            this.f = i;
            a(str, e);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzje(e2);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final int zza() {
        return this.e - this.f;
    }

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final void zzb(byte b) throws IOException {
        try {
            byte[] bArr = this.d;
            int i = this.f;
            this.f = i + 1;
            bArr[i] = b;
        } catch (IndexOutOfBoundsException e) {
            throw new zzje(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f), Integer.valueOf(this.e), 1), e);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final void zzd(int i, boolean z) throws IOException {
        zzq(i << 3);
        zzb(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final void zze(int i, zziy zziyVar) throws IOException {
        zzq((i << 3) | 2);
        zzq(zziyVar.zzd());
        zziyVar.zzh(this);
    }

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final void zzf(int i, int i2) throws IOException {
        zzq((i << 3) | 5);
        zzg(i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final void zzg(int i) throws IOException {
        try {
            byte[] bArr = this.d;
            int i2 = this.f;
            int i3 = i2 + 1;
            this.f = i3;
            bArr[i2] = (byte) (i & 255);
            int i4 = i3 + 1;
            this.f = i4;
            bArr[i3] = (byte) ((i >> 8) & 255);
            int i5 = i4 + 1;
            this.f = i5;
            bArr[i4] = (byte) ((i >> 16) & 255);
            this.f = i5 + 1;
            bArr[i5] = (byte) ((i >> 24) & 255);
        } catch (IndexOutOfBoundsException e) {
            throw new zzje(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f), Integer.valueOf(this.e), 1), e);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final void zzh(int i, long j) throws IOException {
        zzq((i << 3) | 1);
        zzi(j);
    }

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final void zzi(long j) throws IOException {
        try {
            byte[] bArr = this.d;
            int i = this.f;
            int i2 = i + 1;
            this.f = i2;
            bArr[i] = (byte) (((int) j) & 255);
            int i3 = i2 + 1;
            this.f = i3;
            bArr[i2] = (byte) (((int) (j >> 8)) & 255);
            int i4 = i3 + 1;
            this.f = i4;
            bArr[i3] = (byte) (((int) (j >> 16)) & 255);
            int i5 = i4 + 1;
            this.f = i5;
            bArr[i4] = (byte) (((int) (j >> 24)) & 255);
            int i6 = i5 + 1;
            this.f = i6;
            bArr[i5] = (byte) (((int) (j >> 32)) & 255);
            int i7 = i6 + 1;
            this.f = i7;
            bArr[i6] = (byte) (((int) (j >> 40)) & 255);
            int i8 = i7 + 1;
            this.f = i8;
            bArr[i7] = (byte) (((int) (j >> 48)) & 255);
            this.f = i8 + 1;
            bArr[i8] = (byte) (((int) (j >> 56)) & 255);
        } catch (IndexOutOfBoundsException e) {
            throw new zzje(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f), Integer.valueOf(this.e), 1), e);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final void zzj(int i, int i2) throws IOException {
        zzq(i << 3);
        zzk(i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final void zzk(int i) throws IOException {
        if (i >= 0) {
            zzq(i);
        } else {
            zzs(i);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final void zzl(byte[] bArr, int i, int i2) throws IOException {
        e(bArr, 0, i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final void zzm(int i, String str) throws IOException {
        zzq((i << 3) | 2);
        f(str);
    }

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final void zzo(int i, int i2) throws IOException {
        zzq((i << 3) | i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final void zzp(int i, int i2) throws IOException {
        zzq(i << 3);
        zzq(i2);
    }

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final void zzq(int i) throws IOException {
        boolean z;
        z = zzjg.c;
        if (z) {
            int i2 = f2.f8907a;
        }
        while ((i & WristbandManager.SYNC_STATE_FAILED_UNKNOWN) != 0) {
            try {
                byte[] bArr = this.d;
                int i3 = this.f;
                this.f = i3 + 1;
                bArr[i3] = (byte) ((i & 127) | 128);
                i >>>= 7;
            } catch (IndexOutOfBoundsException e) {
                throw new zzje(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f), Integer.valueOf(this.e), 1), e);
            }
        }
        byte[] bArr2 = this.d;
        int i4 = this.f;
        this.f = i4 + 1;
        bArr2[i4] = (byte) i;
    }

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final void zzr(int i, long j) throws IOException {
        zzq(i << 3);
        zzs(j);
    }

    @Override // com.google.android.gms.internal.measurement.zzjg
    public final void zzs(long j) throws IOException {
        boolean z;
        z = zzjg.c;
        if (z && this.e - this.f >= 10) {
            while ((j & (-128)) != 0) {
                byte[] bArr = this.d;
                int i = this.f;
                this.f = i + 1;
                t4.s(bArr, i, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            byte[] bArr2 = this.d;
            int i2 = this.f;
            this.f = i2 + 1;
            t4.s(bArr2, i2, (byte) j);
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
                throw new zzje(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f), Integer.valueOf(this.e), 1), e);
            }
        }
        byte[] bArr4 = this.d;
        int i4 = this.f;
        this.f = i4 + 1;
        bArr4[i4] = (byte) j;
    }
}
