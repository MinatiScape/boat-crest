package com.google.android.gms.internal.fitness;

import com.google.android.gms.internal.fitness.zzgy;
import com.google.android.gms.internal.fitness.zzgy.zzb;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes8.dex */
public abstract class zzgy<MessageType extends zzgy<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzfo<MessageType, BuilderType> {
    private static Map<Object, zzgy<?, ?>> zzxv = new ConcurrentHashMap();
    public zzjr zzxt = zzjr.zzdp();
    private int zzxu = -1;

    /* loaded from: classes8.dex */
    public static final class a implements zzgv<a> {
        @Override // java.lang.Comparable
        public final /* synthetic */ int compareTo(Object obj) {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.fitness.zzgv
        public final zzij zza(zzij zzijVar, zzik zzikVar) {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.fitness.zzgv
        public final zzkg zzbl() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.fitness.zzgv
        public final zzkj zzbm() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.fitness.zzgv
        public final boolean zzbn() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.fitness.zzgv
        public final boolean zzbo() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.fitness.zzgv
        public final int zzc() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.fitness.zzgv
        public final zziq zza(zziq zziqVar, zziq zziqVar2) {
            throw new NoSuchMethodError();
        }
    }

    /* loaded from: classes8.dex */
    public static class zza<T extends zzgy<T, ?>> extends zzft<T> {
        public zza(T t) {
        }
    }

    /* loaded from: classes8.dex */
    public static abstract class zzd<MessageType extends zzd<MessageType, BuilderType>, BuilderType> extends zzgy<MessageType, BuilderType> implements zzim {
        public q2<a> zzya = q2.n();
    }

    /* loaded from: classes8.dex */
    public enum zze {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f8863a = {1, 2, 3, 4, 5, 6, 7};
        public static final int zzyd = 1;
        public static final int zzye = 2;
        public static final int zzyf = 3;
        public static final int zzyg = 4;
        public static final int zzyh = 5;
        public static final int zzyi = 6;
        public static final int zzyj = 7;

        public static int[] zzcc() {
            return (int[]) f8863a.clone();
        }
    }

    public static <T extends zzgy<?, ?>> T c(Class<T> cls) {
        zzgy<?, ?> zzgyVar = zzxv.get(cls);
        if (zzgyVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzgyVar = zzxv.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzgyVar == null) {
            zzgyVar = (T) ((zzgy) s4.x(cls)).zza(zze.zzyi, (Object) null, (Object) null);
            if (zzgyVar != null) {
                zzxv.put(cls, zzgyVar);
            } else {
                throw new IllegalStateException();
            }
        }
        return (T) zzgyVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object d(Method method, Object obj, Object... objArr) {
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

    public static <T extends zzgy<?, ?>> void zza(Class<T> cls, T t) {
        zzxv.put(cls, t);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.internal.fitness.zzhg, com.google.android.gms.internal.fitness.u2] */
    public static zzhg zzbq() {
        return u2.c();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.internal.fitness.d3, com.google.android.gms.internal.fitness.zzhi] */
    public static zzhi zzbr() {
        return d3.c();
    }

    public static <E> zzhh<E> zzbs() {
        return v3.c();
    }

    @Override // com.google.android.gms.internal.fitness.zzfo
    public final int a() {
        return this.zzxu;
    }

    @Override // com.google.android.gms.internal.fitness.zzfo
    public final void b(int i) {
        this.zzxu = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return t3.a().c(this).b(this, (zzgy) obj);
        }
        return false;
    }

    public int hashCode() {
        int i = this.zztu;
        if (i != 0) {
            return i;
        }
        int a2 = t3.a().c(this).a(this);
        this.zztu = a2;
        return a2;
    }

    @Override // com.google.android.gms.internal.fitness.zzim
    public final boolean isInitialized() {
        return zza(this, true);
    }

    public String toString() {
        return m3.a(this, super.toString());
    }

    public abstract Object zza(int i, Object obj, Object obj2);

    @Override // com.google.android.gms.internal.fitness.zzik
    public final void zzb(zzgk zzgkVar) throws IOException {
        t3.a().c(this).e(this, l2.o(zzgkVar));
    }

    @Override // com.google.android.gms.internal.fitness.zzik
    public final int zzbp() {
        if (this.zzxu == -1) {
            this.zzxu = t3.a().c(this).d(this);
        }
        return this.zzxu;
    }

    @Override // com.google.android.gms.internal.fitness.zzik
    public final /* synthetic */ zzij zzbt() {
        zzb zzbVar = (zzb) zza(zze.zzyh, (Object) null, (Object) null);
        zzbVar.zza((zzb) this);
        return zzbVar;
    }

    @Override // com.google.android.gms.internal.fitness.zzim
    public final /* synthetic */ zzik zzbu() {
        return (zzgy) zza(zze.zzyi, (Object) null, (Object) null);
    }

    public static Object zza(zzik zzikVar, String str, Object[] objArr) {
        return new u3(zzikVar, str, objArr);
    }

    public static final <T extends zzgy<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zze.zzyd, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzl = t3.a().c(t).zzl(t);
        if (z) {
            t.zza(zze.zzye, zzl ? t : null, null);
        }
        return zzl;
    }

    /* loaded from: classes8.dex */
    public static abstract class zzb<MessageType extends zzgy<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzfr<MessageType, BuilderType> {
        public final MessageType h;
        public MessageType i;
        public boolean j = false;

        public zzb(MessageType messagetype) {
            this.h = messagetype;
            this.i = (MessageType) messagetype.zza(zze.zzyg, null, null);
        }

        public static void a(MessageType messagetype, MessageType messagetype2) {
            t3.a().c(messagetype).c(messagetype, messagetype2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.fitness.zzfr
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zzb zzbVar = (zzb) this.h.zza(zze.zzyh, null, null);
            zzbVar.zza((zzb) ((zzgy) zzbz()));
            return zzbVar;
        }

        @Override // com.google.android.gms.internal.fitness.zzim
        public final boolean isInitialized() {
            return zzgy.zza(this.i, false);
        }

        public final BuilderType zza(MessageType messagetype) {
            if (this.j) {
                zzbw();
                this.j = false;
            }
            a(this.i, messagetype);
            return this;
        }

        @Override // com.google.android.gms.internal.fitness.zzfr
        public final /* synthetic */ zzfr zzap() {
            return (zzb) clone();
        }

        @Override // com.google.android.gms.internal.fitness.zzim
        public final /* synthetic */ zzik zzbu() {
            return this.h;
        }

        public void zzbw() {
            MessageType messagetype = (MessageType) this.i.zza(zze.zzyg, null, null);
            a(messagetype, this.i);
            this.i = messagetype;
        }

        @Override // com.google.android.gms.internal.fitness.zzij
        /* renamed from: zzbx */
        public MessageType zzbz() {
            if (this.j) {
                return this.i;
            }
            MessageType messagetype = this.i;
            t3.a().c(messagetype).zze(messagetype);
            this.j = true;
            return this.i;
        }

        @Override // com.google.android.gms.internal.fitness.zzij
        /* renamed from: zzby */
        public final MessageType zzca() {
            MessageType messagetype = (MessageType) zzbz();
            if (messagetype.isInitialized()) {
                return messagetype;
            }
            throw new zzjp(messagetype);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.fitness.zzfr
        public final /* synthetic */ zzfr zza(zzfo zzfoVar) {
            return zza((zzb<MessageType, BuilderType>) ((zzgy) zzfoVar));
        }
    }
}
