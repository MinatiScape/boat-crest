package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.CheckForNull;
/* loaded from: classes9.dex */
public class u extends s implements List {
    public final /* synthetic */ v m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(v vVar, Object obj, @CheckForNull List list, s sVar) {
        super(vVar, obj, list, sVar);
        this.m = vVar;
    }

    @Override // java.util.List
    public final void add(int i, Object obj) {
        int i2;
        zzb();
        boolean isEmpty = this.i.isEmpty();
        ((List) this.i).add(i, obj);
        v vVar = this.m;
        i2 = vVar.zzb;
        vVar.zzb = i2 + 1;
        if (isEmpty) {
            a();
        }
    }

    @Override // java.util.List
    public final boolean addAll(int i, Collection collection) {
        int i2;
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = ((List) this.i).addAll(i, collection);
        if (addAll) {
            int size2 = this.i.size();
            v vVar = this.m;
            i2 = vVar.zzb;
            vVar.zzb = i2 + (size2 - size);
            if (size == 0) {
                a();
                return true;
            }
            return addAll;
        }
        return addAll;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzb();
        return ((List) this.i).get(i);
    }

    @Override // java.util.List
    public final int indexOf(@CheckForNull Object obj) {
        zzb();
        return ((List) this.i).indexOf(obj);
    }

    @Override // java.util.List
    public final int lastIndexOf(@CheckForNull Object obj) {
        zzb();
        return ((List) this.i).lastIndexOf(obj);
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        zzb();
        return new t(this);
    }

    @Override // java.util.List
    public final Object remove(int i) {
        int i2;
        zzb();
        Object remove = ((List) this.i).remove(i);
        v vVar = this.m;
        i2 = vVar.zzb;
        vVar.zzb = i2 - 1;
        b();
        return remove;
    }

    @Override // java.util.List
    public final Object set(int i, Object obj) {
        zzb();
        return ((List) this.i).set(i, obj);
    }

    @Override // java.util.List
    public final List subList(int i, int i2) {
        zzb();
        v vVar = this.m;
        Object obj = this.h;
        List subList = ((List) this.i).subList(i, i2);
        s sVar = this.j;
        if (sVar == null) {
            sVar = this;
        }
        return vVar.zzm(obj, subList, sVar);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i) {
        zzb();
        return new t(this, i);
    }
}
