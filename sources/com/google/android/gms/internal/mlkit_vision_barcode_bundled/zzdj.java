package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.htsmart.wristband2.WristbandManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes8.dex */
public abstract class zzdj extends zzcr {
    public static final Logger b = Logger.getLogger(zzdj.class.getName());
    public static final boolean c = g2.C();
    public static final /* synthetic */ int zzb = 0;

    /* renamed from: a  reason: collision with root package name */
    public z f9629a;

    public zzdj() {
    }

    public /* synthetic */ zzdj(zzdi zzdiVar) {
    }

    @Deprecated
    public static int c(int i, zzfo zzfoVar, l1 l1Var) {
        int a2 = ((zzck) zzfoVar).a(l1Var);
        int zzy = zzy(i << 3);
        return zzy + zzy + a2;
    }

    public static int d(zzfo zzfoVar, l1 l1Var) {
        int a2 = ((zzck) zzfoVar).a(l1Var);
        return zzy(a2) + a2;
    }

    public static zzdj zzA(byte[] bArr, int i, int i2) {
        return new y(bArr, 0, i2);
    }

    public static int zzu(int i) {
        if (i >= 0) {
            return zzy(i);
        }
        return 10;
    }

    public static int zzv(zzfo zzfoVar) {
        int zzE = zzfoVar.zzE();
        return zzy(zzE) + zzE;
    }

    public static int zzx(String str) {
        int length;
        try {
            length = l2.e(str);
        } catch (k2 unused) {
            length = str.getBytes(zzem.f9632a).length;
        }
        return zzy(length) + length;
    }

    public static int zzy(int i) {
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

    public static int zzz(long j) {
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
            j >>>= 14;
            i += 2;
        }
        return (j & (-16384)) != 0 ? i + 1 : i;
    }

    public final void a(String str, k2 k2Var) throws IOException {
        b.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) k2Var);
        byte[] bytes = str.getBytes(zzem.f9632a);
        try {
            int length = bytes.length;
            zzq(length);
            zzl(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzdh(e);
        }
    }

    public final void zzB() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b2) throws IOException;

    public abstract void zzd(int i, boolean z) throws IOException;

    public abstract void zze(int i, zzdb zzdbVar) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzg(int i) throws IOException;

    public abstract void zzh(int i, long j) throws IOException;

    public abstract void zzi(long j) throws IOException;

    public abstract void zzj(int i, int i2) throws IOException;

    public abstract void zzk(int i) throws IOException;

    public abstract void zzl(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzm(int i, String str) throws IOException;

    public abstract void zzo(int i, int i2) throws IOException;

    public abstract void zzp(int i, int i2) throws IOException;

    public abstract void zzq(int i) throws IOException;

    public abstract void zzr(int i, long j) throws IOException;

    public abstract void zzs(long j) throws IOException;
}
