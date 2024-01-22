package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzsf;
import com.google.android.libraries.places.internal.zzsf.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes10.dex */
public abstract class zzsf<MessageType extends zzsf<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzqw<MessageType, BuilderType> {
    private static Map<Object, zzsf<?, ?>> zzd = new ConcurrentHashMap();
    public zzuz zzb = zzuz.zza();
    private int zzc = -1;

    /* loaded from: classes10.dex */
    public static abstract class zzb<MessageType extends zzb<MessageType, BuilderType>, BuilderType> extends zzsf<MessageType, BuilderType> implements zztq {
        public zzrx<zze> zzc = zzrx.zza();
    }

    /* loaded from: classes10.dex */
    public static class zzc<T extends zzsf<T, ?>> extends zzqx<T> {
        private final T zza;

        public zzc(T t) {
            this.zza = t;
        }
    }

    /* loaded from: classes10.dex */
    public enum zzd {
        public static final int zza = 1;
        public static final int zzb = 2;
        public static final int zzc = 3;
        public static final int zzd = 4;
        public static final int zze = 5;
        public static final int zzf = 6;
        public static final int zzg = 7;
        private static final /* synthetic */ int[] zzh = {1, 2, 3, 4, 5, 6, 7};

        public static int[] zza() {
            return (int[]) zzh.clone();
        }
    }

    /* loaded from: classes10.dex */
    public static final class zze implements zzrz<zze> {
        @Override // java.lang.Comparable
        public final /* synthetic */ int compareTo(Object obj) {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.libraries.places.internal.zzrz
        public final int zza() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.libraries.places.internal.zzrz
        public final zzvk zzb() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.libraries.places.internal.zzrz
        public final zzvr zzc() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.libraries.places.internal.zzrz
        public final boolean zzd() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.libraries.places.internal.zzrz
        public final boolean zze() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.libraries.places.internal.zzrz
        public final zztr zza(zztr zztrVar, zzto zztoVar) {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.libraries.places.internal.zzrz
        public final zztu zza(zztu zztuVar, zztu zztuVar2) {
            throw new NoSuchMethodError();
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.libraries.places.internal.zzsh, com.google.android.libraries.places.internal.zzsk] */
    public static zzsk zzi() {
        return zzsh.zzd();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.libraries.places.internal.zzsm, com.google.android.libraries.places.internal.zztc] */
    public static zzsm zzj() {
        return zztc.zzd();
    }

    public static <E> zzsp<E> zzk() {
        return zzuc.zzd();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzud.zza().zza((zzud) this).zza(this, (zzsf) obj);
        }
        return false;
    }

    public int hashCode() {
        int i = this.zza;
        if (i != 0) {
            return i;
        }
        int zza2 = zzud.zza().zza((zzud) this).zza(this);
        this.zza = zza2;
        return zza2;
    }

    public String toString() {
        return zztt.zza(this, super.toString());
    }

    public abstract Object zza(int i, Object obj, Object obj2);

    @Override // com.google.android.libraries.places.internal.zzqw
    final void zza(int i) {
        this.zzc = i;
    }

    @Override // com.google.android.libraries.places.internal.zztq
    public final boolean zzc() {
        return zza(this, true);
    }

    @Override // com.google.android.libraries.places.internal.zzqw
    final int zze() {
        return this.zzc;
    }

    public final <MessageType extends zzsf<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> BuilderType zzf() {
        return (BuilderType) zza(zzd.zze, (Object) null, (Object) null);
    }

    @Override // com.google.android.libraries.places.internal.zzto
    public final int zzg() {
        if (this.zzc == -1) {
            this.zzc = zzud.zza().zza((zzud) this).zzb(this);
        }
        return this.zzc;
    }

    @Override // com.google.android.libraries.places.internal.zztq
    public final /* synthetic */ zzto zzh() {
        return (zzsf) zza(zzd.zzf, (Object) null, (Object) null);
    }

    @Override // com.google.android.libraries.places.internal.zzto
    public final /* synthetic */ zztr zzl() {
        zza zzaVar = (zza) zza(zzd.zze, (Object) null, (Object) null);
        zzaVar.zza((zza) this);
        return zzaVar;
    }

    @Override // com.google.android.libraries.places.internal.zzto
    public final void zza(zzrs zzrsVar) throws IOException {
        zzud.zza().zza((zzud) this).zza((zzue) this, (zzvq) zzru.zza(zzrsVar));
    }

    /* loaded from: classes10.dex */
    public static abstract class zza<MessageType extends zzsf<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzqv<MessageType, BuilderType> {
        public MessageType zza;
        public boolean zzb = false;
        private final MessageType zzc;

        public zza(MessageType messagetype) {
            this.zzc = messagetype;
            this.zza = (MessageType) messagetype.zza(zzd.zzd, null, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.libraries.places.internal.zzqv
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zzaVar = (zza) this.zzc.zza(zzd.zze, null, null);
            zzaVar.zza((zza) ((zzsf) zzf()));
            return zzaVar;
        }

        public final BuilderType zza(MessageType messagetype) {
            if (this.zzb) {
                zzb();
                this.zzb = false;
            }
            zza(this.zza, messagetype);
            return this;
        }

        public void zzb() {
            MessageType messagetype = (MessageType) this.zza.zza(zzd.zzd, null, null);
            zza(messagetype, this.zza);
            this.zza = messagetype;
        }

        @Override // com.google.android.libraries.places.internal.zztq
        public final boolean zzc() {
            return zzsf.zza(this.zza, false);
        }

        @Override // com.google.android.libraries.places.internal.zztr
        /* renamed from: zzd */
        public MessageType zzf() {
            if (this.zzb) {
                return this.zza;
            }
            MessageType messagetype = this.zza;
            zzud.zza().zza((zzud) messagetype).zzc(messagetype);
            this.zzb = true;
            return this.zza;
        }

        @Override // com.google.android.libraries.places.internal.zztr
        /* renamed from: zze */
        public final MessageType zzg() {
            MessageType messagetype = (MessageType) zzf();
            if (messagetype.zzc()) {
                return messagetype;
            }
            throw new zzux(messagetype);
        }

        @Override // com.google.android.libraries.places.internal.zztq
        public final /* synthetic */ zzto zzh() {
            return this.zzc;
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzud.zza().zza((zzud) messagetype).zzb(messagetype, messagetype2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.libraries.places.internal.zzqv
        public final /* synthetic */ zzqv zza(zzqw zzqwVar) {
            return zza((zza<MessageType, BuilderType>) ((zzsf) zzqwVar));
        }

        @Override // com.google.android.libraries.places.internal.zzqv
        public final /* synthetic */ zzqv zza() {
            return (zza) clone();
        }
    }

    public static <T extends zzsf<?, ?>> T zza(Class<T> cls) {
        zzsf<?, ?> zzsfVar = zzd.get(cls);
        if (zzsfVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzsfVar = zzd.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzsfVar == null) {
            zzsfVar = (T) ((zzsf) zzvc.zza(cls)).zza(zzd.zzf, (Object) null, (Object) null);
            if (zzsfVar != null) {
                zzd.put(cls, zzsfVar);
            } else {
                throw new IllegalStateException();
            }
        }
        return (T) zzsfVar;
    }

    public static <T extends zzsf<?, ?>> void zza(Class<T> cls, T t) {
        zzd.put(cls, t);
    }

    public static Object zza(zzto zztoVar, String str, Object[] objArr) {
        return new zzuf(zztoVar, str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zza(Method method, Object obj, Object... objArr) {
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

    public static final <T extends zzsf<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zzd.zza, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzd2 = zzud.zza().zza((zzud) t).zzd(t);
        if (z) {
            t.zza(zzd.zzb, zzd2 ? t : null, null);
        }
        return zzd2;
    }

    public static <E> zzsp<E> zza(zzsp<E> zzspVar) {
        int size = zzspVar.size();
        return zzspVar.zzb(size == 0 ? 10 : size << 1);
    }
}
