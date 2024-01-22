package com.google.android.recaptcha.internal;

import com.htsmart.wristband2.WristbandManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes10.dex */
public abstract class zzft extends zzey {
    public static final /* synthetic */ int zzb = 0;
    private static final Logger zzc = Logger.getLogger(zzft.class.getName());
    private static final boolean zzd = zzkg.zzx();
    public zzfu zza;

    private zzft() {
    }

    public /* synthetic */ zzft(zzfs zzfsVar) {
    }

    public static zzft zzA(byte[] bArr, int i, int i2) {
        return new zzfq(bArr, 0, i2);
    }

    @Deprecated
    public static int zzt(int i, zzip zzipVar, zzjc zzjcVar) {
        int zza = ((zzer) zzipVar).zza(zzjcVar);
        int zzy = zzy(i << 3);
        return zzy + zzy + zza;
    }

    public static int zzu(int i) {
        if (i >= 0) {
            return zzy(i);
        }
        return 10;
    }

    public static int zzv(zzip zzipVar) {
        int zzn = zzipVar.zzn();
        return zzy(zzn) + zzn;
    }

    public static int zzw(zzip zzipVar, zzjc zzjcVar) {
        int zza = ((zzer) zzipVar).zza(zzjcVar);
        return zzy(zza) + zza;
    }

    public static int zzx(String str) {
        int length;
        try {
            length = zzkl.zzc(str);
        } catch (zzkk unused) {
            length = str.getBytes(zzhn.zzb).length;
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

    public final void zzB() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public final void zzC(String str, zzkk zzkkVar) throws IOException {
        zzc.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzkkVar);
        byte[] bytes = str.getBytes(zzhn.zzb);
        try {
            int length = bytes.length;
            zzq(length);
            zzl(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzfr(e);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzd(int i, boolean z) throws IOException;

    public abstract void zze(int i, zzfi zzfiVar) throws IOException;

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
