package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zzwz;
import com.google.android.gms.internal.firebase_ml.zzwz.zzb;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes7.dex */
public abstract class zzwz<MessageType extends zzwz<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzvl<MessageType, BuilderType> {
    private static Map<Object, zzwz<?, ?>> zzcll = new ConcurrentHashMap();
    public zzzz zzclj = zzzz.zzwz();
    private int zzclk = -1;

    /* loaded from: classes7.dex */
    public static final class a implements zzwt<a> {
        @Override // java.lang.Comparable
        public final /* synthetic */ int compareTo(Object obj) {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwt
        public final zzyn zza(zzyn zzynVar, zzyk zzykVar) {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwt
        public final int zzd() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwt
        public final zzaan zzui() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwt
        public final zzaaq zzuj() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwt
        public final boolean zzuk() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwt
        public final boolean zzul() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwt
        public final zzyt zza(zzyt zzytVar, zzyt zzytVar2) {
            throw new NoSuchMethodError();
        }
    }

    /* loaded from: classes7.dex */
    public static class zza<T extends zzwz<T, ?>> extends zzvm<T> {
        public zza(T t) {
        }
    }

    /* loaded from: classes7.dex */
    public static abstract class zzc<MessageType extends zzc<MessageType, BuilderType>, BuilderType extends zzd<MessageType, BuilderType>> extends zzwz<MessageType, BuilderType> implements zzym {
        public p6<a> zzclq = p6.r();

        public final p6<a> f() {
            if (this.zzclq.b()) {
                this.zzclq = (p6) this.zzclq.clone();
            }
            return this.zzclq;
        }
    }

    /* loaded from: classes7.dex */
    public static abstract class zzd<MessageType extends zzc<MessageType, BuilderType>, BuilderType extends zzd<MessageType, BuilderType>> extends zzb<MessageType, BuilderType> implements zzym {
        public zzd(MessageType messagetype) {
            super(messagetype);
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz.zzb
        public void zzux() {
            super.zzux();
            MessageType messagetype = this.zzclo;
            ((zzc) messagetype).zzclq = (p6) ((zzc) messagetype).zzclq.clone();
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz.zzb
        public /* synthetic */ zzwz zzuy() {
            return (zzc) zzva();
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzwz.zzb, com.google.android.gms.internal.firebase_ml.zzyn
        public /* synthetic */ zzyk zzva() {
            if (this.zzclp) {
                return (zzc) this.zzclo;
            }
            ((zzc) this.zzclo).zzclq.q();
            return (zzc) super.zzva();
        }
    }

    /* loaded from: classes7.dex */
    public static class zze<ContainingType extends zzyk, Type> extends zzwm<ContainingType, Type> {
    }

    /* loaded from: classes7.dex */
    public enum zzg {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f8813a = {1, 2, 3, 4, 5, 6, 7};
        public static final int zzclt = 1;
        public static final int zzclu = 2;
        public static final int zzclv = 3;
        public static final int zzclw = 4;
        public static final int zzclx = 5;
        public static final int zzcly = 6;
        public static final int zzclz = 7;
        public static final int zzcmb = 1;
        public static final int zzcmc = 2;
        public static final int zzcme = 1;
        public static final int zzcmf = 2;

        public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0() {
            return (int[]) f8813a.clone();
        }
    }

    public static <T extends zzwz<T, ?>> T c(T t, byte[] bArr, int i, int i2, zzwo zzwoVar) throws zzxk {
        T t2 = (T) t.zza(zzg.zzclw, null, null);
        try {
            c8 a2 = x7.c().a(t2);
            a2.f(t2, bArr, 0, i2, new v5(zzwoVar));
            a2.e(t2);
            if (t2.zzchd == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof zzxk) {
                throw ((zzxk) e.getCause());
            }
            throw new zzxk(e.getMessage()).zzg(t2);
        } catch (IndexOutOfBoundsException unused) {
            throw zzxk.zzve().zzg(t2);
        }
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

    public static <T extends zzwz<?, ?>> T e(Class<T> cls) {
        zzwz<?, ?> zzwzVar = zzcll.get(cls);
        if (zzwzVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzwzVar = zzcll.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzwzVar == null) {
            zzwzVar = (T) ((zzwz) b.v(cls)).zza(zzg.zzcly, (Object) null, (Object) null);
            if (zzwzVar != null) {
                zzcll.put(cls, zzwzVar);
            } else {
                throw new IllegalStateException();
            }
        }
        return (T) zzwzVar;
    }

    public static <T extends zzwz<?, ?>> void zza(Class<T> cls, T t) {
        zzcll.put(cls, t);
    }

    public static zzxg zzup() {
        return w6.c();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.internal.firebase_ml.d7, com.google.android.gms.internal.firebase_ml.zzxi] */
    public static zzxi zzuq() {
        return d7.d();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.internal.firebase_ml.t6, com.google.android.gms.internal.firebase_ml.zzxh] */
    public static zzxh zzur() {
        return t6.d();
    }

    public static <E> zzxl<E> zzus() {
        return w7.c();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvl
    public final void a(int i) {
        this.zzclk = i;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvl
    public final int b() {
        return this.zzclk;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return x7.c().a(this).b(this, (zzwz) obj);
        }
        return false;
    }

    public int hashCode() {
        int i = this.zzchd;
        if (i != 0) {
            return i;
        }
        int a2 = x7.c().a(this).a(this);
        this.zzchd = a2;
        return a2;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzym
    public final boolean isInitialized() {
        return zza(this, true);
    }

    public String toString() {
        return p7.a(this, super.toString());
    }

    public abstract Object zza(int i, Object obj, Object obj2);

    @Override // com.google.android.gms.internal.firebase_ml.zzyk
    public final void zzb(zzwi zzwiVar) throws IOException {
        x7.c().a(this).g(this, l6.p(zzwiVar));
    }

    public final <MessageType extends zzwz<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> BuilderType zzun() {
        return (BuilderType) zza(zzg.zzclx, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzyk
    public final int zzuo() {
        if (this.zzclk == -1) {
            this.zzclk = x7.c().a(this).i(this);
        }
        return this.zzclk;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzyk
    public final /* synthetic */ zzyn zzut() {
        zzb zzbVar = (zzb) zza(zzg.zzclx, (Object) null, (Object) null);
        zzbVar.zza((zzb) this);
        return zzbVar;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzyk
    public final /* synthetic */ zzyn zzuu() {
        return (zzb) zza(zzg.zzclx, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzym
    public final /* synthetic */ zzyk zzuv() {
        return (zzwz) zza(zzg.zzcly, (Object) null, (Object) null);
    }

    public static Object zza(zzyk zzykVar, String str, Object[] objArr) {
        return new z7(zzykVar, str, objArr);
    }

    public static final <T extends zzwz<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zzg.zzclt, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean j = x7.c().a(t).j(t);
        if (z) {
            t.zza(zzg.zzclu, j ? t : null, null);
        }
        return j;
    }

    /* loaded from: classes7.dex */
    public static abstract class zzb<MessageType extends zzwz<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzvk<MessageType, BuilderType> {
        public final MessageType h;
        public MessageType zzclo;
        public boolean zzclp = false;

        public zzb(MessageType messagetype) {
            this.h = messagetype;
            this.zzclo = (MessageType) messagetype.zza(zzg.zzclw, null, null);
        }

        public static void a(MessageType messagetype, MessageType messagetype2) {
            x7.c().a(messagetype).h(messagetype, messagetype2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.firebase_ml.zzvk
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zzb zzbVar = (zzb) this.h.zza(zzg.zzclx, null, null);
            zzbVar.zza((zzb) ((zzwz) zzva()));
            return zzbVar;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzym
        public final boolean isInitialized() {
            return zzwz.zza(this.zzclo, false);
        }

        public final BuilderType zza(MessageType messagetype) {
            if (this.zzclp) {
                zzux();
                this.zzclp = false;
            }
            a(this.zzclo, messagetype);
            return this;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzvk
        public final /* synthetic */ zzvk zztf() {
            return (zzb) clone();
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzym
        public final /* synthetic */ zzyk zzuv() {
            return this.h;
        }

        public void zzux() {
            MessageType messagetype = (MessageType) this.zzclo.zza(zzg.zzclw, null, null);
            a(messagetype, this.zzclo);
            this.zzclo = messagetype;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzyn
        /* renamed from: zzuy */
        public MessageType zzva() {
            if (this.zzclp) {
                return this.zzclo;
            }
            MessageType messagetype = this.zzclo;
            x7.c().a(messagetype).e(messagetype);
            this.zzclp = true;
            return this.zzclo;
        }

        @Override // com.google.android.gms.internal.firebase_ml.zzyn
        /* renamed from: zzuz */
        public final MessageType zzvb() {
            MessageType messagetype = (MessageType) zzva();
            if (messagetype.isInitialized()) {
                return messagetype;
            }
            throw new zzzx(messagetype);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.firebase_ml.zzvk
        public final /* synthetic */ zzvk zza(zzvl zzvlVar) {
            return zza((zzb<MessageType, BuilderType>) ((zzwz) zzvlVar));
        }
    }

    public static zzxg zza(zzxg zzxgVar) {
        int size = zzxgVar.size();
        return zzxgVar.zzdr(size == 0 ? 10 : size << 1);
    }

    public static <E> zzxl<E> zza(zzxl<E> zzxlVar) {
        int size = zzxlVar.size();
        return zzxlVar.zzcv(size == 0 ? 10 : size << 1);
    }

    public static <T extends zzwz<T, ?>> T zza(T t, byte[] bArr, zzwo zzwoVar) throws zzxk {
        T t2 = (T) c(t, bArr, 0, bArr.length, zzwoVar);
        if (t2 == null || t2.isInitialized()) {
            return t2;
        }
        throw new zzxk(new zzzx(t2).getMessage()).zzg(t2);
    }
}
