package com.google.common.graph;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public interface n<N, V> {
    Set<N> a();

    Set<N> b();

    Set<N> c();

    @NullableDecl
    V d(N n);

    @CanIgnoreReturnValue
    V e(N n);

    void f(N n);

    Iterator<EndpointPair<N>> g(N n);

    @CanIgnoreReturnValue
    V h(N n, V v);

    void i(N n, V v);
}
