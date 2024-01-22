package com.google.common.graph;

import com.google.common.base.Optional;
/* loaded from: classes10.dex */
public abstract class c<N> {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f10631a;
    public boolean b = false;
    public ElementOrder<N> c = ElementOrder.insertion();
    public ElementOrder<N> d = ElementOrder.unordered();
    public Optional<Integer> e = Optional.absent();

    public c(boolean z) {
        this.f10631a = z;
    }
}
