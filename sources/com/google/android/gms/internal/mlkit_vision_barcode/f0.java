package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;
/* loaded from: classes9.dex */
public final class f0 extends AbstractSet {
    public final /* synthetic */ k0 h;

    public f0(k0 k0Var) {
        this.h = k0Var;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.h.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        int zzq;
        Map zzj = this.h.zzj();
        if (zzj != null) {
            return zzj.entrySet().contains(obj);
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            zzq = this.h.zzq(entry.getKey());
            if (zzq != -1) {
                Object[] objArr = this.h.zzc;
                objArr.getClass();
                if (zzay.zza(objArr[zzq], entry.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        k0 k0Var = this.h;
        Map zzj = k0Var.zzj();
        if (zzj != null) {
            return zzj.entrySet().iterator();
        }
        return new d0(k0Var);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(@CheckForNull Object obj) {
        int zzp;
        int i;
        Map zzj = this.h.zzj();
        if (zzj != null) {
            return zzj.entrySet().remove(obj);
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            k0 k0Var = this.h;
            if (k0Var.zzo()) {
                return false;
            }
            zzp = k0Var.zzp();
            Object key = entry.getKey();
            Object value = entry.getValue();
            Object zzh = k0.zzh(this.h);
            k0 k0Var2 = this.h;
            int[] iArr = k0Var2.zza;
            iArr.getClass();
            Object[] objArr = k0Var2.zzb;
            objArr.getClass();
            Object[] objArr2 = k0Var2.zzc;
            objArr2.getClass();
            int b = l0.b(key, value, zzp, zzh, iArr, objArr, objArr2);
            if (b == -1) {
                return false;
            }
            this.h.zzn(b, zzp);
            k0 k0Var3 = this.h;
            i = k0Var3.zzg;
            k0Var3.zzg = i - 1;
            this.h.zzl();
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.h.size();
    }
}
