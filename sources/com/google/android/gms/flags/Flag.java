package com.google.android.gms.flags;

import android.os.RemoteException;
import com.google.android.gms.common.annotation.KeepForSdk;
@KeepForSdk
@Deprecated
/* loaded from: classes6.dex */
public abstract class Flag<T> {

    /* renamed from: a  reason: collision with root package name */
    public final int f8467a;
    public final String b;
    public final T c;

    @Deprecated
    /* loaded from: classes6.dex */
    public static class BooleanFlag extends Flag<Boolean> {
        public BooleanFlag(int i, String str, Boolean bool) {
            super(i, str, bool);
        }

        @Override // com.google.android.gms.flags.Flag
        /* renamed from: a */
        public final Boolean zza(zzc zzcVar) {
            try {
                return Boolean.valueOf(zzcVar.getBooleanFlagValue(getKey(), zzb().booleanValue(), getSource()));
            } catch (RemoteException unused) {
                return zzb();
            }
        }
    }

    @Deprecated
    @KeepForSdk
    /* loaded from: classes6.dex */
    public static class IntegerFlag extends Flag<Integer> {
        public IntegerFlag(int i, String str, Integer num) {
            super(i, str, num);
        }

        @Override // com.google.android.gms.flags.Flag
        /* renamed from: a */
        public final Integer zza(zzc zzcVar) {
            try {
                return Integer.valueOf(zzcVar.getIntFlagValue(getKey(), zzb().intValue(), getSource()));
            } catch (RemoteException unused) {
                return zzb();
            }
        }
    }

    @Deprecated
    @KeepForSdk
    /* loaded from: classes6.dex */
    public static class LongFlag extends Flag<Long> {
        public LongFlag(int i, String str, Long l) {
            super(i, str, l);
        }

        @Override // com.google.android.gms.flags.Flag
        /* renamed from: a */
        public final Long zza(zzc zzcVar) {
            try {
                return Long.valueOf(zzcVar.getLongFlagValue(getKey(), zzb().longValue(), getSource()));
            } catch (RemoteException unused) {
                return zzb();
            }
        }
    }

    @Deprecated
    @KeepForSdk
    /* loaded from: classes6.dex */
    public static class StringFlag extends Flag<String> {
        public StringFlag(int i, String str, String str2) {
            super(i, str, str2);
        }

        @Override // com.google.android.gms.flags.Flag
        /* renamed from: a */
        public final String zza(zzc zzcVar) {
            try {
                return zzcVar.getStringFlagValue(getKey(), zzb(), getSource());
            } catch (RemoteException unused) {
                return zzb();
            }
        }
    }

    public Flag(int i, String str, T t) {
        this.f8467a = i;
        this.b = str;
        this.c = t;
        Singletons.flagRegistry().zza(this);
    }

    @KeepForSdk
    @Deprecated
    public static BooleanFlag define(int i, String str, Boolean bool) {
        return new BooleanFlag(i, str, bool);
    }

    @KeepForSdk
    public T get() {
        return (T) Singletons.zzd().zzb(this);
    }

    public final String getKey() {
        return this.b;
    }

    @Deprecated
    public final int getSource() {
        return this.f8467a;
    }

    public abstract T zza(zzc zzcVar);

    public final T zzb() {
        return this.c;
    }

    @KeepForSdk
    @Deprecated
    public static IntegerFlag define(int i, String str, int i2) {
        return new IntegerFlag(i, str, Integer.valueOf(i2));
    }

    @KeepForSdk
    @Deprecated
    public static LongFlag define(int i, String str, long j) {
        return new LongFlag(i, str, Long.valueOf(j));
    }

    @KeepForSdk
    @Deprecated
    public static StringFlag define(int i, String str, String str2) {
        return new StringFlag(i, str, str2);
    }
}
