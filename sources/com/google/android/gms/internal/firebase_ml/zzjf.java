package com.google.android.gms.internal.firebase_ml;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
/* loaded from: classes7.dex */
public class zzjf extends AbstractMap<String, Object> implements Cloneable {
    public Map<String, Object> h;
    public final zziv i;

    /* loaded from: classes7.dex */
    public final class a implements Iterator<Map.Entry<String, Object>> {
        public boolean h;
        public final Iterator<Map.Entry<String, Object>> i;
        public final Iterator<Map.Entry<String, Object>> j;

        public a(zzjf zzjfVar, d1 d1Var) {
            this.i = (e1) d1Var.iterator();
            this.j = zzjfVar.h.entrySet().iterator();
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.i.hasNext() || this.j.hasNext();
        }

        @Override // java.util.Iterator
        public final /* synthetic */ Map.Entry<String, Object> next() {
            if (!this.h) {
                if (this.i.hasNext()) {
                    return this.i.next();
                }
                this.h = true;
            }
            return this.j.next();
        }

        @Override // java.util.Iterator
        public final void remove() {
            if (this.h) {
                this.j.remove();
            }
            this.i.remove();
        }
    }

    /* loaded from: classes7.dex */
    public final class b extends AbstractSet<Map.Entry<String, Object>> {
        public final d1 h;

        public b() {
            this.h = (d1) new c1(zzjf.this, zzjf.this.i.zzhy()).entrySet();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final void clear() {
            zzjf.this.h.clear();
            this.h.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public final Iterator<Map.Entry<String, Object>> iterator() {
            return new a(zzjf.this, this.h);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return zzjf.this.h.size() + this.h.size();
        }
    }

    /* loaded from: classes7.dex */
    public enum zzb {
        IGNORE_CASE
    }

    public zzjf() {
        this(EnumSet.noneOf(zzb.class));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<String, Object>> entrySet() {
        return new b();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        if (obj instanceof String) {
            String str = (String) obj;
            zzjd zzao = this.i.zzao(str);
            if (zzao != null) {
                return zzao.zzh(this);
            }
            if (this.i.zzhy()) {
                str = str.toLowerCase(Locale.US);
            }
            return this.h.get(str);
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void putAll(Map<? extends String, ?> map) {
        for (Map.Entry<? extends String, ?> entry : map.entrySet()) {
            zzb(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        if (obj instanceof String) {
            String str = (String) obj;
            if (this.i.zzao(str) == null) {
                if (this.i.zzhy()) {
                    str = str.toLowerCase(Locale.US);
                }
                return this.h.remove(str);
            }
            throw new UnsupportedOperationException();
        }
        return null;
    }

    public zzjf zzb(String str, Object obj) {
        zzjd zzao = this.i.zzao(str);
        if (zzao != null) {
            zzao.zzb(this, obj);
        } else {
            if (this.i.zzhy()) {
                str = str.toLowerCase(Locale.US);
            }
            this.h.put(str, obj);
        }
        return this;
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* renamed from: zzf */
    public final Object put(String str, Object obj) {
        zzjd zzao = this.i.zzao(str);
        if (zzao != null) {
            Object zzh = zzao.zzh(this);
            zzao.zzb(this, obj);
            return zzh;
        }
        if (this.i.zzhy()) {
            str = str.toLowerCase(Locale.US);
        }
        return this.h.put(str, obj);
    }

    @Override // java.util.AbstractMap
    /* renamed from: zzfd */
    public zzjf clone() {
        try {
            zzjf zzjfVar = (zzjf) super.clone();
            zzix.zza(this, zzjfVar);
            zzjfVar.h = (Map) zzix.clone(this.h);
            return zzjfVar;
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(e);
        }
    }

    public final zziv zzie() {
        return this.i;
    }

    public zzjf(EnumSet<zzb> enumSet) {
        this.h = new zziq();
        this.i = zziv.zza(getClass(), enumSet.contains(zzb.IGNORE_CASE));
    }
}
