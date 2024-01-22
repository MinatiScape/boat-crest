package com.jakewharton.rxrelay2;

import io.reactivex.Observable;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
/* loaded from: classes11.dex */
public abstract class Relay<T> extends Observable<T> implements Consumer<T> {
    public abstract void accept(@NonNull T t);

    public abstract boolean hasObservers();

    @CheckReturnValue
    @NonNull
    public final Relay<T> toSerialized() {
        return this instanceof a ? this : new a(this);
    }
}
