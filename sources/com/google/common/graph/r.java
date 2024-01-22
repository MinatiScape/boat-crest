package com.google.common.graph;

import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public class r<K, V> extends q<K, V> {
    @NullableDecl
    public volatile transient a<K, V> c;
    @NullableDecl
    public volatile transient a<K, V> d;

    /* loaded from: classes10.dex */
    public static final class a<K, V> {

        /* renamed from: a  reason: collision with root package name */
        public final K f10638a;
        public final V b;

        public a(K k, V v) {
            this.f10638a = k;
            this.b = v;
        }
    }

    public r(Map<K, V> map) {
        super(map);
    }

    @Override // com.google.common.graph.q
    public void c() {
        super.c();
        this.c = null;
        this.d = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.q
    public V e(@NullableDecl Object obj) {
        V f = f(obj);
        if (f != null) {
            return f;
        }
        V g = g(obj);
        if (g != null) {
            l(obj, g);
        }
        return g;
    }

    @Override // com.google.common.graph.q
    public V f(@NullableDecl Object obj) {
        V v = (V) super.f(obj);
        if (v != null) {
            return v;
        }
        a<K, V> aVar = this.c;
        if (aVar != null && aVar.f10638a == obj) {
            return aVar.b;
        }
        a<K, V> aVar2 = this.d;
        if (aVar2 == null || aVar2.f10638a != obj) {
            return null;
        }
        k(aVar2);
        return aVar2.b;
    }

    public final void k(a<K, V> aVar) {
        this.d = this.c;
        this.c = aVar;
    }

    public final void l(K k, V v) {
        k(new a<>(k, v));
    }
}
