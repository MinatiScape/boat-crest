package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.UnmodifiableIterator;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public abstract class s<E> extends AbstractSet<E> {
    public final Map<E, ?> h;
    public final Object i;

    /* loaded from: classes10.dex */
    public class a extends AbstractIterator<E> {
        public final /* synthetic */ Iterator j;

        public a(Iterator it) {
            this.j = it;
        }

        @Override // com.google.common.collect.AbstractIterator
        public E computeNext() {
            while (this.j.hasNext()) {
                Map.Entry entry = (Map.Entry) this.j.next();
                if (s.this.i.equals(entry.getValue())) {
                    return (E) entry.getKey();
                }
            }
            return endOfData();
        }
    }

    public s(Map<E, ?> map, Object obj) {
        this.h = (Map) Preconditions.checkNotNull(map);
        this.i = Preconditions.checkNotNull(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    /* renamed from: b */
    public UnmodifiableIterator<E> iterator() {
        return new a(this.h.entrySet().iterator());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(@NullableDecl Object obj) {
        return this.i.equals(this.h.get(obj));
    }
}
