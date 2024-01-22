package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzut;
import com.google.android.gms.internal.gtm.zzuz;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes8.dex */
public abstract class zzuz<MessageType extends zzuz<MessageType, BuilderType>, BuilderType extends zzut<MessageType, BuilderType>> extends zzsh<MessageType, BuilderType> {
    private static final Map<Object, zzuz<?, ?>> zza = new ConcurrentHashMap();
    public zzxp zzc = zzxp.zzc();
    public int zzd = -1;

    public static <ContainingType extends zzwk, Type> zzux<ContainingType, Type> zzaa(ContainingType containingtype, Type type, zzwk zzwkVar, zzvc zzvcVar, int i, zzye zzyeVar, Class cls) {
        return new zzux<>(containingtype, type, zzwkVar, new zzuw(zzvcVar, i, zzyeVar, false, false), cls);
    }

    public static <T extends zzuz> T zzab(Class<T> cls) {
        Map<Object, zzuz<?, ?>> map = zza;
        zzuz<?, ?> zzuzVar = map.get(cls);
        if (zzuzVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzuzVar = map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzuzVar == null) {
            zzuzVar = (zzuz) ((zzuz) zzxy.zze(cls)).zzb(6, null, null);
            if (zzuzVar != null) {
                map.put(cls, zzuzVar);
            } else {
                throw new IllegalStateException();
            }
        }
        return zzuzVar;
    }

    public static <T extends zzuz<T, ?>> T zzac(T t, InputStream inputStream, zzuj zzujVar) throws zzvk {
        zzth zzthVar = new zzth(inputStream, 4096, null);
        T t2 = (T) t.zzb(4, null, null);
        try {
            zzwx zzb = zzwt.zza().zzb(t2.getClass());
            zzb.zzh(t2, zztk.zzp(zzthVar), zzujVar);
            zzb.zzf(t2);
            zzc(t2);
            return t2;
        } catch (zzvk e) {
            e = e;
            if (e.zzl()) {
                e = new zzvk(e);
            }
            e.zzh(t2);
            throw e;
        } catch (IOException e2) {
            if (e2.getCause() instanceof zzvk) {
                throw ((zzvk) e2.getCause());
            }
            zzvk zzvkVar = new zzvk(e2);
            zzvkVar.zzh(t2);
            throw zzvkVar;
        } catch (RuntimeException e3) {
            if (e3.getCause() instanceof zzvk) {
                throw ((zzvk) e3.getCause());
            }
            throw e3;
        }
    }

    public static <T extends zzuz<T, ?>> T zzad(T t, byte[] bArr, zzuj zzujVar) throws zzvk {
        T t2 = (T) zzae(t, bArr, 0, bArr.length, zzujVar);
        zzc(t2);
        return t2;
    }

    public static <T extends zzuz<T, ?>> T zzae(T t, byte[] bArr, int i, int i2, zzuj zzujVar) throws zzvk {
        T t2 = (T) t.zzb(4, null, null);
        try {
            zzwx zzb = zzwt.zza().zzb(t2.getClass());
            zzb.zzi(t2, bArr, 0, i2, new zzsl(zzujVar));
            zzb.zzf(t2);
            if (t2.zzb == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (zzvk e) {
            e = e;
            if (e.zzl()) {
                e = new zzvk(e);
            }
            e.zzh(t2);
            throw e;
        } catch (IOException e2) {
            if (e2.getCause() instanceof zzvk) {
                throw ((zzvk) e2.getCause());
            }
            zzvk zzvkVar = new zzvk(e2);
            zzvkVar.zzh(t2);
            throw zzvkVar;
        } catch (IndexOutOfBoundsException unused) {
            zzvk zzj = zzvk.zzj();
            zzj.zzh(t2);
            throw zzj;
        }
    }

    public static zzve zzaf() {
        return zzva.zzf();
    }

    public static <E> zzvh<E> zzag() {
        return zzwu.zze();
    }

    public static <E> zzvh<E> zzah(zzvh<E> zzvhVar) {
        int size = zzvhVar.size();
        return zzvhVar.zzd(size == 0 ? 10 : size + size);
    }

    public static Object zzai(Method method, Object obj, Object... objArr) {
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

    public static Object zzaj(zzwk zzwkVar, String str, Object[] objArr) {
        return new zzwv(zzwkVar, str, objArr);
    }

    public static <T extends zzuz> void zzak(Class<T> cls, T t) {
        zza.put(cls, t);
    }

    public static <T extends zzuz<T, ?>> T zzc(T t) throws zzvk {
        if (t == null || t.zzas()) {
            return t;
        }
        zzvk zzvkVar = new zzvk(new zzxn(t).getMessage());
        zzvkVar.zzh(t);
        throw zzvkVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzwt.zza().zzb(getClass()).zzj(this, (zzuz) obj);
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        if (i != 0) {
            return i;
        }
        int zzb = zzwt.zza().zzb(getClass()).zzb(this);
        this.zzb = zzb;
        return zzb;
    }

    public final String toString() {
        return zzwm.zza(this, super.toString());
    }

    @Override // com.google.android.gms.internal.gtm.zzsh
    public final int zzQ() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.gtm.zzsh
    public final void zzT(int i) {
        this.zzd = i;
    }

    @Override // com.google.android.gms.internal.gtm.zzwk
    public final int zzX() {
        int i = this.zzd;
        if (i == -1) {
            int zza2 = zzwt.zza().zzb(getClass()).zza(this);
            this.zzd = zza2;
            return zza2;
        }
        return i;
    }

    public final <MessageType extends zzuz<MessageType, BuilderType>, BuilderType extends zzut<MessageType, BuilderType>> BuilderType zzY() {
        return (BuilderType) zzb(5, null, null);
    }

    public final BuilderType zzZ() {
        BuilderType buildertype = (BuilderType) zzb(5, null, null);
        buildertype.zzz(this);
        return buildertype;
    }

    @Override // com.google.android.gms.internal.gtm.zzwk
    public final /* bridge */ /* synthetic */ zzwj zzao() {
        return (zzut) zzb(5, null, null);
    }

    @Override // com.google.android.gms.internal.gtm.zzwk
    public final /* bridge */ /* synthetic */ zzwj zzap() {
        zzut zzutVar = (zzut) zzb(5, null, null);
        zzutVar.zzz(this);
        return zzutVar;
    }

    @Override // com.google.android.gms.internal.gtm.zzwk
    public final void zzaq(zzto zztoVar) throws IOException {
        zzwt.zza().zzb(getClass()).zzn(this, zztp.zza(zztoVar));
    }

    @Override // com.google.android.gms.internal.gtm.zzwl
    public final /* bridge */ /* synthetic */ zzwk zzar() {
        return (zzuz) zzb(6, null, null);
    }

    @Override // com.google.android.gms.internal.gtm.zzwl
    public final boolean zzas() {
        byte byteValue = ((Byte) zzb(1, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzk = zzwt.zza().zzb(getClass()).zzk(this);
        zzb(2, true != zzk ? null : this, null);
        return zzk;
    }

    public abstract Object zzb(int i, Object obj, Object obj2);
}
