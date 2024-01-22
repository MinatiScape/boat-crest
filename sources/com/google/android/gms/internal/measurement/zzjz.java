package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzjv;
import com.google.android.gms.internal.measurement.zzjz;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes8.dex */
public abstract class zzjz<MessageType extends zzjz<MessageType, BuilderType>, BuilderType extends zzjv<MessageType, BuilderType>> extends zzih<MessageType, BuilderType> {
    private static final Map<Object, zzjz<?, ?>> zza = new ConcurrentHashMap();
    public zzmj zzc = zzmj.zzc();
    public int zzd = -1;

    public static Object c(Method method, Object obj, Object... objArr) {
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

    public static <T extends zzjz> T d(Class<T> cls) {
        Map<Object, zzjz<?, ?>> map = zza;
        zzjz<?, ?> zzjzVar = map.get(cls);
        if (zzjzVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzjzVar = map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzjzVar == null) {
            zzjzVar = (zzjz) ((zzjz) t4.j(cls)).zzl(6, null, null);
            if (zzjzVar != null) {
                map.put(cls, zzjzVar);
            } else {
                throw new IllegalStateException();
            }
        }
        return zzjzVar;
    }

    public static <E> zzkg<E> zzbA() {
        return v3.a();
    }

    public static <E> zzkg<E> zzbB(zzkg<E> zzkgVar) {
        int size = zzkgVar.size();
        return zzkgVar.zzd(size == 0 ? 10 : size + size);
    }

    public static Object zzbF(zzlg zzlgVar, String str, Object[] objArr) {
        return new w3(zzlgVar, str, objArr);
    }

    public static <T extends zzjz> void zzbG(Class<T> cls, T t) {
        zza.put(cls, t);
    }

    public static zzke zzbx() {
        return c3.b();
    }

    public static zzkf zzby() {
        return g3.a();
    }

    public static zzkf zzbz(zzkf zzkfVar) {
        int size = zzkfVar.size();
        return zzkfVar.zze(size == 0 ? 10 : size + size);
    }

    @Override // com.google.android.gms.internal.measurement.zzih
    public final int a() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.measurement.zzih
    public final void b(int i) {
        this.zzd = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return u3.a().b(getClass()).d(this, (zzjz) obj);
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        if (i != 0) {
            return i;
        }
        int zzb = u3.a().b(getClass()).zzb(this);
        this.zzb = zzb;
        return zzb;
    }

    public final String toString() {
        return o3.a(this, super.toString());
    }

    @Override // com.google.android.gms.internal.measurement.zzlg
    public final /* bridge */ /* synthetic */ zzlf zzbC() {
        return (zzjv) zzl(5, null, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzlg
    public final /* bridge */ /* synthetic */ zzlf zzbD() {
        zzjv zzjvVar = (zzjv) zzl(5, null, null);
        zzjvVar.zzay(this);
        return zzjvVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzlg
    public final void zzbH(zzjg zzjgVar) throws IOException {
        u3.a().b(getClass()).c(this, s2.l(zzjgVar));
    }

    @Override // com.google.android.gms.internal.measurement.zzlh
    public final /* bridge */ /* synthetic */ zzlg zzbL() {
        return (zzjz) zzl(6, null, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzlg
    public final int zzbt() {
        int i = this.zzd;
        if (i == -1) {
            int zza2 = u3.a().b(getClass()).zza(this);
            this.zzd = zza2;
            return zza2;
        }
        return i;
    }

    public final <MessageType extends zzjz<MessageType, BuilderType>, BuilderType extends zzjv<MessageType, BuilderType>> BuilderType zzbu() {
        return (BuilderType) zzl(5, null, null);
    }

    public final BuilderType zzbv() {
        BuilderType buildertype = (BuilderType) zzl(5, null, null);
        buildertype.zzay(this);
        return buildertype;
    }

    public abstract Object zzl(int i, Object obj, Object obj2);
}
