package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs;
import com.google.android.gms.internal.vision.zzgs.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes10.dex */
public abstract class zzgs<MessageType extends zzgs<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzet<MessageType, BuilderType> {
    private static Map<Object, zzgs<?, ?>> zzwl = new ConcurrentHashMap();
    public zzjm zzwj = zzjm.zzig();
    private int zzwk = -1;

    /* loaded from: classes10.dex */
    public static final class a implements zzgk<a> {
        public final zzka j;
        public final zzgv<?> h = null;
        public final int i = 202056002;
        public final boolean k = true;
        public final boolean l = false;

        public a(zzgv<?> zzgvVar, int i, zzka zzkaVar, boolean z, boolean z2) {
            this.j = zzkaVar;
        }

        @Override // java.lang.Comparable
        public final /* synthetic */ int compareTo(Object obj) {
            return this.i - ((a) obj).i;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.vision.zzgk
        public final zzib zza(zzib zzibVar, zzic zzicVar) {
            return ((zza) zzibVar).zza((zza) ((zzgs) zzicVar));
        }

        @Override // com.google.android.gms.internal.vision.zzgk
        public final int zzag() {
            return this.i;
        }

        @Override // com.google.android.gms.internal.vision.zzgk
        public final zzka zzfs() {
            return this.j;
        }

        @Override // com.google.android.gms.internal.vision.zzgk
        public final zzkd zzft() {
            return this.j.zzip();
        }

        @Override // com.google.android.gms.internal.vision.zzgk
        public final boolean zzfu() {
            return this.k;
        }

        @Override // com.google.android.gms.internal.vision.zzgk
        public final boolean zzfv() {
            return this.l;
        }

        @Override // com.google.android.gms.internal.vision.zzgk
        public final zzih zza(zzih zzihVar, zzih zzihVar2) {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class zzb<MessageType extends zze<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zza<MessageType, BuilderType> implements zzie {
        public zzb(MessageType messagetype) {
            super(messagetype);
        }

        @Override // com.google.android.gms.internal.vision.zzgs.zza
        public void zzfy() {
            super.zzfy();
            MessageType messagetype = this.zzwh;
            ((zze) messagetype).zzwq = (e2) ((zze) messagetype).zzwq.clone();
        }

        @Override // com.google.android.gms.internal.vision.zzgs.zza
        public /* synthetic */ zzgs zzfz() {
            return (zze) zzgb();
        }

        @Override // com.google.android.gms.internal.vision.zzgs.zza, com.google.android.gms.internal.vision.zzib
        public /* synthetic */ zzic zzgb() {
            if (this.zzwi) {
                return (zze) this.zzwh;
            }
            ((zze) this.zzwh).zzwq.p();
            return (zze) super.zzgb();
        }
    }

    /* loaded from: classes10.dex */
    public static class zzc<T extends zzgs<T, ?>> extends zzey<T> {
        public zzc(T t) {
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class zze<MessageType extends zze<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzgs<MessageType, BuilderType> implements zzie {
        public e2<a> zzwq = e2.q();

        public final e2<a> i() {
            if (this.zzwq.b()) {
                this.zzwq = (e2) this.zzwq.clone();
            }
            return this.zzwq;
        }

        /* JADX WARN: Type inference failed for: r1v6, types: [Type, java.util.List, java.util.ArrayList] */
        public final <Type> Type zzc(zzge<MessageType, Type> zzgeVar) {
            zzg c = zzgs.c(zzgeVar);
            if (c.f10023a == ((zzgs) zzgd())) {
                Type type = (Type) this.zzwq.f(c.d);
                if (type == null) {
                    return c.b;
                }
                a aVar = c.d;
                if (aVar.k) {
                    if (aVar.j.zzip() == zzkd.ENUM) {
                        ?? r1 = (Type) new ArrayList();
                        for (Object obj : (List) type) {
                            r1.add(c.a(obj));
                        }
                        return r1;
                    }
                    return type;
                }
                return (Type) c.a(type);
            }
            throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
        }
    }

    /* loaded from: classes10.dex */
    public enum zzf {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10022a = {1, 2, 3, 4, 5, 6, 7};
        public static final int zzwr = 1;
        public static final int zzws = 2;
        public static final int zzwt = 3;
        public static final int zzwu = 4;
        public static final int zzwv = 5;
        public static final int zzww = 6;
        public static final int zzwx = 7;
        public static final int zzwz = 1;
        public static final int zzxa = 2;
        public static final int zzxc = 1;
        public static final int zzxd = 2;

        public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0() {
            return (int[]) f10022a.clone();
        }
    }

    /* loaded from: classes10.dex */
    public static class zzg<ContainingType extends zzic, Type> extends zzge<ContainingType, Type> {

        /* renamed from: a  reason: collision with root package name */
        public final ContainingType f10023a;
        public final Type b;
        public final zzic c;
        public final a d;

        public zzg(ContainingType containingtype, Type type, zzic zzicVar, a aVar, Class cls) {
            if (containingtype != null) {
                if (aVar.j == zzka.zzacc && zzicVar == null) {
                    throw new IllegalArgumentException("Null messageDefaultInstance");
                }
                this.f10023a = containingtype;
                this.b = type;
                this.c = zzicVar;
                this.d = aVar;
                return;
            }
            throw new IllegalArgumentException("Null containingTypeDefaultInstance");
        }

        public final Object a(Object obj) {
            return this.d.j.zzip() == zzkd.ENUM ? this.d.h.zzh(((Integer) obj).intValue()) : obj;
        }
    }

    public static <MessageType extends zze<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>, T> zzg<MessageType, T> c(zzge<MessageType, T> zzgeVar) {
        return (zzg) zzgeVar;
    }

    public static <T extends zzgs<T, ?>> T d(T t, byte[] bArr, int i, int i2, zzgd zzgdVar) throws zzhc {
        T t2 = (T) t.zza(zzf.zzwu, null, null);
        try {
            o3 c = k3.b().c(t2);
            c.g(t2, bArr, 0, i2, new f1(zzgdVar));
            c.e(t2);
            if (t2.zzro == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof zzhc) {
                throw ((zzhc) e.getCause());
            }
            throw new zzhc(e.getMessage()).zzg(t2);
        } catch (IndexOutOfBoundsException unused) {
            throw zzhc.zzgm().zzg(t2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
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

    public static <T extends zzgs<T, ?>> T g(T t) throws zzhc {
        if (t == null || t.isInitialized()) {
            return t;
        }
        throw new zzhc(new zzjk(t).getMessage()).zzg(t);
    }

    public static <T extends zzgs<?, ?>> T h(Class<T> cls) {
        zzgs<?, ?> zzgsVar = zzwl.get(cls);
        if (zzgsVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzgsVar = zzwl.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzgsVar == null) {
            zzgsVar = (T) ((zzgs) j4.r(cls)).zza(zzf.zzww, (Object) null, (Object) null);
            if (zzgsVar != null) {
                zzwl.put(cls, zzgsVar);
            } else {
                throw new IllegalStateException();
            }
        }
        return (T) zzgsVar;
    }

    public static <T extends zzgs<?, ?>> void zza(Class<T> cls, T t) {
        zzwl.put(cls, t);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.internal.vision.zzgx, com.google.android.gms.internal.vision.m2] */
    public static zzgx zzgg() {
        return m2.d();
    }

    public static <E> zzgz<E> zzgh() {
        return n3.c();
    }

    @Override // com.google.android.gms.internal.vision.zzet
    public final void a(int i) {
        this.zzwk = i;
    }

    @Override // com.google.android.gms.internal.vision.zzet
    public final int b() {
        return this.zzwk;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return k3.b().c(this).b(this, (zzgs) obj);
        }
        return false;
    }

    public int hashCode() {
        int i = this.zzro;
        if (i != 0) {
            return i;
        }
        int a2 = k3.b().c(this).a(this);
        this.zzro = a2;
        return a2;
    }

    @Override // com.google.android.gms.internal.vision.zzie
    public final boolean isInitialized() {
        return zza(this, true);
    }

    public String toString() {
        return e3.a(this, super.toString());
    }

    public abstract Object zza(int i, Object obj, Object obj2);

    @Override // com.google.android.gms.internal.vision.zzic
    public final void zzb(zzga zzgaVar) throws IOException {
        k3.b().c(this).h(this, a2.p(zzgaVar));
    }

    @Override // com.google.android.gms.internal.vision.zzie
    public final /* synthetic */ zzic zzgd() {
        return (zzgs) zza(zzf.zzww, (Object) null, (Object) null);
    }

    public final <MessageType extends zzgs<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> BuilderType zzge() {
        return (BuilderType) zza(zzf.zzwv, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.vision.zzic
    public final int zzgf() {
        if (this.zzwk == -1) {
            this.zzwk = k3.b().c(this).f(this);
        }
        return this.zzwk;
    }

    @Override // com.google.android.gms.internal.vision.zzic
    public final /* synthetic */ zzib zzgi() {
        zza zzaVar = (zza) zza(zzf.zzwv, (Object) null, (Object) null);
        zzaVar.zza((zza) this);
        return zzaVar;
    }

    @Override // com.google.android.gms.internal.vision.zzic
    public final /* synthetic */ zzib zzgj() {
        return (zza) zza(zzf.zzwv, (Object) null, (Object) null);
    }

    public static Object zza(zzic zzicVar, String str, Object[] objArr) {
        return new m3(zzicVar, str, objArr);
    }

    public static <ContainingType extends zzic, Type> zzg<ContainingType, Type> zza(ContainingType containingtype, zzic zzicVar, zzgv<?> zzgvVar, int i, zzka zzkaVar, boolean z, Class cls) {
        return new zzg<>(containingtype, Collections.emptyList(), zzicVar, new a(null, 202056002, zzkaVar, true, false), cls);
    }

    /* loaded from: classes10.dex */
    public static abstract class zza<MessageType extends zzgs<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzew<MessageType, BuilderType> {
        public final MessageType h;
        public MessageType zzwh;
        public boolean zzwi = false;

        public zza(MessageType messagetype) {
            this.h = messagetype;
            this.zzwh = (MessageType) messagetype.zza(zzf.zzwu, null, null);
        }

        public static void a(MessageType messagetype, MessageType messagetype2) {
            k3.b().c(messagetype).c(messagetype, messagetype2);
        }

        @Override // com.google.android.gms.internal.vision.zzew
        /* renamed from: b */
        public final BuilderType zza(zzft zzftVar, zzgd zzgdVar) throws IOException {
            if (this.zzwi) {
                zzfy();
                this.zzwi = false;
            }
            try {
                k3.b().c(this.zzwh).i(this.zzwh, x1.M(zzftVar), zzgdVar);
                return this;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) {
                    throw ((IOException) e.getCause());
                }
                throw e;
            }
        }

        public final BuilderType c(byte[] bArr, int i, int i2, zzgd zzgdVar) throws zzhc {
            if (this.zzwi) {
                zzfy();
                this.zzwi = false;
            }
            try {
                k3.b().c(this.zzwh).g(this.zzwh, bArr, 0, i2 + 0, new f1(zzgdVar));
                return this;
            } catch (zzhc e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
            } catch (IndexOutOfBoundsException unused) {
                throw zzhc.zzgm();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.vision.zzew
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zzaVar = (zza) this.h.zza(zzf.zzwv, null, null);
            zzaVar.zza((zza) ((zzgs) zzgb()));
            return zzaVar;
        }

        @Override // com.google.android.gms.internal.vision.zzie
        public final boolean isInitialized() {
            return zzgs.zza(this.zzwh, false);
        }

        public final BuilderType zza(MessageType messagetype) {
            if (this.zzwi) {
                zzfy();
                this.zzwi = false;
            }
            a(this.zzwh, messagetype);
            return this;
        }

        @Override // com.google.android.gms.internal.vision.zzew
        public final /* synthetic */ zzew zzdn() {
            return (zza) clone();
        }

        public void zzfy() {
            MessageType messagetype = (MessageType) this.zzwh.zza(zzf.zzwu, null, null);
            a(messagetype, this.zzwh);
            this.zzwh = messagetype;
        }

        @Override // com.google.android.gms.internal.vision.zzib
        /* renamed from: zzfz */
        public MessageType zzgb() {
            if (this.zzwi) {
                return this.zzwh;
            }
            MessageType messagetype = this.zzwh;
            k3.b().c(messagetype).e(messagetype);
            this.zzwi = true;
            return this.zzwh;
        }

        @Override // com.google.android.gms.internal.vision.zzib
        /* renamed from: zzga */
        public final MessageType zzgc() {
            MessageType messagetype = (MessageType) zzgb();
            if (messagetype.isInitialized()) {
                return messagetype;
            }
            throw new zzjk(messagetype);
        }

        @Override // com.google.android.gms.internal.vision.zzie
        public final /* synthetic */ zzic zzgd() {
            return this.h;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.vision.zzew
        public final /* synthetic */ zzew zza(zzet zzetVar) {
            return zza((zza<MessageType, BuilderType>) ((zzgs) zzetVar));
        }

        @Override // com.google.android.gms.internal.vision.zzew
        public final /* synthetic */ zzew zza(byte[] bArr, int i, int i2, zzgd zzgdVar) throws zzhc {
            return c(bArr, 0, i2, zzgdVar);
        }
    }

    public static final <T extends zzgs<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zzf.zzwr, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean d = k3.b().c(t).d(t);
        if (z) {
            t.zza(zzf.zzws, d ? t : null, null);
        }
        return d;
    }

    public static <E> zzgz<E> zza(zzgz<E> zzgzVar) {
        int size = zzgzVar.size();
        return zzgzVar.zzah(size == 0 ? 10 : size << 1);
    }

    public static <T extends zzgs<T, ?>> T zza(T t, byte[] bArr) throws zzhc {
        return (T) g(d(t, bArr, 0, bArr.length, zzgd.zzfl()));
    }

    public static <T extends zzgs<T, ?>> T zza(T t, byte[] bArr, zzgd zzgdVar) throws zzhc {
        return (T) g(d(t, bArr, 0, bArr.length, zzgdVar));
    }
}
