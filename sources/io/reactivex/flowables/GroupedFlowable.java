package io.reactivex.flowables;

import io.reactivex.Flowable;
import io.reactivex.annotations.Nullable;
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
