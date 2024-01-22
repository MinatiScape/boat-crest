package io.reactivex.rxjava3.observables;

import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Observable;
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
