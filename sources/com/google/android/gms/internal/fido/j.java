package com.google.android.gms.internal.fido;

import java.util.Iterator;
import javax.annotation.CheckForNull;
/* loaded from: classes7.dex */
public final class j extends zzau {
    public static final j zza;
    private static final Object[] zzd;
    public final transient Object[] zzb;
    public final transient Object[] zzc;
    private final transient int zze;
    private final transient int zzf;
    private final transient int zzg;

    static {
        Object[] objArr = new Object[0];
        zzd = objArr;
        zza = new j(objArr, 0, objArr, 0, 0);
    }

    public j(Object[] objArr, int i, Object[] objArr2, int i2, int i3) {
        this.zzb = objArr;
        this.zze = i;
        this.zzc = objArr2;
        this.zzf = i2;
        this.zzg = i3;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        Object[] objArr = this.zzc;
        if (obj == null || objArr.length == 0) {
            return false;
        }
        int a2 = e.a(obj.hashCode());
        while (true) {
            int i = a2 & this.zzf;
            Object obj2 = objArr[i];
            if (obj2 == null) {
                return false;
            }
            if (obj2.equals(obj)) {
                return true;
            }
            a2 = i + 1;
        }
    }

    @Override // com.google.android.gms.internal.fido.zzau, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.fido.zzau, com.google.android.gms.internal.fido.zzaq, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return zzg().listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.fido.zzaq
    public final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzb, 0, objArr, 0, this.zzg);
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.fido.zzaq
    public final int zzb() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.fido.zzaq
    public final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.fido.zzau, com.google.android.gms.internal.fido.zzaq
    public final zzaz zzd() {
        return zzg().listIterator(0);
    }

    @Override // com.google.android.gms.internal.fido.zzaq
    public final Object[] zze() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.fido.zzau
    public final zzat zzh() {
        return zzat.zzg(this.zzb, this.zzg);
    }

    @Override // com.google.android.gms.internal.fido.zzau
    public final boolean zzj() {
        return true;
    }
}
