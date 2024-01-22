package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;
/* loaded from: classes6.dex */
public final class u extends AbstractSet {
    public final /* synthetic */ z h;

    public u(z zVar) {
        this.h = zVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.h.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        int zzv;
        Map zzl = this.h.zzl();
        if (zzl != null) {
            return zzl.entrySet().contains(obj);
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            zzv = this.h.zzv(entry.getKey());
            if (zzv != -1 && zzw.zza(z.zzj(this.h, zzv), entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        z zVar = this.h;
        Map zzl = zVar.zzl();
        if (zzl != null) {
            return zzl.entrySet().iterator();
        }
        return new s(zVar);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(@CheckForNull Object obj) {
        int zzu;
        int[] zzz;
        Object[] zzA;
        Object[] zzB;
        Map zzl = this.h.zzl();
        if (zzl != null) {
            return zzl.entrySet().remove(obj);
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            z zVar = this.h;
            if (zVar.zzq()) {
                return false;
            }
            zzu = zVar.zzu();
            Object key = entry.getKey();
            Object value = entry.getValue();
            Object zzk = z.zzk(this.h);
            zzz = this.h.zzz();
            zzA = this.h.zzA();
            zzB = this.h.zzB();
            int b = a0.b(key, value, zzu, zzk, zzz, zzA, zzB);
            if (b == -1) {
                return false;
            }
            this.h.zzp(b, zzu);
            z.zzb(this.h);
            this.h.zzn();
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.h.size();
    }
}
