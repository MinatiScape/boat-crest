package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdx;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes8.dex */
public abstract class zzed<MessageType extends zzed<MessageType, BuilderType>, BuilderType extends zzdx<MessageType, BuilderType>> extends zzck<MessageType, BuilderType> {
    private static final Map zza = new ConcurrentHashMap();
    private int zzd = -1;
    public zzgz zzc = zzgz.zzc();

    public static zzed c(Class cls) {
        Map map = zza;
        zzed zzedVar = (zzed) map.get(cls);
        if (zzedVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzedVar = (zzed) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzedVar == null) {
            zzedVar = (zzed) ((zzed) g2.j(cls)).zzg(6, null, null);
            if (zzedVar != null) {
                map.put(cls, zzedVar);
            } else {
                throw new IllegalStateException();
            }
        }
        return zzedVar;
    }

    public static Object e(Method method, Object obj, Object... objArr) {
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

    public static zzed j(zzed zzedVar, byte[] bArr, int i, int i2, zzdo zzdoVar) throws zzeo {
        zzed d = zzedVar.d();
        try {
            l1 b = d1.a().b(d.getClass());
            b.a(d, bArr, 0, i2, new m(zzdoVar));
            b.zzf(d);
            return d;
        } catch (zzeo e) {
            e.zzf(d);
            throw e;
        } catch (zzgx e2) {
            zzeo zza2 = e2.zza();
            zza2.zzf(d);
            throw zza2;
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzeo) {
                throw ((zzeo) e3.getCause());
            }
            zzeo zzeoVar = new zzeo(e3);
            zzeoVar.zzf(d);
            throw zzeoVar;
        } catch (IndexOutOfBoundsException unused) {
            zzeo zzg = zzeo.zzg();
            zzg.zzf(d);
            throw zzg;
        }
    }

    public static zzeb zzH(zzfo zzfoVar, Object obj, zzfo zzfoVar2, zzeg zzegVar, int i, zzho zzhoVar, Class cls) {
        return new zzeb(zzfoVar, obj, zzfoVar2, new i0(null, i, zzhoVar, false, false), cls);
    }

    public static zzed zzK(zzed zzedVar, byte[] bArr, zzdo zzdoVar) throws zzeo {
        zzed j = j(zzedVar, bArr, 0, bArr.length, zzdoVar);
        if (j == null || j.zzac()) {
            return j;
        }
        zzeo zza2 = new zzgx(j).zza();
        zza2.zzf(j);
        throw zza2;
    }

    public static zzei zzL() {
        return g0.a();
    }

    public static zzei zzM(zzei zzeiVar) {
        int size = zzeiVar.size();
        return zzeiVar.zzf(size == 0 ? 10 : size + size);
    }

    public static zzej zzN() {
        return j0.b();
    }

    public static zzel zzO() {
        return e1.a();
    }

    public static zzel zzP(zzel zzelVar) {
        int size = zzelVar.size();
        return zzelVar.zzd(size == 0 ? 10 : size + size);
    }

    public static Object zzR(zzfo zzfoVar, String str, Object[] objArr) {
        return new f1(zzfoVar, str, objArr);
    }

    public static void zzU(Class cls, zzed zzedVar) {
        zzedVar.f();
        zza.put(cls, zzedVar);
    }

    public static final boolean zzW(zzed zzedVar, boolean z) {
        byte byteValue = ((Byte) zzedVar.zzg(1, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzk = d1.a().b(zzedVar.getClass()).zzk(zzedVar);
        if (z) {
            zzedVar.zzg(2, true != zzk ? null : zzedVar, null);
        }
        return zzk;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzck
    public final int a(l1 l1Var) {
        if (h()) {
            int i = i(l1Var);
            if (i >= 0) {
                return i;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + i);
        }
        int i2 = this.zzd & Integer.MAX_VALUE;
        if (i2 != Integer.MAX_VALUE) {
            return i2;
        }
        int i3 = i(l1Var);
        if (i3 >= 0) {
            this.zzd = (this.zzd & Integer.MIN_VALUE) | i3;
            return i3;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + i3);
    }

    public final int b() {
        return d1.a().b(getClass()).zzb(this);
    }

    public final zzed d() {
        return (zzed) zzg(4, null, null);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return d1.a().b(getClass()).zzj(this, (zzed) obj);
        }
        return false;
    }

    public final void f() {
        this.zzd &= Integer.MAX_VALUE;
    }

    public final void g(int i) {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    public final boolean h() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    public final int hashCode() {
        if (h()) {
            return b();
        }
        int i = this.zzb;
        if (i == 0) {
            int b = b();
            this.zzb = b;
            return b;
        }
        return i;
    }

    public final int i(l1 l1Var) {
        if (l1Var == null) {
            return d1.a().b(getClass()).zza(this);
        }
        return l1Var.zza(this);
    }

    public final String toString() {
        return x0.a(this, super.toString());
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo
    public final int zzE() {
        int i;
        if (h()) {
            i = i(null);
            if (i < 0) {
                throw new IllegalStateException("serialized size must be non-negative, was " + i);
            }
        } else {
            i = this.zzd & Integer.MAX_VALUE;
            if (i == Integer.MAX_VALUE) {
                i = i(null);
                if (i >= 0) {
                    this.zzd = (this.zzd & Integer.MIN_VALUE) | i;
                } else {
                    throw new IllegalStateException("serialized size must be non-negative, was " + i);
                }
            }
        }
        return i;
    }

    public final zzdx zzF() {
        return (zzdx) zzg(5, null, null);
    }

    public final zzdx zzG() {
        zzdx zzdxVar = (zzdx) zzg(5, null, null);
        zzdxVar.zzg(this);
        return zzdxVar;
    }

    public final void zzS() {
        d1.a().b(getClass()).zzf(this);
        f();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo
    public final /* synthetic */ zzfn zzY() {
        return (zzdx) zzg(5, null, null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo
    public final /* synthetic */ zzfn zzZ() {
        zzdx zzdxVar = (zzdx) zzg(5, null, null);
        zzdxVar.zzg(this);
        return zzdxVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo
    public final void zzaa(zzdj zzdjVar) throws IOException {
        d1.a().b(getClass()).b(this, z.d(zzdjVar));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp
    public final /* synthetic */ zzfo zzab() {
        return (zzed) zzg(6, null, null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp
    public final boolean zzac() {
        return zzW(this, true);
    }

    public abstract Object zzg(int i, Object obj, Object obj2);
}
