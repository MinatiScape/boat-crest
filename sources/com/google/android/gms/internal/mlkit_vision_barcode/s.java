package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;
import javax.annotation.CheckForNull;
/* loaded from: classes9.dex */
public class s extends AbstractCollection {
    public final Object h;
    public Collection i;
    @CheckForNull
    public final s j;
    @CheckForNull
    public final Collection k;
    public final /* synthetic */ v l;

    public s(v vVar, Object obj, @CheckForNull Collection collection, s sVar) {
        this.l = vVar;
        this.h = obj;
        this.i = collection;
        this.j = sVar;
        this.k = sVar == null ? null : sVar.i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a() {
        s sVar = this.j;
        if (sVar != null) {
            sVar.a();
        } else {
            v.zzn(this.l).put(this.h, this.i);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean add(Object obj) {
        zzb();
        boolean isEmpty = this.i.isEmpty();
        boolean add = this.i.add(obj);
        if (add) {
            v vVar = this.l;
            v.zzq(vVar, v.zzg(vVar) + 1);
            if (isEmpty) {
                a();
                return true;
            }
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
            int size2 = this.i.size();
            v vVar = this.l;
            v.zzq(vVar, v.zzg(vVar) + (size2 - size));
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
        s sVar = this.j;
        if (sVar != null) {
            sVar.b();
        } else if (this.i.isEmpty()) {
            v.zzn(this.l).remove(this.h);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        int size = size();
        if (size == 0) {
            return;
        }
        this.i.clear();
        v vVar = this.l;
        v.zzq(vVar, v.zzg(vVar) - size);
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
        return new r(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean remove(@CheckForNull Object obj) {
        zzb();
        boolean remove = this.i.remove(obj);
        if (remove) {
            v vVar = this.l;
            v.zzq(vVar, v.zzg(vVar) - 1);
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
            int size2 = this.i.size();
            v vVar = this.l;
            v.zzq(vVar, v.zzg(vVar) + (size2 - size));
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
            int size2 = this.i.size();
            v vVar = this.l;
            v.zzq(vVar, v.zzg(vVar) + (size2 - size));
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
        s sVar = this.j;
        if (sVar != null) {
            sVar.zzb();
            if (this.j.i != this.k) {
                throw new ConcurrentModificationException();
            }
        } else if (!this.i.isEmpty() || (collection = (Collection) v.zzn(this.l).get(this.h)) == null) {
        } else {
            this.i = collection;
        }
    }
}
