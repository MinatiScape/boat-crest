package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.internal.zzgz;
import com.google.android.recaptcha.internal.zzhf;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes10.dex */
public abstract class zzhf<MessageType extends zzhf<MessageType, BuilderType>, BuilderType extends zzgz<MessageType, BuilderType>> extends zzer<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    private int zzd = -1;
    public zzjx zzc = zzjx.zzc();

    public static void zzC(Class cls, zzhf zzhfVar) {
        zzhfVar.zzB();
        zzb.put(cls, zzhfVar);
    }

    public static final boolean zzE(zzhf zzhfVar, boolean z) {
        byte byteValue = ((Byte) zzhfVar.zzh(1, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzl = zziy.zza().zzb(zzhfVar.getClass()).zzl(zzhfVar);
        if (z) {
            zzhfVar.zzh(2, true != zzl ? null : zzhfVar, null);
        }
        return zzl;
    }

    private final int zzf(zzjc zzjcVar) {
        if (zzjcVar == null) {
            return zziy.zza().zzb(getClass()).zza(this);
        }
        return zzjcVar.zza(this);
    }

    private static zzhf zzg(zzhf zzhfVar) throws zzhp {
        if (zzhfVar == null || zzhfVar.zzo()) {
            return zzhfVar;
        }
        zzhp zza = new zzjv(zzhfVar).zza();
        zza.zzh(zzhfVar);
        throw zza;
    }

    private static zzhf zzi(zzhf zzhfVar, byte[] bArr, int i, int i2, zzgq zzgqVar) throws zzhp {
        zzhf zzs = zzhfVar.zzs();
        try {
            zzjc zzb2 = zziy.zza().zzb(zzs.getClass());
            zzb2.zzi(zzs, bArr, 0, i2, new zzev(zzgqVar));
            zzb2.zzf(zzs);
            return zzs;
        } catch (zzhp e) {
            e = e;
            if (e.zzl()) {
                e = new zzhp(e);
            }
            e.zzh(zzs);
            throw e;
        } catch (zzjv e2) {
            zzhp zza = e2.zza();
            zza.zzh(zzs);
            throw zza;
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzhp) {
                throw ((zzhp) e3.getCause());
            }
            zzhp zzhpVar = new zzhp(e3);
            zzhpVar.zzh(zzs);
            throw zzhpVar;
        } catch (IndexOutOfBoundsException unused) {
            zzhp zzj = zzhp.zzj();
            zzj.zzh(zzs);
            throw zzj;
        }
    }

    public static zzhd zzq(zzip zzipVar, Object obj, zzip zzipVar2, zzhi zzhiVar, int i, zzkm zzkmVar, Class cls) {
        return new zzhd(zzipVar, "", null, new zzhc(null, i, zzkmVar, false, false), cls);
    }

    public static zzhf zzr(Class cls) {
        Map map = zzb;
        zzhf zzhfVar = (zzhf) map.get(cls);
        if (zzhfVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzhfVar = (zzhf) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzhfVar == null) {
            zzhfVar = (zzhf) ((zzhf) zzkg.zze(cls)).zzh(6, null, null);
            if (zzhfVar != null) {
                map.put(cls, zzhfVar);
            } else {
                throw new IllegalStateException();
            }
        }
        return zzhfVar;
    }

    public static zzhf zzt(zzhf zzhfVar, InputStream inputStream) throws zzhp {
        zzfo zzfmVar;
        int i = zzfo.zzd;
        if (inputStream == null) {
            byte[] bArr = zzhn.zzd;
            int length = bArr.length;
            zzfmVar = zzfo.zzH(bArr, 0, 0, false);
        } else {
            zzfmVar = new zzfm(inputStream, 4096, null);
        }
        zzgq zzgqVar = zzgq.zza;
        zzhf zzs = zzhfVar.zzs();
        try {
            zzjc zzb2 = zziy.zza().zzb(zzs.getClass());
            zzb2.zzh(zzs, zzfp.zzq(zzfmVar), zzgqVar);
            zzb2.zzf(zzs);
            zzg(zzs);
            return zzs;
        } catch (zzhp e) {
            e = e;
            if (e.zzl()) {
                e = new zzhp(e);
            }
            e.zzh(zzs);
            throw e;
        } catch (zzjv e2) {
            zzhp zza = e2.zza();
            zza.zzh(zzs);
            throw zza;
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzhp) {
                throw ((zzhp) e3.getCause());
            }
            zzhp zzhpVar = new zzhp(e3);
            zzhpVar.zzh(zzs);
            throw zzhpVar;
        } catch (RuntimeException e4) {
            if (e4.getCause() instanceof zzhp) {
                throw ((zzhp) e4.getCause());
            }
            throw e4;
        }
    }

    public static zzhf zzu(zzhf zzhfVar, byte[] bArr) throws zzhp {
        zzhf zzi = zzi(zzhfVar, bArr, 0, bArr.length, zzgq.zza);
        zzg(zzi);
        return zzi;
    }

    public static zzhk zzv() {
        return zzhg.zzf();
    }

    public static zzhm zzw() {
        return zziz.zze();
    }

    public static zzhm zzx(zzhm zzhmVar) {
        int size = zzhmVar.size();
        return zzhmVar.zzd(size == 0 ? 10 : size + size);
    }

    public static Object zzy(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (!(cause instanceof RuntimeException)) {
                if (cause instanceof Error) {
                    throw ((Error) cause);
                }
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
            throw ((RuntimeException) cause);
        }
    }

    public static Object zzz(zzip zzipVar, String str, Object[] objArr) {
        return new zzja(zzipVar, str, objArr);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zziy.zza().zzb(getClass()).zzk(this, (zzhf) obj);
        }
        return false;
    }

    public final int hashCode() {
        if (zzF()) {
            return zzm();
        }
        int i = this.zza;
        if (i == 0) {
            int zzm = zzm();
            this.zza = zzm;
            return zzm;
        }
        return i;
    }

    public final String toString() {
        return zzir.zza(this, super.toString());
    }

    public final void zzA() {
        zziy.zza().zzb(getClass()).zzf(this);
        zzB();
    }

    public final void zzB() {
        this.zzd &= Integer.MAX_VALUE;
    }

    public final void zzD(int i) {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    public final boolean zzF() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    @Override // com.google.android.recaptcha.internal.zzip
    public final /* synthetic */ zzio zzV() {
        return (zzgz) zzh(5, null, null);
    }

    @Override // com.google.android.recaptcha.internal.zzip
    public final /* synthetic */ zzio zzW() {
        zzgz zzgzVar = (zzgz) zzh(5, null, null);
        zzgzVar.zzg(this);
        return zzgzVar;
    }

    @Override // com.google.android.recaptcha.internal.zziq
    public final /* synthetic */ zzip zzX() {
        return (zzhf) zzh(6, null, null);
    }

    @Override // com.google.android.recaptcha.internal.zzer
    public final int zza(zzjc zzjcVar) {
        if (zzF()) {
            int zzf = zzf(zzjcVar);
            if (zzf >= 0) {
                return zzf;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + zzf);
        }
        int i = this.zzd & Integer.MAX_VALUE;
        if (i != Integer.MAX_VALUE) {
            return i;
        }
        int zzf2 = zzf(zzjcVar);
        if (zzf2 >= 0) {
            this.zzd = (this.zzd & Integer.MIN_VALUE) | zzf2;
            return zzf2;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + zzf2);
    }

    @Override // com.google.android.recaptcha.internal.zzip
    public final void zze(zzft zzftVar) throws IOException {
        zziy.zza().zzb(getClass()).zzj(this, zzfu.zza(zzftVar));
    }

    public abstract Object zzh(int i, Object obj, Object obj2);

    final int zzm() {
        return zziy.zza().zzb(getClass()).zzb(this);
    }

    @Override // com.google.android.recaptcha.internal.zzip
    public final int zzn() {
        int i;
        if (zzF()) {
            i = zzf(null);
            if (i < 0) {
                throw new IllegalStateException("serialized size must be non-negative, was " + i);
            }
        } else {
            i = this.zzd & Integer.MAX_VALUE;
            if (i == Integer.MAX_VALUE) {
                i = zzf(null);
                if (i >= 0) {
                    this.zzd = (this.zzd & Integer.MIN_VALUE) | i;
                } else {
                    throw new IllegalStateException("serialized size must be non-negative, was " + i);
                }
            }
        }
        return i;
    }

    @Override // com.google.android.recaptcha.internal.zziq
    public final boolean zzo() {
        return zzE(this, true);
    }

    public final zzgz zzp() {
        return (zzgz) zzh(5, null, null);
    }

    public final zzhf zzs() {
        return (zzhf) zzh(4, null, null);
    }
}
