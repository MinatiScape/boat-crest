package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;
import javax.annotation.CheckForNull;
/* loaded from: classes6.dex */
public class j extends AbstractCollection {
    public final Object h;
    public Collection i;
    @CheckForNull
    public final j j;
    @CheckForNull
    public final Collection k;
    public final /* synthetic */ m l;

    public j(m mVar, Object obj, @CheckForNull Collection collection, j jVar) {
        this.l = mVar;
        this.h = obj;
        this.i = collection;
        this.j = jVar;
        this.k = jVar == null ? null : jVar.i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a() {
        j jVar = this.j;
        if (jVar != null) {
            jVar.a();
        } else {
            m.zzj(this.l).put(this.h, this.i);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean add(Object obj) {
        zzb();
        boolean isEmpty = this.i.isEmpty();
        boolean add = this.i.add(obj);
        if (add) {
            m.zzd(this.l);
            if (isEmpty) {
                a();
                return true;
            }
            return add;
        }
        return add;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean addAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = this.i.addAll(collection);
        if (addAll) {
            m.zzf(this.l, this.i.size() - size);
            if (size == 0) {
                a();
                return true;
            }
            return addAll;
        }
        return addAll;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b() {
        j jVar = this.j;
        if (jVar != null) {
            jVar.b();
        } else if (this.i.isEmpty()) {
            m.zzj(this.l).remove(this.h);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        int size = size();
        if (size == 0) {
            return;
        }
        this.i.clear();
        m.zzg(this.l, size);
        b();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        zzb();
        return this.i.contains(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean containsAll(Collection collection) {
        zzb();
        return this.i.containsAll(collection);
    }

    @Override // java.util.Collection
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        zzb();
        return this.i.equals(obj);
    }

    @Override // java.util.Collection
    public final int hashCode() {
        zzb();
        return this.i.hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        zzb();
        return new i(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean remove(@CheckForNull Object obj) {
        zzb();
        boolean remove = this.i.remove(obj);
        if (remove) {
            m.zze(this.l);
            b();
        }
        return remove;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean removeAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean removeAll = this.i.removeAll(collection);
        if (removeAll) {
            m.zzf(this.l, this.i.size() - size);
            b();
        }
        return removeAll;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean retainAll(Collection collection) {
        Objects.requireNonNull(collection);
        int size = size();
        boolean retainAll = this.i.retainAll(collection);
        if (retainAll) {
            m.zzf(this.l, this.i.size() - size);
            b();
        }
        return retainAll;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        zzb();
        return this.i.size();
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        zzb();
        return this.i.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb() {
        Collection collection;
        j jVar = this.j;
        if (jVar != null) {
            jVar.zzb();
            if (this.j.i != this.k) {
                throw new ConcurrentModificationException();
            }
        } else if (!this.i.isEmpty() || (collection = (Collection) m.zzj(this.l).get(this.h)) == null) {
        } else {
            this.i = collection;
        }
    }
}
