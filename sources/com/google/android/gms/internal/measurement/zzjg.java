package com.google.android.gms.internal.measurement;

import com.htsmart.wristband2.WristbandManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes8.dex */
public abstract class zzjg extends zzin {
    public static final Logger b = Logger.getLogger(zzjg.class.getName());
    public static final boolean c = t4.C();

    /* renamed from: a  reason: collision with root package name */
    public s2 f8961a;

    public zzjg() {
    }

    public /* synthetic */ zzjg(zzjf zzjfVar) {
    }

    @Deprecated
    public static int c(int i, zzlg zzlgVar, x3 x3Var) {
        int zzA = zzA(i << 3);
        int i2 = zzA + zzA;
        zzih zzihVar = (zzih) zzlgVar;
        int a2 = zzihVar.a();
        if (a2 == -1) {
            a2 = x3Var.zza(zzihVar);
            zzihVar.b(a2);
        }
        return i2 + a2;
    }

    public static int d(zzlg zzlgVar, x3 x3Var) {
        zzih zzihVar = (zzih) zzlgVar;
        int a2 = zzihVar.a();
        if (a2 == -1) {
            a2 = x3Var.zza(zzihVar);
            zzihVar.b(a2);
        }
        return zzA(a2) + a2;
    }

    public static int zzA(int i) {
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

    public static int zzB(long j) {
        int i;
        if (((-128) & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if (((-34359738368L) & j) != 0) {
            j >>>= 28;
            i = 6;
        } else {
            i = 2;
        }
        if (((-2097152) & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & (-16384)) != 0 ? i + 1 : i;
    }

    public static zzjg zzC(byte[] bArr) {
        return new r2(bArr, 0, bArr.length);
    }

    public static int zzt(zziy zziyVar) {
        int zzd = zziyVar.zzd();
        return zzA(zzd) + zzd;
    }

    public static int zzv(int i) {
        if (i >= 0) {
            return zzA(i);
        }
        return 10;
    }

    public static int zzw(zzkm zzkmVar) {
        int zza = zzkmVar.zza();
        return zzA(zza) + zza;
    }

    public static int zzy(String str) {
        int length;
        try {
            length = y4.c(str);
        } catch (x4 unused) {
            length = str.getBytes(zzkh.f8963a).length;
        }
        return zzA(length) + length;
    }

    public static int zzz(int i) {
        return zzA(i << 3);
    }

    public final void a(String str, x4 x4Var) throws IOException {
        b.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) x4Var);
        byte[] bytes = str.getBytes(zzkh.f8963a);
        try {
            int length = bytes.length;
            zzq(length);
            zzl(bytes, 0, length);
        } catch (zzje e) {
            throw e;
        } catch (IndexOutOfBoundsException e2) {
            throw new zzje(e2);
        }
    }

    public final void zzD() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b2) throws IOException;

    public abstract void zzd(int i, boolean z) throws IOException;

    public abstract void zze(int i, zziy zziyVar) throws IOException;

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
