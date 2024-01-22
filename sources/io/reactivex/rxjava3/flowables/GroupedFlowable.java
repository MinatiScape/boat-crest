package io.reactivex.rxjava3.flowables;

import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Flowable;
/* loaded from: classes12.dex */
public abstract class GroupedFlowable<K, T> extends Flowable<T> {
    public final K i;

    public GroupedFlowable(@Nullable K k) {
        this.i = k;
    }

    @Nullable
    public K getKey() {
        return this.i;
    }
}
