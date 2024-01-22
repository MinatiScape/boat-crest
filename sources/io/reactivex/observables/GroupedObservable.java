package io.reactivex.observables;

import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;
/* loaded from: classes12.dex */
public abstract class GroupedObservable<K, T> extends Observable<T> {
    public final K h;

    public GroupedObservable(@Nullable K k) {
        this.h = k;
    }

    @Nullable
    public K getKey() {
        return this.h;
    }
}
