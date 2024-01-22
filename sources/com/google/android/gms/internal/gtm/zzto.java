package com.google.android.gms.internal.gtm;

import com.htsmart.wristband2.WristbandManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes8.dex */
public abstract class zzto extends zzss {
    public static final Logger zzb = Logger.getLogger(zzto.class.getName());
    public static final boolean zzc = zzxy.zzx();
    public zztp zza;

    public zzto() {
    }

    public /* synthetic */ zzto(zztn zztnVar) {
    }

    public static int zzA(zzwk zzwkVar, zzwx zzwxVar) {
        zzsh zzshVar = (zzsh) zzwkVar;
        int zzQ = zzshVar.zzQ();
        if (zzQ == -1) {
            zzQ = zzwxVar.zza(zzshVar);
            zzshVar.zzT(zzQ);
        }
        return zzD(zzQ) + zzQ;
    }

    public static int zzB(String str) {
        int length;
        try {
            length = zzyd.zzc(str);
        } catch (zzyc unused) {
            length = str.getBytes(zzvi.zza).length;
        }
        return zzD(length) + length;
    }

    public static int zzC(int i) {
        return zzD(i << 3);
    }

    public static int zzD(int i) {
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

    public static int zzE(long j) {
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

    public static zzto zzF(byte[] bArr) {
        return new zztl(bArr, 0, bArr.length);
    }

    public static int zzt(byte[] bArr) {
        int length = bArr.length;
        return zzD(length) + length;
    }

    public static int zzu(zztd zztdVar) {
        int zzd = zztdVar.zzd();
        return zzD(zzd) + zzd;
    }

    @Deprecated
    public static int zzv(int i, zzwk zzwkVar, zzwx zzwxVar) {
        int zzD = zzD(i << 3);
        int i2 = zzD + zzD;
        zzsh zzshVar = (zzsh) zzwkVar;
        int zzQ = zzshVar.zzQ();
        if (zzQ == -1) {
            zzQ = zzwxVar.zza(zzshVar);
            zzshVar.zzT(zzQ);
        }
        return i2 + zzQ;
    }

    @Deprecated
    public static int zzw(zzwk zzwkVar) {
        return zzwkVar.zzX();
    }

    public static int zzx(int i) {
        if (i >= 0) {
            return zzD(i);
        }
        return 10;
    }

    public static int zzy(zzvq zzvqVar) {
        int zza = zzvqVar.zza();
        return zzD(zza) + zza;
    }

    public static int zzz(zzwk zzwkVar) {
        int zzX = zzwkVar.zzX();
        return zzD(zzX) + zzX;
    }

    public final void zzG() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public final void zzH(String str, zzyc zzycVar) throws IOException {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzycVar);
        byte[] bytes = str.getBytes(zzvi.zza);
        try {
            int length = bytes.length;
            zzq(length);
            zzl(bytes, 0, length);
        } catch (zztm e) {
            throw e;
        } catch (IndexOutOfBoundsException e2) {
            throw new zztm(e2);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzd(int i, boolean z) throws IOException;

    public abstract void zze(int i, zztd zztdVar) throws IOException;

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
