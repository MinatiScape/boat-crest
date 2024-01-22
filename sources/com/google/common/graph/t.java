package com.google.common.graph;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Set;
/* loaded from: classes10.dex */
public interface t<N, E> {
    Set<N> a();

    Set<N> b();

    Set<N> c();

    @CanIgnoreReturnValue
    N d(E e, boolean z);

    void e(E e, N n);

    void f(E e, N n, boolean z);

    Set<E> g();

    N h(E e);

    Set<E> i();

    @CanIgnoreReturnValue
    N j(E e);

    Set<E> k();

    Set<E> l(N n);
}
