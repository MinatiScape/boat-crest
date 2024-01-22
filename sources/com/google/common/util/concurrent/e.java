package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public abstract class e<V, C> extends c<V, C> {
    public List<b<V>> w;

    /* loaded from: classes10.dex */
    public static final class a<V> extends e<V, List<V>> {
        public a(ImmutableCollection<? extends ListenableFuture<? extends V>> immutableCollection, boolean z) {
            super(immutableCollection, z);
            O();
        }

        @Override // com.google.common.util.concurrent.e
        /* renamed from: T */
        public List<V> S(List<b<V>> list) {
            ArrayList newArrayListWithCapacity = Lists.newArrayListWithCapacity(list.size());
            Iterator<b<V>> it = list.iterator();
            while (it.hasNext()) {
                b<V> next = it.next();
                newArrayListWithCapacity.add(next != null ? next.f10808a : null);
            }
            return Collections.unmodifiableList(newArrayListWithCapacity);
        }
    }

    /* loaded from: classes10.dex */
    public static final class b<V> {

        /* renamed from: a  reason: collision with root package name */
        public V f10808a;

        public b(V v) {
            this.f10808a = v;
        }
    }

    public e(ImmutableCollection<? extends ListenableFuture<? extends V>> immutableCollection, boolean z) {
        super(immutableCollection, z, true);
        List<b<V>> newArrayListWithCapacity;
        if (immutableCollection.isEmpty()) {
            newArrayListWithCapacity = ImmutableList.of();
        } else {
            newArrayListWithCapacity = Lists.newArrayListWithCapacity(immutableCollection.size());
        }
        for (int i = 0; i < immutableCollection.size(); i++) {
            newArrayListWithCapacity.add(null);
        }
        this.w = newArrayListWithCapacity;
    }

    @Override // com.google.common.util.concurrent.c
    public final void J(int i, @NullableDecl V v) {
        List<b<V>> list = this.w;
        if (list != null) {
            list.set(i, new b<>(v));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.util.concurrent.c
    public final void M() {
        List<b<V>> list = this.w;
        if (list != null) {
            set(S(list));
        }
    }

    @Override // com.google.common.util.concurrent.c
    public void R(c.EnumC0529c enumC0529c) {
        super.R(enumC0529c);
        this.w = null;
    }

    public abstract C S(List<b<V>> list);
}
