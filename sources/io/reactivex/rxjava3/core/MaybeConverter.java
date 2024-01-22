package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.annotations.NonNull;
@FunctionalInterface
/* loaded from: classes12.dex */
public interface MaybeConverter<T, R> {
    @NonNull
    R apply(@NonNull Maybe<T> maybe);
}
